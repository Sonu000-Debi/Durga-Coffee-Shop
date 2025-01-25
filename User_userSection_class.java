package cofeShopDemo.packg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class User_userSection_class extends HttpServlet{
	
	static String dbname = "root";
	static String dbPwd = "12345678";
	static String dbUrl = "jdbc:mysql://localhost:3306/coffeeshopDemo?useSSL=false";

	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req , HttpServletResponse res) throws IOException  , ServletException{
		PrintWriter out = res.getWriter();
		
		res.setContentType("text/html");
		Long finalPhone = Long.parseLong(req.getParameter("userPhone"));
		String finalName = req.getParameter("userName") ;
		String finalEmail = req.getParameter("userEmail") ;
		int finalPinNo = Integer.parseInt(req.getParameter("userPin")) ;
		String finalState = req.getParameter("userState") ;
		String finalDistrict = req.getParameter("userDist") ;
		String finalCity = req.getParameter("userCity") ;
		int finalRoadNo = Integer.parseInt(req.getParameter("userRoad")) ;
		
		
		System.out.println(finalState);
		
		boolean outpt = doEdit(finalPhone ,finalName,finalEmail,finalPinNo,finalState,finalDistrict,finalCity,finalRoadNo);
				
		
		if(outpt) {
			
			
			
			HttpSession sesObj = req.getSession();
			
			sesObj.setAttribute("userPhNo" , finalPhone);
			sesObj.setAttribute("userName" , finalName);
			sesObj.setAttribute("userEmail" , finalEmail);
			sesObj.setAttribute("userPin" , finalPinNo);
			sesObj.setAttribute("userState" , finalState);
			sesObj.setAttribute("userDist" , finalDistrict);
			sesObj.setAttribute("userCity" , finalCity);
			sesObj.setAttribute("userRoadNo" , finalRoadNo);
			
			out.println("<html><head><style>");

			out.println("body {");
			out.println("  font-family: 'Arial', sans-serif;");
			out.println("  background-color: #f8f9fa;");
			out.println("  display: flex;");
			out.println("  justify-content: center;");
			out.println("  align-items: center;");
			out.println("  height: 100vh;");
			out.println("  margin: 0;");
			out.println("  text-align: center;");
			out.println("  animation: fadeIn 1s ease-in-out;");
			out.println("}");

			out.println("h3 {");
			out.println("  color: #28a745;");
			out.println("  font-size: 28px;");
			out.println("  font-weight: 600;");
			out.println("  padding: 15px;");
			out.println("  text-transform: uppercase;");
			out.println("  animation: slideInFromTop 1s ease-in-out;");
			out.println("}");

			out.println("p {");
			out.println("  font-size: 18px;");
			out.println("  color: #495057;");
			out.println("  padding: 10px;");
			out.println("  animation: fadeInText 1.5s ease-in-out;");
			out.println("}");

			out.println("a {");
			out.println("  display: inline-block;");
			out.println("  text-align: center;");
			out.println("  font-size: 16px;");
			out.println("  background-color: #007BFF;");
			out.println("  color: white;");
			out.println("  padding: 12px 24px;");
			out.println("  text-decoration: none;");
			out.println("  border-radius: 50px;");
			out.println("  margin-top: 20px;");
			out.println("  transition: all 0.3s ease;");
			out.println("  animation: buttonFadeIn 1s ease-in-out;");
			out.println("}");

			out.println("a:hover {");
			out.println("  background-color: #0056b3;");
			out.println("  transform: scale(1.05);");
			out.println("}");

			out.println("@keyframes fadeIn {");
			out.println("  0% { opacity: 0; }");
			out.println("  100% { opacity: 1; }");
			out.println("}");

			out.println("@keyframes fadeInText {");
			out.println("  0% { opacity: 0; }");
			out.println("  100% { opacity: 1; }");
			out.println("}");

			out.println("@keyframes slideInFromTop {");
			out.println("  0% { transform: translateY(-50px); opacity: 0; }");
			out.println("  100% { transform: translateY(0); opacity: 1; }");
			out.println("}");

			out.println("@keyframes buttonFadeIn {");
			out.println("  0% { opacity: 0; }");
			out.println("  100% { opacity: 1; }");
			out.println("}");

			out.println("</style></head><body>");

			out.println("<h3>PROFILE UPDATED SUCCESSFULLY</h3>");
			out.println("<p>Your profile has been updated successfully. Click below to continue.</p>");
			out.println("<a href='user_userSection.jsp'>OK</a>");

			out.println("</body></html>");




		}
		
	}
	public boolean doEdit(Long finalPhone ,String finalName,String finalEmail,int finalPinNo,String finalState,String finalDistrict,
			String finalCity,int finalRoadNo) {
		
		Connection con = null;
		PreparedStatement pstmnt = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection(dbUrl , dbname , dbPwd);
			
			String sqlQur = "insert into userDetailsDemo(phoneNo , name , email , pin , state , district , city , roadNo) values"
					   +"(?,?,?,?,?,?,?,?)"
					   +" ON DUPLICATE KEY UPDATE email = '"+finalEmail+"' , pin = "+finalPinNo+" , state = '"+finalState+"' , district = '"+ finalDistrict
					  +"' , city = '"+ finalCity + "' , roadNo = '"+finalRoadNo+"'";

			pstmnt = con.prepareStatement(sqlQur);
			pstmnt.setLong(1, finalPhone);
			pstmnt.setString(2,finalName);
			pstmnt.setString(3, finalEmail);
			pstmnt.setInt(4, finalPinNo);
			pstmnt.setString(5, finalState);
			pstmnt.setString(6, finalDistrict);
			pstmnt.setString(7, finalCity);
			pstmnt.setInt(8, finalRoadNo);
			pstmnt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (pstmnt != null) pstmnt.close();
				if (con != null) con.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	return true;	
	}
}
