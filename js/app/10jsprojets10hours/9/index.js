const pwEl = document.getElementById("pw");
const copyEl = document.getElementById("copy");
const lenEl = document.getElementById("len");
const upperEl = document.getElementById("upper");
const lowerEl = document.getElementById("lower");
const numberEl = document.getElementById("number");
const symbolEl = document.getElementById("symbol");
const generateEl = document.getElementById("generate");

const upperLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
const lowerLetters = "abcdefghijklmnopqrstuvwxyz";
const numbers = "0123456789";
const symbols = "!@#$%^&*()_+=";


function getLowercase() {
    return lowerLetters[Math.floor(Math.random() * lowerLetters.length)];
}

function getUppercase() {
    return upperLetters[Math.floor(Math.random() * upperLetters.length)];
}

function getNumber() {
    return numbers[Math.floor(Math.random() * numbers.length)];
}

function getSymbol() {
    return symbols[Math.floor(Math.random() * symbols.length)];
}

function generatePassword() {
    let allChars = '';
    if (upperEl.checked) {
        allChars += upperLetters;
    }
    if (lowerEl.checked) {
        allChars += lowerLetters;
    }
    if (numberEl.checked) {
        allChars += numbers;
    }
    if (symbolEl.checked) {
        allChars += symbols;
    }

    const len = lenEl.value;

    let password = '';
    if (allChars.length == 0) {
        alert("Select at least one set of characters");
    } else {
        for (let i = 0; i < len; i++) {
            password += allChars[Math.floor(Math.random() * allChars.length)];
        }
    }
    pwEl.innerText = password;
}

function copyToClipBoard() {
    const currPw = pwEl.innerText;
    if(!currPw)
        return;

    const ta = document.createElement('textarea');
    ta.value = currPw;
    document.body.appendChild(ta);
    ta.select();
    document.execCommand('copy');
    ta.remove();
    alert('Password copied to clipboard');
}

generateEl.addEventListener("click", generatePassword);
copyEl.addEventListener("click", copyToClipBoard);