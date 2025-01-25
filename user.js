let btnsDiv = document.querySelector("#btnsDiv");
	let logBtn = document.querySelector("#logBtn");
	let signBtn = document.querySelector("#signBtn");
let logDiv = document.querySelector("#logDiv");
let signUpDiv = document.querySelector("#signUpDiv");

function onlogBtnCliClick(logBtn){
	logBtn.addEventListener("click",function() {
		btnsDiv.style.display = "none";
		logDiv.style.display = "block";
		signUpDiv.style.display = "none";
	});
}
onlogBtnCliClick(logBtn);

function onsignBtnCliClick(signBtn){
	signBtn.addEventListener("click",function() {
		btnsDiv.style.display = "none";
		logDiv.style.display = "none";
		signUpDiv.style.display = "block";
	});
}
onsignBtnCliClick(signBtn);