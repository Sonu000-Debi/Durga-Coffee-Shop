<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>USER SECTION</title>
		<link rel = "stylesheet" href = "user_userSection.css">
	</head>
	<body>
	<%@ page import="java.util.HashMap" %>
	<%
        		String userName = (String)session.getAttribute("userName");
				Long userPhoneNo = (Long)session.getAttribute("userPhNo");
				String userEmail = (String)session.getAttribute("userEmail");
				int userPin = (int)session.getAttribute("userPin");
				String userState = (String)session.getAttribute("userState");
				String userDistrict = (String)session.getAttribute("userDist");
				String userCity = (String)session.getAttribute("userCity");
				int userRoadNo = (int)session.getAttribute("userRoadNo");
				
				String address = "roadNo="+userRoadNo+",city="+userCity+",pin="+userPin+",dist="+userDistrict+",state="+userState ;
     %>
		<div id = "homeDiv">
			<button id = "profileBtn"></button><br><br><br><br>
			<button id = "orderBtn">ORDER</button>
			
			<form action = "serv5">
				<input type = "hidden" name = "userPhNo" value = "<%= userPhoneNo %>">
				<input type = "hidden" name = "work" value = "userReqReward">
				<button type ="submit">REWARDS</button><br><br><!--JS DIV SHOW  -->
			</form>
			
			<form  action = "serv5">
				<input type = "hidden" name = "userPhNo" value = "<%= userPhoneNo %>">
				<input type = "hidden" name = "work" value = "userReqHistory">
				<button type = "submit">HISTORY</button>
			</form><br>
			
			<button id = "helpBtn">HELP</button>
			<button id = "aboutBtn">ABOUT</button>
		</div>
		 
		<div id = "helpDiv" class = "divs">
			<h3>HELP</h3><br><br>
			<h4>CONTACT WITH OUR CUSTOMER CARE USING ::</h4>
			<h5><a href="https://wa.me/919348924363" target="_blank">WHATSAPP</a></h5>
			<h5><a href="https://m.me/dab_fb_000" target="_blank">FACEBOOK</a></h5>
			<h5><a href = "mailto:dabHelpLine333@gmail.com">MAIL</a> </h5>
		</div>
		<div id = "aboutDiv" class = "divs">
			<h3>ABOUT US </h3><br><br>
			<h4 id = "desc">
				Welcome to Durga Coffee Shop,<br><br>
				your cozy retreat in the heart of india. We pride ourselves on serving expertly crafted 
				coffees made from the finest beans sourced worldwide. Our menu features delicious 
				pastries, gourmet sandwiches, and wholesome salads, all made with the freshest 
				ingredients. Whether you're here for a quick pick-me-up or a leisurely break, our warm and 
				inviting atmosphere is the perfect place to relax. Join us for one of our community events 
				and become a part of our coffee-loving family. Come in, sit back, and enjoy the perfect blend
				of coffee, comfort, and conversation.
			</h4>		
		</div>
		<div id = "profileDiv"  class = "divs">
		
			<h3>PROFILE</h3><br>
			 
			<h5>NAME =><%= userName %></h5>
			
			<h5>PHONE NUMBER =><%= userPhoneNo %></h5>
			
			<h5>EMAIL => <%= userEmail %></h5>
			
			<h5>PIN => <%= userPin %></h5>
			
			<h5>STATE => <%= userState %></h5>
			
			<h5>DISTRICT => <%= userDistrict %></h5>
			
			<h5>CITY => <%= userCity %></h5>
			
			<h5>ROAD NUMBER => <%= userRoadNo %></h5>
			
			<form action = "user_userSection_profile.jsp">
				<input type = "hidden" name = "userName" value = "<%= userName %>">
				<input type = "hidden" name = "userPhNo" value = "<%= userPhoneNo %>">
				<input type = "hidden" name = "userEmail" value = "<%= userEmail %>">
				<input type = "hidden" name = "userPin" value = "<%= userPin %>">
				<input type = "hidden" name = "userState" value = "<%= userState %>">
				<input type = "hidden" name = "userDistrict" value = "<%= userDistrict %>">
				<input type = "hidden" name = "userCity" value = "<%= userCity %>">
				<input type = "hidden" name = "userRoadNo" value = "<%= userRoadNo %>">
				
				<button type = "submit">EDIT</button><br><br>
			</form>
			<button id = "backBtn">BACK</button>
		</div>
		<div class = "divs" id = "cSelectDiv">
			
		<%
			HashMap<String , Integer> priceCofe = (HashMap<String , Integer>)session.getAttribute("cNamePrice");
		
		
			if(priceCofe != null){
				for(String name : priceCofe.keySet()){
					out.println("<h3>NAME : "+name+"   <<==>>   PRICE : "+priceCofe.get(name)+"</h3>");
					out.println("<a href = \"cofeOrder.jsp?pin="+userPin+"&name="+userName+"&userAddress="+address+"&userNumber="+userPhoneNo+"&cName="+name+"&cPrice="+priceCofe.get(name)+"\">BUY NOW</a>");
				}
			}
			else {
				out.println("not found");
			}
		%>
		</div>
		
		
		<script src="user_userSection.js"></script>
	</body>
</html>