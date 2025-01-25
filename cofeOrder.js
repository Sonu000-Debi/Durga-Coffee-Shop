let delCharge = document.querySelector("#delCharge");
let storeRadio = document.getElementById('stRadio');  // Access by ID
let homeRadio = document.getElementById('hmRadio');  // Access by ID
let amountHeading = document.querySelector("#amountHeading");
let selectDiv = document.querySelector("#selectDiv");
let paymentDiv = document.querySelector("#paymentDiv");
let payBtn = document.querySelector("#pay");
let haveRerd = document.querySelector("#haveRerd");
let rerdClass = document.querySelector(".rerd");
let rewardApply = document.querySelector("#rewardApply");
let rewardUse = document.querySelector("#rewardUse");
let rerdLabel = document.querySelector("#rerdLabel");
let payTotalPrice = document.querySelector("#payTotalPrice");
let payDelType = document.querySelector("#payDelType");


function onHaveRerdBtnClick(haveRerd){
	haveRerd.addEventListener("click",function(){
		rerdLabel.style.display = "block";
		rewardUse.style.display = "block";
		rewardApply.style.display = "block";
		haveRerd.style.display = "none";
	});
}
onHaveRerdBtnClick(haveRerd);


let storeRadioOrder = document.getElementById('stRadioOrder');  // Access by ID
let homeRadioOrder = document.getElementById('hmRadioOrder');


function updateDeliveryPayOption() {
	  if (storeRadioOrder.checked) {
		  payTotalPrice.value = cofePrice;
		  payDelType.value = "STORE";
	    
	  } else if (homeRadioOrder.checked) {
		  payTotalPrice.value = (cofePrice + 40) ;
		  payDelType.value = "HOME";

	  }
	}

	// Attach event listeners to both radio buttons
	storeRadioOrder.addEventListener('change', updateDeliveryPayOption);
	homeRadioOrder.addEventListener('change', updateDeliveryPayOption);

	// Initialize based on the default selection
	updateDeliveryPayOption();






// let totalAmnt = document.querySelector("#totalAmnt");

// Function to update the UI based on selection
function updateDeliveryOption() {
  if (storeRadio.checked) {
    delCharge.style.display = "none"; // Hide delivery charges
    amountHeading.textContent = "TOTAL PAYBLE AMOUNT => "+ (cofePrice);
//    totalAmnt.textContent = (cofePrice);
    
  } else if (homeRadio.checked) {
    delCharge.style.display = "block"; // Show delivery charges
    amountHeading.textContent = "TOTAL PAYBLE AMOUNT => "+ (cofePrice+40);
//    totalAmnt.textContent = (cofePrice+40);
  }
}

// Attach event listeners to both radio buttons
storeRadio.addEventListener('change', updateDeliveryOption);
homeRadio.addEventListener('change', updateDeliveryOption);

// Initialize based on the default selection
updateDeliveryOption();

function onPayBtnClick(payBtn){
	payBtn.addEventListener("click",function(){
		selectDiv.style.display = "none";
		paymentDiv.style.display = "block"; 
	});
}
onPayBtnClick(payBtn);

document.addEventListener("DOMContentLoaded", function() {
    // Get the button element
    var rewardApply = document.querySelector("#rewardApply");
    
    // Add click event listener to the button
    rewardApply.addEventListener("click", function() {
        // Get the input field
        var inputField = document.querySelector("#rewardUse");
        
        // Make the input field read-only
        inputField.readOnly = true;
        
        // Change the button text to "APPLIED"
        rewardApply.innerHTML = "APPLIED";
        payTotalPrice.value = cofePrice - (rewardUse.value);
        cofePrice = parseInt(payTotalPrice.value);
    });
});






