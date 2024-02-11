const canvas = document.getElementById('canvas');
const decrease = document.getElementById('decrease');
const increase = document.getElementById('increase');
const sizeEl = document.getElementById('size');
const colorEl = document.getElementById('color');
const clearEl = document.getElementById('clear');

const ctx = canvas.getContext('2d');

let size = 30;
let isPressed = false;
let color = 'black';
let x = undefined;
let y = undefined;

canvas.addEventListener('mousedown', (e) =>{
    isPressed = true;
    x = e.offsetX;
    y = e.offsetY;
});

canvas.addEventListener('mouseup', (e) =>{
    isPressed = false;
    x = undefined;
    y = undefined;
});

canvas.addEventListener('mousemove', (e) =>{
    if(isPressed){
     const x2 = e.offsetX;
     const y2 = e.offsetY;
     drawCircle(x2,y2);
     drawLine(x,y, x2, y2);
     x = x2;
     y = y2;
    }
});

canvas.addEventListener('mousedown', (e) =>{
    isPressed = true;
});

decrease.addEventListener('click', (e) =>{
    if(size>3)
        size-=3;
        updateSizeOnScreen();
});

increase.addEventListener('click', (e) =>{
    size+=3;
    updateSizeOnScreen();
});

function updateSizeOnScreen(){
    sizeEl.innerText = size;
}

function drawCircle(x,y){
    ctx.beginPath();
    ctx.arc(x,y,size,0,2*Math.PI);
    ctx.fillStyle = color;
    ctx.fill();
}

colorEl.addEventListener('change', (e) =>{
    color = e.target.value;
});

function drawLine(x1,y1,x2,y2){
    ctx.beginPath();
    ctx.moveTo(x1,y1);
    ctx.lineTo(x2,y2);
    ctx.strokeStyle = color;
    ctx.lineWidth = size*2;
    ctx.stroke();
}

clearEl.addEventListener('click', () =>{
    ctx.clearRect(0,0,canvas.width,canvas.height);
});