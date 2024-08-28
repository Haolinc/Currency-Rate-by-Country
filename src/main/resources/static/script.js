async function fetchCountryNameList(inputId, listId) {
    let countryInput = document.getElementById(inputId).value;
    let isValidCountry = await checkIfCountryNameValid(countryInput);
    let countryList = document.getElementById(listId);
    countryList.innerHTML = null;
    // Limit Request Amount
    if (countryInput == "" || countryInput.length > 50 || isValidCountry)
        return;
    
    const url = `/country?countryName=${encodeURIComponent(countryInput)}`;

    // GET response from Country Search controller
    try {
        const response = await fetch(url);
        if (response.ok){
            const responseJson = await response.json();
            Array.from(responseJson).forEach(countryName => {
                console.log(`name: ${countryName}`);
                let option = document.createElement('option');
                option.value = option.innerHTML = countryName;
                countryList.appendChild(option);
            });
        }
        else{
            console.error('Non-OK status:', response.status)
            return false;
        }
    } catch (error) {
        console.error('Error fetching data:', error);
        return false;
    }
}

async function displayCurrencyInput(){
    const currencyForm = document.getElementById("currencyForm");
    const sourceCountryName = document.getElementById("country1").value;
    const targetCountryName = document.getElementById("country2").value;
    const isValidSourceCountry = await checkIfCountryNameValid(sourceCountryName);
    const isValidTargetCountry = await checkIfCountryNameValid(targetCountryName);
    if (isValidSourceCountry && isValidTargetCountry){
        console.log("field unlocked!")
        currencyForm.style.display = "block";
        document.getElementById("country1CurrencyAmount").value = 1;
        await setCurrencyAmountByRate("country1", "country2", "country1CurrencyAmount", "country2CurrencyAmount");
    } else{
        console.log("field locked!")
        currencyForm.style.display = "none";
    }
}

async function checkIfCountryNameValid(countryName) {
    try {
        const url = `/isValidCountry?countryName=${encodeURIComponent(countryName)}`;
        const response = await fetch(url);
        if (response.ok) {
            const isValidCountry = await response.json();
            return isValidCountry;
        } else {
            console.error('Non-OK status:', response.status)
            return false;
        }
    } catch (error) {
        console.error('Error fetching data:', error);
        return false;
    }
}

async function setCurrencyAmountByRate(inputCountryId, targetCountryId, inputElementId, targetElementId){
    try {
        const countryName1 = document.getElementById(inputCountryId).value;
        const countryName2 = document.getElementById(targetCountryId).value;
        const inputNumber = document.getElementById(inputElementId).value;
        if (inputNumber.length == 0)
            return;
        const url = `/currencyAmount?country1=${encodeURIComponent(countryName1)}&country2=${encodeURIComponent(countryName2)}&inputRate=${encodeURIComponent(inputNumber)}`
        const response = await fetch(url);
        if (response.ok){
            const rate = await response.json();
            document.getElementById(targetElementId).value = rate;
        }
        else{
            console.error('Non-OK status:', response.status)
            return -1;
        }
    } catch (error) {
        console.error('Error fetching data:', error);
        return -1;
    }
}