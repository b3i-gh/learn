const quizData = [
    {
      question: "What is Mesopotamia?",
      a: "Mesopotamia is a region located in the eastern Mediterranean, which is now modern-day Iraq.",
      b: "Mesopotamia is a region located in the western Mediterranean, which is now modern-day Italy.",
      c: "Mesopotamia is a region located in the southern Mediterranean, which is now modern-day Egypt.",
      answer: "a"
    },
    {
      question: "What does the word 'Mesopotamia' mean?",
      a: "The word 'Mesopotamia' means 'land between rivers' in Greek.",
      b: "The word 'Mesopotamia' means 'land of the rising sun' in Greek.",
      c: "The word 'Mesopotamia' means 'land of the setting sun' in Greek.",
      answer: "a"
    },
    {
      question: "What were the two rivers that Mesopotamia was located between?",
      a: "Mesopotamia was located between the Tigris and Euphrates rivers.",
      b: "Mesopotamia was located between the Nile and Amazon rivers.",
      c: "Mesopotamia was located between the Danube and Rhine rivers.",
      answer: "a"
    },
    {
      question: "What was the first civilization in Mesopotamia?",
      a: "The Sumerians were the first civilization in Mesopotamia.",
      b: "The Babylonians were the first civilization in Mesopotamia.",
      c: "The Assyrians were the first civilization in Mesopotamia.",
      answer: "a"
    },
    {
      question: "What was the most important invention of the Sumerians?",
      a: "The most important invention of the Sumerians was writing.",
      b: "The most important invention of the Sumerians was the wheel.",
      c: "The most important invention of the Sumerians was the plow.",
      answer: "a"
    },
    {
      question: "What was the Code of Hammurabi?",
      a: "The Code of Hammurabi was a set of laws created by the Babylonian king Hammurabi in 1754 BCE.",
      b: "The Code of Hammurabi was a set of laws created by the Assyrian king Hammurabi in 1754 BCE.",
      c: "The Code of Hammurabi was a set of laws created by the Sumerian king Hammurabi in 1754 BCE.",
      answer: "a"
    },
    {
      question: "What was the Hanging Gardens of Babylon?",
      a: "The Hanging Gardens of Babylon were one of the Seven Wonders of the Ancient World, built by King Nebuchadnezzar II for his wife.",
      b: "The Hanging Gardens of Babylon were one of the Seven Wonders of the Ancient World, built by King Cyrus the Great for his wife.",
      c: "The Hanging Gardens of Babylon were one of the Seven Wonders of the Ancient World, built by King Darius the Great for his wife.",
      answer: "a"
    },
    {
      question: "What was the Babylonian Empire?",
      a: "The Babylonian Empire was an ancient empire that existed from 1894 BCE to 539 BCE.",
      b: "The Babylonian Empire was an ancient empire that existed from 1894 BCE to 539 CE.",
      c: "The Babylonian Empire was an ancient empire that existed from 1894 CE to 539 CE.",
      answer: "a"
    },
    {
      question: "What was the Assyrian Empire?",
      a: "The Assyrian Empire was an ancient empire that existed from 2500 BCE to 609 BCE.",
      b: "The Assyrian Empire was an ancient empire that existed from 2500 BCE to 609 CE.",
      c: "The Assyrian Empire was an ancient empire that existed from 2500 CE to 609 CE.",
      answer: "a"
    },
    {
      question: "What was the Persian Empire?",
      a: "The Persian Empire was an ancient empire that existed from 550 BCE to 330 BCE.",
      b: "The Persian Empire was an ancient empire that existed from 550 CE to 330 CE.",
      c: "The Persian Empire was an ancient empire that existed from 550 BCE to 330 CE.",
      answer: "a"
    }
  ];
  
const question = document.getElementById("question");
const a_text = document.getElementById("a_text");
const b_text = document.getElementById("b_text");
const c_text = document.getElementById("c_text");
const submitButton = document.getElementById("submitButton");

let score = 0;


let currentQuestion = 0;

  loadQuiz();

  
  function loadQuiz(){
    if(currentQuestion == quizData.length)
        alert(`Quiz done!\nYou gave ${score} correct answers out of ${quizData.length} totals`);
    else {

        question.innerText = quizData[currentQuestion].question;
        a_text.innerText = quizData[currentQuestion].a;
        b_text.innerText =quizData[currentQuestion].b;
        c_text.innerText = quizData[currentQuestion].c;
    }
  }

  submitButton.addEventListener("click", () => {
      var currAnswer = getSelected();
      console.log(currAnswer);
      
      if(currAnswer == quizData[currentQuestion].answer){
        alert("Correct Answer ✅");
        score++;
      } else 
        alert("Wrong Answer ❌");
    currentQuestion++;
    loadQuiz();
  });

  function getSelected(){
    const radioButtons = document.querySelectorAll("input");
    var currAnswer = undefined;
    radioButtons.forEach( button => {
        if(button.checked){
            currAnswer = button.id;
        }
    });
    return currAnswer;
  }