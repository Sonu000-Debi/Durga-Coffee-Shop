let homeBtnsDiv = document.querySelector("#homeBtnsDiv");
let orderBtn = document.querySelector("#orderBtn");
let orderBtnDiv = document.querySelector("#orderBtnDiv");
let addDeleteBtn = document.querySelector("#addDeleteBtn");
let addDelBtnDiv = document.querySelector("#addDelBtnDiv");
let addCoffeeDiv = document.querySelector("#addCoffee"); 
let addBtn = document.querySelector("#addBtn");

// Function to reset visibility of all sections
function hideAllSections() {
    homeBtnsDiv.style.display = "none";
    orderBtnDiv.style.display = "none";
    addDelBtnDiv.style.display = "none";
    addCoffeeDiv.style.display = "none";
}

// Show COFFEE_ORDERS section
function onOrderBtnClick() {
    orderBtn.addEventListener("click", function () {
        hideAllSections(); // Hide all sections first
        orderBtnDiv.style.display = "block"; // Show only orders section
    });
}
onOrderBtnClick();

// Show COFFEE_ADD/DELETE section
function onaddDeleteBtnClick() {
    addDeleteBtn.addEventListener("click", function () {
        hideAllSections(); // Hide all sections first
        addDelBtnDiv.style.display = "block"; // Show only add/delete section
    });
}
onaddDeleteBtnClick();



function onaddBtnClick() {
	addBtn.addEventListener("click", function () {
        hideAllSections(); // Hide all sections first
        addCoffeeDiv.style.display = "block"; // Show only add/delete section
    });
}
onaddBtnClick();





// Optional: Return to home screen
document.addEventListener("keydown", function (event) {
    if (event.key === "Escape") { // Press "Esc" key to go back
        hideAllSections();
        homeBtnsDiv.style.display = "block";
    }
});
