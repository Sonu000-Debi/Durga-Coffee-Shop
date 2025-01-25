let homeDiv = document.querySelector("#homeDiv");
	let helpBtn = document.querySelector("#helpBtn");
		let helpDiv = document.querySelector("#helpDiv");
	
	let aboutBtn = document.querySelector("#aboutBtn");
		let aboutDiv = document.querySelector("#aboutDiv");
		
	let profileBtn = document.querySelector("#profileBtn");	
		let profileDiv = document.querySelector("#profileDiv");
			let backBtn = document.querySelector("#backBtn");
			
	let orderBtn = document.querySelector("#orderBtn");
		let cSelect = document.querySelector("#cSelectDiv");
		
		
	function onHelpBtnClick(helpBtn){
		helpBtn.addEventListener("click",function(){
			helpDiv.style.display = "block";
			aboutDiv.style.display = "none";
			profileDiv.style.display = "none";
		});
	}
	onHelpBtnClick(helpBtn);
	
	function onAboutBtnClick(aboutBtn){
		aboutBtn.addEventListener("click",function(){
			helpDiv.style.display = "none";
			aboutDiv.style.display = "block";
			profileDiv.style.display = "none";
		});
	}
	onAboutBtnClick(aboutBtn);
	
	function onprofileBtnClick(profileBtn){
		profileBtn.addEventListener("click",function(){
			profileDiv.style.display = "block";
			helpDiv.style.display = "none";
			aboutDiv.style.display = "none";
			homeDiv.style.display = "none";
			
		});
	}
	onprofileBtnClick(profileBtn);
	

	function onbackBtnClick(backBtn){
		backBtn.addEventListener("click",function(){
			helpDiv.style.display = "none";
			aboutDiv.style.display = "none";
			homeDiv.style.display = "block";
			profileDiv.style.display = "none";
		});
	}
	onbackBtnClick(backBtn);
	
	function onOrderBtnClick(orderBtn){
		orderBtn.addEventListener("click",function(){
			cSelect.style.display = "block";
			homeDiv.style.display = "none";
			aboutDiv.style.display = "none";
			helpDiv.style.display = "none";
		});
	}
	onOrderBtnClick(orderBtn);