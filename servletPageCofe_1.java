package cofeShopDemo.packg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class servletPageCofe_1 extends HttpServlet{
	
	static String dbname = "root";
	static String dbPwd = "12345678";
	static String dbUrl = "jdbc:mysql://localhost:3306/coffeeshopDemo?useSSL=false";
	
	static String finalName = null ;
	static long finalPhNo = 0;
	static String finalEmail = "NOT SAVED";
	static int finalPinNo = 0;
	static String finalState = "NOT SAVED";
	static String finalDistrict = "NOT SAVED";
	static String finalCity = "NOT SAVED";
	static int finalRoadNo = 0;
	
	HashMap<String , Integer> cofePrice = new HashMap<>();

	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req , HttpServletResponse res) throws IOException {
		
		String name = "";
		Long phNo ;
		String password = "";
		String confirmPassword = "";

		name = req.getParameter("name");
		phNo = Long.parseLong(req.getParameter("number"));
		password = req.getParameter("password");
		confirmPassword = req.getParameter("passwordConfirm");
		String work =  req.getParameter("work");
		
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		
		if(work.equals("register")) {
		
			if(password.equals(confirmPassword)) {
			
				String workDone = doRegister(phNo ,name ,password);
						
				if(workDone.equals("signUpsucces")) {out.println("<html><head><style>");

				// Body styling with modern background and center alignment
				out.println("body {");
				out.println("  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;");
				out.println("  background-color: #f0f4f8;");
				out.println("  display: flex;");
				out.println("  flex-direction: column;");  // Ensures elements stack vertically
				out.println("  justify-content: center;");
				out.println("  align-items: center;");
				out.println("  height: 100vh;");
				out.println("  margin: 0;");
				out.println("  text-align: center;");
				out.println("  animation: fadeIn 1.5s ease-in-out;");
				out.println("}");

				// Heading styling with modern green and smooth animation
				out.println("h2 {");
				out.println("  color: #28a745;");
				out.println("  font-size: 30px;");
				out.println("  font-weight: 700;");
				out.println("  margin-bottom: 20px;");
				out.println("  text-transform: uppercase;");
				out.println("  animation: slideUp 1s ease-out;");
				out.println("}");

				// Button styling with modern rounded look, hover effect and smooth fade-in
				out.println("a {");
				out.println("  color: #fff;");
				out.println("  background-color: #28a745;");
				out.println("  font-size: 18px;");
				out.println("  text-decoration: none;");
				out.println("  padding: 15px 30px;");
				out.println("  border-radius: 50px;");
				out.println("  border: 1px solid #28a745;");
				out.println("  transition: all 0.3s ease;");
				out.println("  display: inline-block;");
				out.println("  margin-top: 20px;");
				out.println("  animation: buttonFadeIn 1s ease-in-out;");
				out.println("}");

				// Button hover effect: Slight color change and scaling for emphasis
				out.println("a:hover {");
				out.println("  background-color: #218838;");
				out.println("  transform: scale(1.05);");
				out.println("}");

				// Keyframes for smooth fade-in animation
				out.println("@keyframes fadeIn {");
				out.println("  0% { opacity: 0; }");
				out.println("  100% { opacity: 1; }");
				out.println("}");

				// Keyframes for sliding up animation for the heading
				out.println("@keyframes slideUp {");
				out.println("  0% { transform: translateY(20px); opacity: 0; }");
				out.println("  100% { transform: translateY(0); opacity: 1; }");
				out.println("}");

				// Keyframes for button fade-in animation
				out.println("@keyframes buttonFadeIn {");
				out.println("  0% { opacity: 0; }");
				out.println("  100% { opacity: 1; }");
				out.println("}");

				// Closing HTML tags
				out.println("</style></head><body>");
				out.println("<h2>REGISTRATION SUCCESSFUL</h2>");
				out.println("<a href='user.html'>LOGIN HERE</a>");
				out.println("</body></html>");
				}


				else if(workDone.equals("signUpduplicate")) {out.println("<html><head><style>");

				// Body styling with a clean background and centered layout
				out.println("body {");
				out.println("  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;");
				out.println("  background-color: #f7f7f7;");
				out.println("  text-align: center;");
				out.println("  padding: 50px;");
				out.println("  margin: 0;");
				out.println("  display: flex;");
				out.println("  flex-direction: column;");  // Ensures elements stack vertically
				out.println("  justify-content: center;");
				out.println("  align-items: center;");
				out.println("  height: 100vh;");
				out.println("  animation: fadeIn 1.5s ease-in-out;");
				out.println("}");

				// Heading styling with more modern color and animation
				out.println("h2 {");
				out.println("  color: #e74c3c;");
				out.println("  font-size: 32px;");
				out.println("  font-weight: 700;");
				out.println("  margin-bottom: 30px;");
				out.println("  text-transform: uppercase;");
				out.println("  letter-spacing: 2px;");
				out.println("  animation: slideUp 1s ease-out;");
				out.println("}");

				// Paragraph styling with readability and a cleaner look
				out.println("p {");
				out.println("  color: #333;");
				out.println("  font-size: 20px;");
				out.println("  margin-bottom: 40px;");
				out.println("  animation: fadeInText 1.5s ease-in-out;");
				out.println("}");

				// Button styling with a more modern approach and hover effects
				out.println("a {");
				out.println("  color: #fff;");
				out.println("  background-color: #e74c3c;");
				out.println("  font-size: 18px;");
				out.println("  text-decoration: none;");
				out.println("  padding: 15px 30px;");
				out.println("  border-radius: 50px;");
				out.println("  border: 1px solid #e74c3c;");
				out.println("  transition: all 0.3s ease;");
				out.println("  display: inline-block;");
				out.println("  animation: buttonFadeIn 1s ease-in-out;");
				out.println("}");

				// Hover effect for the button: color change and scale animation
				out.println("a:hover {");
				out.println("  background-color: #c0392b;");
				out.println("  transform: scale(1.05);");
				out.println("}");

				// Keyframes for fadeIn animation
				out.println("@keyframes fadeIn {");
				out.println("  0% { opacity: 0; }");
				out.println("  100% { opacity: 1; }");
				out.println("}");

				// Keyframes for sliding up the heading with smooth transition
				out.println("@keyframes slideUp {");
				out.println("  0% { transform: translateY(30px); opacity: 0; }");
				out.println("  100% { transform: translateY(0); opacity: 1; }");
				out.println("}");

				// Keyframes for fade-in text effect
				out.println("@keyframes fadeInText {");
				out.println("  0% { opacity: 0; }");
				out.println("  100% { opacity: 1; }");
				out.println("}");

				// Keyframes for button fade-in effect
				out.println("@keyframes buttonFadeIn {");
				out.println("  0% { opacity: 0; }");
				out.println("  100% { opacity: 1; }");
				out.println("}");

				// Closing HTML tags
				out.println("</style></head><body>");
				out.println("<h2>Duplicate Registration!</h2>");
				out.println("<p>This phone number is already registered. Please log in or use a different number.</p>");
				out.println("<a href='user.html'>Login Here</a>");
				out.println("</body></html>");
				}


				
			}
			else {out.println("<html><head><style>");

			// Body Styling with a clean background and central alignment
			out.println("body {");
			out.println("  font-family: 'Roboto', sans-serif;");
			out.println("  background-color: #e8f5e9;");
			out.println("  text-align: center;");
			out.println("  padding: 40px;");
			out.println("  margin: 0;");
			out.println("  display: flex;");
			out.println("  flex-direction: column;"); // Ensures elements stack vertically
			out.println("  justify-content: center;");
			out.println("  align-items: center;");
			out.println("  height: 100vh;");
			out.println("  animation: fadeIn 1.5s ease-in-out;");
			out.println("}");

			// Heading (Error Message) Styling with modern look
			out.println("h2 {");
			out.println("  color: #c62828;");
			out.println("  font-size: 32px;");
			out.println("  font-weight: 600;");
			out.println("  margin-bottom: 30px;");
			out.println("  text-transform: uppercase;");
			out.println("  animation: slideUp 1s ease-out;");
			out.println("}");

			// Paragraph Styling with better readability and animation
			out.println("p {");
			out.println("  color: #333;");
			out.println("  font-size: 18px;");
			out.println("  margin-bottom: 40px;");
			out.println("  animation: fadeInText 1.5s ease-in-out;");
			out.println("}");

			// Link/Button Styling with improved hover effect and animation
			out.println("a {");
			out.println("  color: #fff;");
			out.println("  background-color: #c62828;");
			out.println("  font-size: 18px;");
			out.println("  text-decoration: none;");
			out.println("  padding: 12px 25px;");
			out.println("  border-radius: 25px;");
			out.println("  border: 1px solid #c62828;");
			out.println("  transition: all 0.3s ease;");
			out.println("  display: inline-block;");
			out.println("  animation: buttonFadeIn 1s ease-in-out;");
			out.println("}");

			// Button hover effect for a smoother experience
			out.println("a:hover {");
			out.println("  background-color: #b71c1c;");
			out.println("  color: #fff;");
			out.println("  transform: scale(1.05);");
			out.println("}");

			// Keyframes for fadeIn animation
			out.println("@keyframes fadeIn {");
			out.println("  0% { opacity: 0; }");
			out.println("  100% { opacity: 1; }");
			out.println("}");

			// Keyframes for sliding up animation on the heading
			out.println("@keyframes slideUp {");
			out.println("  0% { transform: translateY(20px); opacity: 0; }");
			out.println("  100% { transform: translateY(0); opacity: 1; }");
			out.println("}");

			// Keyframes for fade-in text animation
			out.println("@keyframes fadeInText {");
			out.println("  0% { opacity: 0; }");
			out.println("  100% { opacity: 1; }");
			out.println("}");

			// Keyframes for button fade-in effect
			out.println("@keyframes buttonFadeIn {");
			out.println("  0% { opacity: 0; }");
			out.println("  100% { opacity: 1; }");
			out.println("}");

			out.println("</style></head><body>");
			out.println("<h2>Both Passwords Should Be The Same</h2>");
			out.println("<a href='user.html'>Register Again</a>");
			out.println("</body></html>");
			}



		}
		else if(work.equals("login")) {
		    String workDone = doLogin(phNo ,password);
		    
		    if(workDone.equals("logInsuccess")) {HttpSession sesObj = req.getSession();

		 // Set session attributes
		 sesObj.setAttribute("userName", finalName);
		 sesObj.setAttribute("userPhNo", finalPhNo);
		 sesObj.setAttribute("userEmail", finalEmail);
		 sesObj.setAttribute("userPin", finalPinNo);
		 sesObj.setAttribute("userState", finalState);
		 sesObj.setAttribute("userDist", finalDistrict);
		 sesObj.setAttribute("userCity", finalCity);
		 sesObj.setAttribute("userRoadNo", finalRoadNo);
		 sesObj.setAttribute("cNamePrice", cofePrice);

		 // Output response with enhanced CSS
		 out.println("<html><head><style>");

		 // Body Styling with smooth fade-in animation and clean layout
		 out.println("body {");
		 out.println("  font-family: 'Roboto', sans-serif;");
		 out.println("  background-color: #e8f5e9;");
		 out.println("  text-align: center;");
		 out.println("  padding: 50px;");
		 out.println("  margin: 0;");
		 out.println("  display: flex;");
		 out.println("  flex-direction: column;"); // Ensures elements stack vertically
		 out.println("  justify-content: center;");
		 out.println("  align-items: center;");
		 out.println("  height: 100vh;");
		 out.println("  animation: fadeIn 1.5s ease-in-out;");
		 out.println("}");

		 // Heading Styling with a professional look and animation
		 out.println("h2 {");
		 out.println("  color: #388e3c;");
		 out.println("  font-size: 32px;");
		 out.println("  font-weight: 700;");
		 out.println("  margin-bottom: 20px;"); // Adjusted to decrease space between heading and text
		 out.println("  text-transform: uppercase;");
		 out.println("  animation: slideUp 1s ease-out;");
		 out.println("}");

		 // Paragraph Styling with soft color and better readability
		 out.println("p {");
		 out.println("  color: #333;");
		 out.println("  font-size: 18px;");
		 out.println("  margin-bottom: 30px;"); // Adjusted to decrease space between paragraph and link
		 out.println("  animation: fadeInText 1.5s ease-in-out;");
		 out.println("}");

		 // Link/Button Styling with hover effects and animation
		 out.println("a {");
		 out.println("  color: #fff;");
		 out.println("  background-color: #388e3c;");
		 out.println("  font-size: 18px;");
		 out.println("  text-decoration: none;");
		 out.println("  padding: 12px 25px;");
		 out.println("  border-radius: 25px;");
		 out.println("  border: 1px solid #388e3c;");
		 out.println("  transition: all 0.3s ease;");
		 out.println("  display: inline-block;");
		 out.println("  animation: buttonFadeIn 1s ease-in-out;");
		 out.println("}");

		 // Button hover effect with color change and scaling
		 out.println("a:hover {");
		 out.println("  background-color: #2e7d32;");
		 out.println("  color: #fff;");
		 out.println("  transform: scale(1.1);");
		 out.println("}");

		 // Keyframe Animations for smooth transitions
		 out.println("@keyframes fadeIn {");
		 out.println("  0% { opacity: 0; }");
		 out.println("  100% { opacity: 1; }");
		 out.println("}");

		 out.println("@keyframes slideUp {");
		 out.println("  0% { transform: translateY(30px); opacity: 0; }");
		 out.println("  100% { transform: translateY(0); opacity: 1; }");
		 out.println("}");

		 out.println("@keyframes fadeInText {");
		 out.println("  0% { opacity: 0; }");
		 out.println("  100% { opacity: 1; }");
		 out.println("}");

		 out.println("@keyframes buttonFadeIn {");
		 out.println("  0% { opacity: 0; }");
		 out.println("  100% { opacity: 1; }");
		 out.println("}");

		 out.println("</style></head><body>");
		 out.println("<h2>Login Successful</h2>");
		 out.println("<p>You have logged in successfully.</p>");
		 out.println("<a href='user_userSection.jsp'>OK</a>");
		 out.println("</body></html>");
		    }


		    else if(workDone.equals("logInNotsuccess")) {// Output response for login failure
		    	out.println("<html><head><style>");

		    	// Body styling with a smooth fade-in effect and clean layout
		    	out.println("body {");
		    	out.println("  font-family: 'Roboto', sans-serif;");
		    	out.println("  background-color: #f8d7da;");
		    	out.println("  text-align: center;");
		    	out.println("  padding: 50px;");
		    	out.println("  margin: 0;");
		    	out.println("  display: flex;");
		    	out.println("  flex-direction: column;"); // Ensures elements stack vertically
		    	out.println("  justify-content: center;");
		    	out.println("  align-items: center;");
		    	out.println("  height: 100vh;");
		    	out.println("  animation: fadeIn 1.5s ease-in-out;");
		    	out.println("}");

		    	// Heading styling with a strong emphasis on the error message
		    	out.println("h2 {");
		    	out.println("  color: #d32f2f;");
		    	out.println("  font-size: 32px;");
		    	out.println("  font-weight: 700;");
		    	out.println("  margin-bottom: 20px;"); // Adjusted to decrease space between heading and text
		    	out.println("  text-transform: uppercase;");
		    	out.println("  animation: slideUp 1s ease-out;");
		    	out.println("}");

		    	// Paragraph styling with clear error message visibility
		    	out.println("p {");
		    	out.println("  color: #c62828;");
		    	out.println("  font-size: 18px;");
		    	out.println("  margin-bottom: 30px;"); // Adjusted to decrease space between paragraph and link
		    	out.println("  animation: fadeInText 1.5s ease-in-out;");
		    	out.println("}");

		    	// Link/Button styling with hover effect and smooth animation
		    	out.println("a {");
		    	out.println("  color: #fff;");
		    	out.println("  background-color: #d32f2f;");
		    	out.println("  font-size: 18px;");
		    	out.println("  text-decoration: none;");
		    	out.println("  padding: 12px 25px;");
		    	out.println("  border-radius: 25px;");
		    	out.println("  border: 1px solid #d32f2f;");
		    	out.println("  transition: all 0.3s ease;");
		    	out.println("  display: inline-block;");
		    	out.println("  animation: buttonFadeIn 1s ease-in-out;");
		    	out.println("}");

		    	// Hover effect for the button with a color change and scaling effect
		    	out.println("a:hover {");
		    	out.println("  background-color: #b71c1c;");
		    	out.println("  color: #fff;");
		    	out.println("  transform: scale(1.1);");
		    	out.println("}");

		    	// Keyframe animations for smooth transitions
		    	out.println("@keyframes fadeIn {");
		    	out.println("  0% { opacity: 0; }");
		    	out.println("  100% { opacity: 1; }");
		    	out.println("}");

		    	out.println("@keyframes slideUp {");
		    	out.println("  0% { transform: translateY(30px); opacity: 0; }");
		    	out.println("  100% { transform: translateY(0); opacity: 1; }");
		    	out.println("}");

		    	out.println("@keyframes fadeInText {");
		    	out.println("  0% { opacity: 0; }");
		    	out.println("  100% { opacity: 1; }");
		    	out.println("}");

		    	out.println("@keyframes buttonFadeIn {");
		    	out.println("  0% { opacity: 0; }");
		    	out.println("  100% { opacity: 1; }");
		    	out.println("}");

		    	out.println("</style></head><body>");
		    	out.println("<h2>LOGIN FAILED</h2>");
		    	out.println("<p>The entered credentials do not match. Please try again.</p>");
		    	out.println("<a href='user.html'>LOGIN AGAIN</a>");
		    	out.println("</body></html>");
		    }

		    else {
		        out.println(workDone);
		    }
		}

		
		else {
			if((phNo == 11223344) && (password.equals("sonu"))) {// Login success
				out.println("<html><head><style>");

				// Body styling with a calm, clean background, centered content, and fade-in animation
				out.println("body {");
				out.println("  font-family: 'Roboto', sans-serif;");
				out.println("  background-color: #e8f5e9;");
				out.println("  text-align: center;");
				out.println("  padding: 50px;");
				out.println("  margin: 0;");
				out.println("  display: flex;");
				out.println("  flex-direction: column;"); // Ensures elements stack vertically
				out.println("  justify-content: center;");
				out.println("  align-items: center;");
				out.println("  height: 100vh;");
				out.println("  animation: fadeIn 1.5s ease-in-out;");
				out.println("}");

				// Heading styling with a strong green color and smooth slide-up animation
				out.println("h2 {");
				out.println("  color: #388e3c;");
				out.println("  font-size: 32px;");
				out.println("  font-weight: 700;");
				out.println("  margin-bottom: 20px;");
				out.println("  text-transform: uppercase;");
				out.println("  animation: slideUp 1s ease-out;");
				out.println("}");

				// Confirmation message styling with a softer green color
				out.println("p {");
				out.println("  color: #66bb6a;");
				out.println("  font-size: 20px;");
				out.println("  margin-bottom: 30px;");
				out.println("  animation: fadeInText 1.5s ease-in-out;");
				out.println("}");

				// 'OK' button styling with smooth animation and hover effects
				out.println("a {");
				out.println("  color: #fff;");
				out.println("  background-color: #388e3c;");
				out.println("  font-size: 18px;");
				out.println("  text-decoration: none;");
				out.println("  padding: 15px 30px;");
				out.println("  border-radius: 30px;");
				out.println("  border: 1px solid #388e3c;");
				out.println("  transition: all 0.3s ease;");
				out.println("  display: inline-block;");
				out.println("  animation: buttonFadeIn 1s ease-in-out;");
				out.println("}");

				// Hover effect for the button to make it slightly bigger and change the color
				out.println("a:hover {");
				out.println("  background-color: #2c6e2f;");
				out.println("  transform: scale(1.1);");
				out.println("}");

				// Keyframe animations for smooth fade-ins and slide-up effects
				out.println("@keyframes fadeIn {");
				out.println("  0% { opacity: 0; }");
				out.println("  100% { opacity: 1; }");
				out.println("}");

				out.println("@keyframes slideUp {");
				out.println("  0% { transform: translateY(30px); opacity: 0; }");
				out.println("  100% { transform: translateY(0); opacity: 1; }");
				out.println("}");

				out.println("@keyframes fadeInText {");
				out.println("  0% { opacity: 0; }");
				out.println("  100% { opacity: 1; }");
				out.println("}");

				out.println("@keyframes buttonFadeIn {");
				out.println("  0% { opacity: 0; }");
				out.println("  100% { opacity: 1; }");
				out.println("}");

				out.println("</style></head><body>");
				out.println("<h2>LOGIN SUCCESSFUL</h2>");
				out.println("<p>You have successfully logged in.</p>");
				out.println("<a href='AdminHome.jsp'>OK</a>");
				out.println("</body></html>");
			}

			else {// Login failure
				out.println("<html><head><style>");

				// Body styling with background, centered content, and fade-in animation
				out.println("body {");
				out.println("  font-family: 'Roboto', sans-serif;");
				out.println("  background-color: #ffebee;");
				out.println("  text-align: center;");
				out.println("  padding: 50px;");
				out.println("  margin: 0;");
				out.println("  display: flex;");
				out.println("  flex-direction: column;"); // Ensures elements stack vertically
				out.println("  justify-content: center;");
				out.println("  align-items: center;");
				out.println("  height: 100vh;");
				out.println("  animation: fadeIn 1.5s ease-in-out;");
				out.println("}");

				// Error message styling with a bold red color and smooth animation
				out.println("p {");
				out.println("  color: #d32f2f;");
				out.println("  font-size: 20px;");
				out.println("  font-weight: bold;");
				out.println("  margin-bottom: 30px;");
				out.println("  animation: fadeInText 1.5s ease-in-out;");
				out.println("}");

				// 'Login Again' button styling with a hover effect, smooth transition, and animation
				out.println("a {");
				out.println("  color: #fff;");
				out.println("  background-color: #d32f2f;");
				out.println("  font-size: 18px;");
				out.println("  text-decoration: none;");
				out.println("  padding: 15px 30px;");
				out.println("  border-radius: 30px;");
				out.println("  border: 1px solid #d32f2f;");
				out.println("  transition: all 0.3s ease;");
				out.println("  display: inline-block;");
				out.println("  animation: buttonFadeIn 1s ease-in-out;");
				out.println("}");

				// Hover effect for the 'Login Again' button to give dynamic feedback
				out.println("a:hover {");
				out.println("  background-color: #c62828;");
				out.println("  transform: scale(1.1);");
				out.println("}");

				// Keyframe animations for fading in and button effects
				out.println("@keyframes fadeIn {");
				out.println("  0% { opacity: 0; }");
				out.println("  100% { opacity: 1; }");
				out.println("}");

				out.println("@keyframes fadeInText {");
				out.println("  0% { opacity: 0; }");
				out.println("  100% { opacity: 1; }");
				out.println("}");

				out.println("@keyframes buttonFadeIn {");
				out.println("  0% { opacity: 0; }");
				out.println("  100% { opacity: 1; }");
				out.println("}");

				out.println("</style></head><body>");
				out.println("<p>The entered credentials do not match. Please try again.</p>");
				out.println("<a href='HomePage.html'>LOGIN AGAIN</a>");
				out.println("</body></html>");
			}

		}

		
	}	

public String doRegister(Long phNo , String name , String password) {
	Connection con = null;
	PreparedStatement pstmnt = null;
	Statement stmnt = null;
	ResultSet rslt = null;
	
	boolean flagR = false ;
	String workDone = "unknown";
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		con = DriverManager.getConnection(dbUrl , dbname , dbPwd);
		
		String sqlOutQur = "select * from idpwdtableDemo";
		
		stmnt = con.createStatement();
		rslt = stmnt.executeQuery(sqlOutQur);
		
		while (rslt.next()) {
			Long checkNo = rslt.getLong("phoneNo");
			if(checkNo.equals(phNo)) {
				flagR = true;
				break;
			}
		}
		if(flagR == false) {
			
			String sqlInsQur = "insert into idpwdtableDemo (phoneNo , name , password) values (? , ? , ?)";
			pstmnt = con.prepareStatement(sqlInsQur);
			pstmnt.setLong(1, phNo);
			pstmnt.setString(2, name);
			pstmnt.setString(3, password);
			pstmnt.executeUpdate();
			
			workDone = "signUpsucces";
			System.out.println(workDone);
			
		}
		else {
			workDone = "signUpduplicate";
		}
		
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		try {
			if (pstmnt != null) pstmnt.close();
			if (stmnt != null) stmnt.close();
			if (rslt != null) rslt.close();
			if (con != null) con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	return workDone ;
}
	
	

	public String doLogin(Long phNo , String password) {
		Connection con = null;
		Statement stmnt = null;
		ResultSet rslt = null;
		ResultSet rsltDet = null;
		ResultSet rsltCPrice = null;
	
		String workDone = "unknown";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection(dbUrl , dbname , dbPwd);
			
			String sqlOutQur = "select * from idpwdtableDemo";
			
			stmnt = con.createStatement();
			rslt = stmnt.executeQuery(sqlOutQur);
			
			
			while (rslt.next()) {
				Long checkNo = rslt.getLong("phoneNo");
				String checkName = rslt.getString("name");
				String checkPassword = rslt.getString("password");
				
				if (checkNo.equals(phNo) && checkPassword.equals(password)) {
					finalName = checkName;
					finalPhNo = checkNo;
					workDone = "logInsuccess";
					
					String details = "select * from userDetailsDemo where phoneNo = "+ checkNo;
					
					rsltDet = stmnt.executeQuery(details);
					
					while (rsltDet.next()) {
//						Long CcheckNo = rsltDet.getLong("phoneNo");
//						String CcheckName = rsltDet.getString("name");
						String CfinalEmail = rsltDet.getString("email");
						int CfinalPinNo = rsltDet.getInt("pin");
						String CfinalState = rsltDet.getString("state");
						String CfinalDistrict = rsltDet.getString("district");
						String CfinalCity = rsltDet.getString("city");
						int CfinalRoadNo = rsltDet.getInt("roadNo");
						
						if(! CfinalEmail.isEmpty() && CfinalEmail != null) {
							 finalEmail = CfinalEmail;
							 finalPinNo = CfinalPinNo;
							 finalState = CfinalState;
							 finalDistrict = CfinalDistrict;
							 finalCity = CfinalCity;
							 finalRoadNo = CfinalRoadNo;
						}
						
					}
					
					break;		
				}
				else{
					workDone = "logInNotsuccess";
				}
				
			}
			
			String hashOut = "select * from cofeDetails";
			rsltCPrice = stmnt.executeQuery(hashOut);
			
			while(rsltCPrice.next()) {
				String name = rsltCPrice.getString("cofeName");
				int cPrice = rsltCPrice.getInt("cofePrice");
				
				cofePrice.put(name, cPrice);
			}
	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (stmnt != null) stmnt.close();
				if (rslt != null) rslt.close();
				if (rsltDet != null) rsltDet.close();
				if (rsltCPrice != null) rsltCPrice.close();
				if (con != null) con.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return workDone ;
	}


}















