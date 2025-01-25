<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>COFE ORDER</title>
		<link rel = "stylesheet" href = "cofeOrder.css">
	</head>
	<body>
	
		<%
			int pin = Integer.parseInt(request.getParameter("pin"));
			String name = request.getParameter("name");
			String cName = request.getParameter("cName");
			int cPrice = Integer.parseInt(request.getParameter("cPrice"));
			int cFnlPrice = cPrice ;
			Long userNumber = Long.parseLong(request.getParameter("userNumber"));
			String userAddress = request.getParameter("userAddress");
		%>
	<div id = "selectDiv">	
		<h3>SELECTED COFFEE => <%= cName %></h3>
		
		<h4>PRICE => <%= cPrice %></h4>
		
		<input type="radio" name="deliveryType" id = "stRadio" checked>
        STORE
        
        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  
        
        <input type="radio" name="deliveryType" id = "hmRadio">
        HOME DELIVERY
        <h6 id = "delCharge">Delivery charges 40/- RS</h6>
        <h3 id = "amountHeading">TOTAL PAYBLE AMOUNT => </h3>
        
        
		<button id = "pay" value = "cofePrice">PAY NOW</button>
		
		<a href = "user_userSection.jsp">CANCEL ORDER</a>
	</div>
	
	<div id = "paymentDiv">
		<img src="file:///D:/qrCd.jpg" alt="Unable to processed qr code ::  UPI ID - 9348924363@ybl">
		<form action = "serv4"> 
			<label>PHONE NUMBER =>> </label>
				PHONE NUMBER =>> <input type = "number" name = "number" value = <%= userNumber %> readOnly><br><br>
			<label>COFFEE NAME =>> </label>
				<input type = "text" name = "cofeName" value = <%= cName %> readOnly><br><br>
			<label>COFFEE PRICE =>> </label>
				<input type = "number" name = "cofePrice" value = <%= cFnlPrice %> readOnly>	
				
				<br><br><button id = "haveRerd" type="button">have reward point ?  optional</button>
				
				
			<label class = "rerd" id = "rerdLabel">USE REWARD POINT =>> </label><br>
				<input type="number" class = "rerd" id="rewardUse" name="rewardUse" placeholder="lower than available" value = "0"><br>
   				<button type="button" class = "rerd" id="rewardApply">APPLY</button><br>
   				
   				
   				
    		<label>TOTAL AMOUNT =>> </label>
				<input type = "number" name = "finalPrice" id = "payTotalPrice" value = <%= cPrice %> readOnly>
				<br><br>
			
			<input type="radio" name="deliveryType2" id = "stRadioOrder" checked>
       		 STORE DELIVERY
        
       		 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  
        
      		  <input type="radio" name="deliveryType2" id = "hmRadioOrder">
     		   HOME DELIVERY
				
				
				
			<br><br><label>UTR =>> </label>
				<input type = "text" placeHolder = "Enter The Utr" name = "utr" required><br><br>
			<label>BANK ACCOUNT HOLDER NAME =>> </label>
				<input type = "text" placeHolder = "Enter Name" name = "SenderName" required><br><br>
			<label>DELIVERY TYPE =>> </label>
				<input type="text"  id = "payDelType" value = "HOME" name = "delType" required readOnly><br><br>
			
			<input type = "hidden" name = "pin" value = "<%= pin %>">	
			<input type = "hidden" name = "userName" value = "<%= name %>">
			<input type = "hidden" name = "address" value = "<%= userAddress %>">	
			<input type = "hidden" name = "work" value = "userReqOrder">
			<!-- <input type="number" name = "rewardUser" value = "0" style="display: none;"> -->
			
			<button type = "submit">SUBMIT</button>
		</form>
		<br><br><a href = "user_userSection.jsp">BACK</a>
	</div>
		<script>
			let cofePrice = <%= cPrice %>;
		</script>

		<script src = "cofeOrder.js"></script>
	</body>
</html>