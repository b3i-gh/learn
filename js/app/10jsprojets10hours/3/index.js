// search
// add to favorite
var mealData = undefined;

const favBtn = document.getElementById("favBtn");

favBtn.addEventListener("click", () => {
    if(favBtn.classList.contains("favved")){
        console.log(mealData);
        removeMealFromLS(mealData.idMeal);
    }
    else {
        addMealToLS(mealData.idMeal);
    }

    favBtn.classList.toggle("favved");
});

showRandomMeal();


async function showRandomMeal(){
    const meal = await getRandomMeal();
    mealData = meal;
    const mealTitle = meal.strMeal;
    const mealImg = meal.strMealThumb;

    document.getElementById("random-recipe-img").src=mealImg;
    document.getElementById("random-recipe-img").alt=mealTitle;
    document.getElementById("meal-title").innerText=mealTitle;
}



async function getRandomMeal(){
    const resp = await fetch('https://www.themealdb.com/api/json/v1/1/random.php');
    const respData = await resp.json();
    const meal = respData.meals[0];
    return meal;
}

async function getMeal(id){
    const resp  = await fetch('https://www.themealdb.com/api/json/v1/1/lookup.php?i='+id);
    const respData = await resp.json();
    const meal = respData.meals[0];
}

async function getBySearch(q){
    const meal = await fetch('https://www.themealdb.com/api/json/v1/1/search.php?s='+q);
}

function addMealToLS(mealId){
    const mealIds = getMealsFromLS();
    localStorage.setItem('mealIds', JSON.stringify([...mealIds, mealId]));
}

function removeMealFromLS(mealId){
    const mealIds = getMealsFromLS();
    localStorage.setItem('mealIds', JSON.stringify(
        mealIds.filter(id => id !== mealId)));
}

function getMealsFromLS(){
    const mealIds = JSON.parse(localStorage.getItem('mealIds'));
    return mealIds;
}