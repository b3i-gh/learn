const APIKEY = "76259e0347d2bacbf5c82a1a98787501";
const URL = (city) => `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${APIKEY}`;
const main = document.getElementById("main");
const form = document.getElementById("form");
const search = document.getElementById("search");



async function getWeatherByLocation(city){
    const resp = await fetch(URL(city));
    const respData = await resp.json();

    console.log(respData, KtoC(respData.main.temp));

    addWeatherToPage(respData);
}



function KtoC(K){
    return Math.floor(K - 273.15);
}

function addWeatherToPage(data){
    const temp = KtoC(data.main.temp);

    const weather = document.createElement('div');
    weather.classList.add('weather');
    weather.innerHTML = `<h2><img src="https://openweathermap.org/img/wn/${data.weather[0].icon}@2x.png" />
    ${temp}ºC
    <img src="https://openweathermap.org/img/wn/${data.weather[0].icon}@2x.png" /></h2>
    <small>${data.weather[0].main}</small>`;

    // cleanup

    main.innerHTML = '';
    main.appendChild(weather);
}

form.addEventListener('submit', (e) =>{
    e.preventDefault();
    const city = search.value;
    if(city){
        getWeatherByLocation(city);
    }
});