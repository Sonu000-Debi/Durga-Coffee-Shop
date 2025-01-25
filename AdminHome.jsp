<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>ADMIN WORK</title>
		 <link rel="stylesheet" href="AdminHome.css">
	</head>
	
	<body>
	
		<div id = "homeBtnsDiv">
			<button id = "orderBtn">COFFEE_ORDERS</button>
			<form action = "serv6">
				<button type = "submit" id = "pricingBtn" name = "work" value = "cofePricing">COFFEE_PRICING</button>
			</form>
			<button id = "addDeleteBtn">COFFEE_ADD/DELETE</button>
			<form action = "serv6">
				<input type = "hidden" name = "work" value = "stockInquiry">
				<button type = "submit" id = "stocksBtn">COFFEE_STOCKS</button>
			</form>
		</div>
		
		<div id = "orderBtnDiv">
			<form action = "serv5">
				<button type = "submit" name = "work" value = "pendingsOrder">PENDINGS_ORDERS</button>
				<button type = "submit" name = "work" value = "historyOrderByAdmin">ORDER_HISTORY</button>
				<!-- <input type = "hidden" value = "admin"> -->
			</form>
		</div>
		

		<div id="addDelBtnDiv">
     		   <button type="button" id = "addBtn">ADD NEW COFFEE</button>
   			 <form action="serv6">
    		    <button type="submit" id = "delete" name="work" value="deleteCoffee">DELETE COFFEE</button>
   			 </form>
		</div>
		
		<div id = "addCoffee">
			<a href = "AdminHome.jsp">BACK</a>
			<form action = "serv6">
				<label>COFFEE NAME >></label>
				<input type = "text" name = "newCofeAddName" placeHolder = "enter coffee name" required>
				<label>COFFEE PRICE >></label>
				<input type = "number" name = "newCofeAddPrice" placeHolder = "enter coffee price" required>
				<label>COFFEE STOCKS >></label>
				<input type = "number" name = "newCofeAddStock" placeHolder = "coffee stock amount" required>
				<input type = "hidden" name = "work" value = "newCofeAdd" >
				<input type = "submit" value = "UPDATE">
			</form>
		</div>

		
		<script src = "AdminHome.js"></script>
		
	</body>
</html>