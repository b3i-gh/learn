const wCont = document.getElementById("weeks");
const dCont = document.getElementById("days");
const hCont = document.getElementById("hours");
const mCont = document.getElementById("minutes");
const sCont = document.getElementById("seconds");

const endingDate = new Date("1 Jan 2025");

setInterval(refreshTimer, 1000);

function refreshTimer(){
    let currDate = new Date();
    let seconds = (endingDate - currDate) / 1000;

    const weeks = Math.floor(seconds / 3600 / 24 / 7);
    const days = Math.floor(seconds / 3600 / 24) %7;
    const hours = Math.floor(seconds / 3600) %24;
    const minutes = Math.floor(seconds / 60) %60;
    wCont.textContent = weeks;
    dCont.textContent = days;
    hCont.textContent = String(hours).padStart(2, '0');
    mCont.textContent = String(minutes).padStart(2, '0');
    sCont.textContent = String(Math.floor(seconds) %60).padStart(2, '0');
}