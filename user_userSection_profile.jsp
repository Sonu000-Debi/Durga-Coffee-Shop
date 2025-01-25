<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel = "styleSheet" href = "user_userSection_profile.css">
</head>
<body>
	
	<form action = "serv2">
		<%
		String name = request.getParameter("userName");
		Long phNo = Long.parseLong(request.getParameter("userPhNo"));
		String email = request.getParameter("userEmail");
		int pin = Integer.parseInt(request.getParameter("userPin"));
		String state = request.getParameter("userState");
		String district = request.getParameter("userDistrict");
		String city = request.getParameter("userCity");
		int roadNo = Integer.parseInt(request.getParameter("userRoadNo"));
		%>
		<label>NAME => </label>
		<input type = "text" name = "userName" value = "<%= name %>" readonly>
		<br><br><br>
		<label>PHONE NUMBER => </label>
		<input type = "number" name = "userPhone" value = "<%= phNo %>" readonly>
		<br><br><br>
		<label>EMAIL => </label>
		<input type = "email"  name = "userEmail" value = "<%= email %>" required>
		<br><br><br>
		<label>PIN NUMBER => </label>
		<input type = "number" name = "userPin" value = "<%= pin %>" required>
		<br><br><br>
		<label>STATE => </label>
		<input type = "text" name = "userState" value = "<%= state %>" required>
		<br><br><br>
		<label>DISTRICT => </label>
		<input type = "text" name = "userDist" value = "<%= district %>" required>
		<br><br><br>
		<label>CITY => </label>
		<input type = "text" name = "userCity" value = "<%= city %>" required>
		<br><br><br>
		<label>ROAD NUMBER => </label>
		<input type = "number" name = "userRoad" value = "<%= roadNo %>" required>
		<br><br><br>
		<input type ="reset" >&nbsp &nbsp &nbsp &nbsp<input type = "submit" value = "SAVE">
	</form>
</body>
</html>