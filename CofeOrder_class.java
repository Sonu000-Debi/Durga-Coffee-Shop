package cofeShopDemo.packg;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateDB")

public class CofeOrder_class extends HttpServlet{
	
	static Long phone ;
	static String userName ;
	static String cName ;
	static int cPrice ;
	static int tPrice;
	static String senderName ;
	static String utr ;
	static String delType ;
	static String address ;
	static int rewards ;
	static int rewardUsed;
	static Long UpdateForOrder;
	static String toUpdateStatus;
	static String toUpdateRemark;
	static int pin;
	static String toDedStCname;
	
	static String dbname = "root";
	static String dbPwd = "12345678";
	static String dbUrl = "jdbc:mysql://localhost:3306/coffeeshopDemo?useSSL=false";
	
	static String workDone = "nothing";
	
	private static final long serialVersionUID = 1L;
	
	
	public void doGet(HttpServletRequest req , HttpServletResponse res) throws IOException {
		
		PrintWriter out = res.getWriter();
		
		res.setContentType("text/html");
		
		String work = req.getParameter("work");
		
		if(work.equals("userReqOrder")) {
			
			pin = Integer.parseInt(req.getParameter("pin")) ;
			phone = Long.parseLong(req.getParameter("number"));   //cofeOrder.jsp
			userName = req.getParameter("userName");
			cName = req.getParameter("cofeName");
			cPrice = Integer.parseInt(req.getParameter("cofePrice")) ;
			tPrice = Integer.parseInt(req.getParameter("finalPrice"));  					
			senderName = req.getParameter("SenderName");
			utr = req.getParameter("utr");
			delType = req.getParameter("delType");
			address = req.getParameter("address");
			rewardUsed = Integer.parseInt(req.getParameter("rewardUse"));  					
		
			if(pin == 0) {
				out.println("<html><head><style>");

				// Body styling with background color, text alignment, and padding
				out.println("body { font-family: 'Arial', sans-serif; background-color: #f4f4f9; text-align: center; padding: 50px; margin: 0; display: flex; justify-content: center; align-items: center; height: 100vh; animation: fadeIn 1s ease-in-out; }");

				// Message styling with emphasis color and animation
				out.println("h3 { color: #FF5733; font-size: 24px; font-weight: bold; padding: 10px; animation: fadeInText 1s ease-in-out; }");

				// Link button styling with colors, padding, border radius, and smooth hover effect
				out.println("a { display: inline-block; font-size: 16px; color: #ffffff; background-color: #FF5733; padding: 12px 24px; text-decoration: none; border-radius: 5px; transition: all 0.3s ease; animation: buttonFadeIn 1s ease-in-out; }");

				// Hover effect for the link to make it interactive and dynamic
				out.println("a:hover { background-color: #e74c3c; transform: scale(1.1); }");

				// Keyframe animations for smooth transitions
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

				out.println("<h3>PLEASE UPDATE YOUR PROFILE !!</h3>");
				out.println("<a href='user_userSection.jsp'>UPDATE HERE</a>");

				out.println("</body></html>");

				
			}
			else {
				String orderResult = doWork("orderWork", out);

				out.println("<html><head><style>");

				// Body styling with gradient background and center alignment
				out.println("body { font-family: 'Arial', sans-serif; background: linear-gradient(to right, #ffecd2, #fcb69f); text-align: center; padding: 50px; margin: 0; display: flex; flex-direction: column; justify-content: center; align-items: center; height: 100vh; animation: fadeIn 1s ease-in-out; }");

				// Success message styling with vibrant green and bold text
				out.println("h3.success { color: #2ecc71; font-size: 24px; font-weight: bold; padding: 10px; margin: 10px 0; animation: fadeInText 1s ease-in-out; }");

				// Error message styling with vibrant red and bold text
				out.println("h3.error { color: #e74c3c; font-size: 24px; font-weight: bold; padding: 10px; margin: 10px 0; animation: fadeInText 1s ease-in-out; }");

				// Secondary text with soft gray
				out.println("h5, h6 { color: #34495e; font-size: 18px; margin: 10px 0; animation: fadeInText 1s ease-in-out; }");

				// Link button styling with gradient background, rounded corners, and hover effects
				out.println("a { display: inline-block; font-size: 18px; color: white; text-decoration: none; padding: 12px 24px; border-radius: 25px; background: linear-gradient(to right, #3498db, #8e44ad); margin-top: 20px; transition: all 0.3s ease; animation: buttonFadeIn 1s ease-in-out; }");

				// Link hover effect with brighter gradient
				out.println("a:hover { background: linear-gradient(to right, #5dade2, #9b59b6); transform: scale(1.05); }");

				// Keyframe animation for fading in elements smoothly
				out.println("@keyframes fadeIn { 0% { opacity: 0; } 100% { opacity: 1; } }");
				out.println("@keyframes fadeInText { 0% { opacity: 0; } 100% { opacity: 1; } }");
				out.println("@keyframes buttonFadeIn { 0% { opacity: 0; } 100% { opacity: 1; } }");

				out.println("</style></head><body>");

				// Conditional statements to display success or failure messages
				if (orderResult.equals("orderSuccess")) {
				    out.println("<h3 class='success'>*** ORDERED SUCCESSFUL ***</h3>");
				    out.println("<h5>PAYMENT VERIFICATION TAKES UPTO 5 MINUTES</h5>");
				    out.println("<h5>YOU CAN GO TO HOME PAGE</h5>");
				    out.println("<a href='user_userSection.jsp'>HOME PAGE</a>");
				} else {
				    out.println("<h3 class='error'>UNKNOWN ERROR !!! ORDER AGAIN</h3>");
				    out.println("<a href='user_userSection.jsp'>ORDER AGAIN</a>");
				    out.println("<h6>If money has been debited, it will reverse within 30 minutes</h6>");
				    out.println("<h6>For more information, contact our customer helpline number</h6>");
				}

				out.println("</body></html>");

			}
			
		}
		else if(work.equals("userReqHistory")) {
			System.out.println("request for history");
			phone = Long.parseLong(req.getParameter("userPhNo"));  //user_userSection.jsp
			String historyResult = doWork("historyWorkByUser",out);
			if( historyResult.equals("historyShownNotSuccess")) {
				out.println("unknown error showing in history shown");
				out.println("<a href = \"user_userSection.jsp\">SEE HISTORY AGAIN OR REFRESH</a>");
			}
		}
		
		else if(work.equals("userReqReward")) {			
			System.out.println("request for rewards");
			phone = Long.parseLong(req.getParameter("userPhNo"));
			doWork("rewardWorkByUser",out);
		}
		
		else if(work.equals("pendingsOrder")){
			System.out.println("pending");
			doWork("pendingOrderByAdmin" , out);
		}
		else if(work.equals("historyOrderByAdmin")){
			System.out.println("historyOrderByAdmin");
			doWork("historyOrderByAdmin", out);
		}
		else if(work.equals("doUpdateDB")){
			System.out.println("UPDATION");
			UpdateForOrder = Long.parseLong(req.getParameter("Onumber")); 
			toUpdateStatus = req.getParameter("status");
			toUpdateRemark = req.getParameter("remark");
			toDedStCname = req.getParameter("cNameForStDed");
			System.out.println(toDedStCname);
			doWork("doUpdate",out);
			if(toUpdateStatus.equals("SUCCESSFULL")) {
				doWork("doDeductStock",out);
			}
			doWork("pendingOrderByAdmin" , out);
		}
		else {
			out.println("unknownErrorOnDoGet()");
		}
			
	}
	
	
	public static String doWork(String work , PrintWriter out) {
		
		Connection con = null;
		PreparedStatement pstmnt = null;
		Statement stmnt = null;
		ResultSet rslt = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection(dbUrl , dbname , dbPwd);
			if(work.equals("orderWork")) {
				String orderOrNot = doOrderByUserReq(con,pstmnt);
					if(orderOrNot.equals("ordered")) {
						workDone = "orderSuccess";
					}
					else {
						workDone = "orderNotSuccess";
					}
			}
			else if(work.equals("historyWorkByUser")) {
				String historyRes = doHistoryByUserReq(con,stmnt,rslt,out);
				if(historyRes.equals("shown")) {
					workDone = "historyShownSuccess";
				}
				else {
					workDone = "historyShownNotSuccess";
				}
			}
			else if(work.equals("pendingOrderByAdmin")){
				doPendingOrderWorkByAdmin(con,stmnt ,rslt,out);
			}
			else if(work.equals("historyOrderByAdmin")) {
				doHistoryOrderWorkByAdmin(con,stmnt ,rslt,out);
			}
			else if(work.equals("doUpdate")) {
				doUpdateByAdmin(con, pstmnt);
			}
			else if(work.equals("doDeductStock")){
				doDeductStockByUser(con,stmnt,pstmnt,rslt);
			}
			else if(work.equals("rewardWorkByUser")){
				doRewardWorkByUser(con,stmnt ,rslt,out);
			}
			else {
				out.println("unknown error by admin");
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
				if (con != null) con.close();
				if (stmnt != null) stmnt.close();
				if (rslt != null) rslt.close();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return workDone;
	}


	public static String doOrderByUserReq(Connection con,PreparedStatement pstmnt) {
	    
	    try {
	        String sqlQur = "insert into cofeOrders(phNo, userName, cofeName, orderTime, cofePrice, senderName, utr, "
	                + "deliveryType, address, orderStatus, rewards, remark, rewardUsed, totalPrice)"
	                + " values (?, ?, ?, NOW(), ?, ?, ?, ?, ?, ?, 0, ?, ?, ?)";
	        
	        // Prepare statement within DOiNSERT
	        pstmnt = con.prepareStatement(sqlQur);
	        

	        
	        pstmnt.setLong(1, phone);        
	        pstmnt.setString(2, userName);
	        pstmnt.setString(3, cName);
	        pstmnt.setInt(4, cPrice);
	        pstmnt.setString(5, senderName);
	        pstmnt.setString(6, utr);
	        pstmnt.setString(7, delType);
	        pstmnt.setString(8, address);
	        pstmnt.setString(9, "pending");
	        pstmnt.setString(10, "unseen");
	        pstmnt.setInt(11, rewardUsed);
	        pstmnt.setInt(12, tPrice);
	        
	        pstmnt.executeUpdate();
	        System.out.println("ordered");
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return "ordered";  // Return the flag
	}
	
	
	public static String doHistoryByUserReq(Connection con,Statement stmnt ,ResultSet rslt,PrintWriter out){
		String sqlQur = "select orderTime, cofeName, cofePrice, orderStatus, remark from cofeOrders where phNo = '"+phone+"' ORDER BY orderTime DESC";
		try {
		    stmnt = con.createStatement();
		    rslt = stmnt.executeQuery(sqlQur);
		    
		    out.println("<a href = \"user_userSection.jsp\" style='color: #007BFF; font-size: 16px; text-decoration: none; display: block; margin-bottom: 20px;'>BACK</a>");

		    out.println("<table border='1' style='width: 100%; border-collapse: collapse; margin-top: 20px;'>");
		    out.println("<tr style='background-color: #f2f2f2; text-align: center; font-weight: bold;'>"
		        + "<th>COFFEE NAME</th>"
		        + "<th>COFFEE PRICE</th>"
		        + "<th>ORDER TIME</th>"
		        + "<th>STATUS</th>"
		        + "<th>REMARK</th>"
		        + "</tr>");

		    boolean hasHist = false;
		    // Iterating through the result set
		    while (rslt.next()) {
		        hasHist = true;
		        
		        int price = rslt.getInt("cofePrice");
		        Timestamp dtTime = rslt.getTimestamp("orderTime");
		        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm - dd MMMM yyyy");  // HH for two-digit hours
		        String formattedTime = sdf.format(dtTime);
		        String cName = rslt.getString("cofeName");
		        String status = rslt.getString("orderStatus");
		        String remark = rslt.getString("remark");

		        out.println("<tr style='text-align: center;'>");
		        out.println("<td>" + cName + "</td>");
		        out.println("<td>" + price + "</td>");
		        out.println("<td>" + formattedTime + "</td>");
		        out.println("<td>" + status + "</td>");
		        out.println("<td>" + remark + "</td>");
		        out.println("</tr>");
		    }

		    out.println("</table>");
		    
		    if (!hasHist) {
		        out.println("<br><br><h3 style='color: #FF0000; text-align: center;'>YOU HAVE NOT ORDERED YET !!!</h3>");
		        out.println("<br><br><a href = \"user_userSection.jsp\" style='color: #007BFF; font-size: 18px; text-decoration: none; display: block; text-align: center;'>ORDER NOW</a>");
		    }
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "shown";
	}

	
	public static void doPendingOrderWorkByAdmin(Connection con,Statement stmnt ,ResultSet rslt,PrintWriter out) {
		String sqlQur = "select * from cofeOrders where orderStatus = 'pending'";
		try {
			stmnt = con.createStatement();
			rslt = stmnt.executeQuery(sqlQur);
			
			
			
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>PENDING ORDERS</title>");
			out.println("<style>");
			out.println("body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; }");
			out.println("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
			out.println("th, td { padding: 10px; text-align: center; border: 1px solid #ddd; }");
			out.println("th { background-color: #4CAF50; color: white; }");
			out.println("td { background-color: #f9f9f9; }");
			out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
			out.println(".selectStatus, .selectRemark { width: 100%; padding: 8px; margin: 5px; border: 1px solid #ddd; }");
			out.println(".submit-btn { background-color: #4CAF50; color: white; padding: 10px 20px; border: none; cursor: pointer; }");
			out.println(".submit-btn:hover { background-color: #45a049; }");
			out.println(".orderNumber, .phoneNo, .Uname, .time, .Cname, .Cprice, .dType, .rerdAvail, .rerdUsed, .tPrice, .Sname, .utr, .address { font-weight: bold; }");
			out.println(".noOrders { color: red; font-size: 18px; text-align: center; }");
			out.println("a { color: #007BFF; text-decoration: none; font-size: 18px; }");
			out.println("a:hover { text-decoration: underline; }");
			out.println("</style>");
			out.println("</head>");

			out.println("<body>");

			out.println("<form id='updateDbForm' action = \"updateDB\" style='display:none;'>");
			out.println("<input type='hidden' id='hiddenNo' name='Onumber'>");
			out.println("<input type='hidden' id='hiddenStatus' name='status'>");
			out.println("<input type='hidden' id='hiddenRemark' name='remark'>");
			out.println("<input type='hidden' id='hiddenCname' name='cNameForStDed'>");
			out.println("<input type='hidden' name='work' value = \"doUpdateDB\">");
			out.println("</form>");

			out.println("<table>");
			out.println("<tr>");
			out.println("<th>ORDER NO.</th>");
			out.println("<th>PHONE NO.</th>");
			out.println("<th>USER NAME</th>");
			out.println("<th>ORDER TIME</th>");
			out.println("<th>COFFEE NAME</th>");
			out.println("<th>COFFEE PRICE</th>");
			out.println("<th>DELIVERY TYPE</th>");
			out.println("<th>REWARD AVAILABLE</th>");
			out.println("<th>REWARD USED</th>");
			out.println("<th>FINAL PRICE</th>");
			out.println("<th>SENDER NAME</th>");
			out.println("<th>UTR</th>");
			out.println("<th>ADDRESS</th>");
			out.println("<th>STATUS</th>");
			out.println("<th>REMARK</th>");
			out.println("<th>ACTION</th>");
			out.println("</tr>");

			boolean hasOrder = false;

			while (rslt.next()) {
			    hasOrder = true;

			    Long orderNo = rslt.getLong("orderNo");
			    Long phNo = rslt.getLong("phNo");
			    String UserName = rslt.getString("userName");
			    Timestamp dtTime = rslt.getTimestamp("orderTime");
			    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm - dd MMMM yyyy");  
			    String formattedTime = sdf.format(dtTime);
			    String c_name = rslt.getString("cofeName");
			    int c_price = rslt.getInt("cofePrice");
			    String deliveryType = rslt.getString("deliveryType");
			    int rewardUsed = rslt.getInt("rewardUsed");
			    int t_price = rslt.getInt("totalPrice");
			    String s_name = rslt.getString("senderName");
			    String utr = rslt.getString("utr");
			    String address = rslt.getString("address");

			    Statement stmt2 = con.createStatement();
			    ResultSet rs2 = stmt2.executeQuery("SELECT SUM(rewards) AS totalGotReward FROM cofeOrders WHERE phNo = '" + phNo + "' AND orderStatus = 'SUCCESSFULL'");
			    int gotRd = 0;
			    if (rs2.next()) {
			        gotRd = rs2.getInt("totalGotReward");
			    }

			    rs2.close();
			    stmt2.close();

			    Statement stmt3 = con.createStatement();
			    ResultSet rs3 = stmt3.executeQuery("SELECT SUM(rewardUsed) AS totalUsedReward FROM cofeOrders WHERE phNo = '" + phNo + "' AND orderStatus = 'SUCCESSFULL'");
			    int usedRd = 0;
			    if (rs3.next()) {
			        usedRd = rs3.getInt("totalUsedReward");
			    }

			    rs3.close();
			    stmt3.close();

			    int availableReward = gotRd - usedRd;
			    if (deliveryType.equals("STORE")) {
			        address = "STORE";
			    }

			    out.println("<tr>");
			    out.println("<td class='orderNumber'>" + orderNo + "</td>");
			    out.println("<td>" + phNo + "</td>");
			    out.println("<td>" + UserName + "</td>");
			    out.println("<td>" + formattedTime + "</td>");
			    out.println("<td class='Cname'>" + c_name + "</td>");
			    out.println("<td>" + c_price + "</td>");
			    out.println("<td>" + deliveryType + "</td>");
			    out.println("<td>" + availableReward + "</td>");
			    out.println("<td>" + rewardUsed + "</td>");
			    out.println("<td>" + t_price + "</td>");
			    out.println("<td>" + s_name + "</td>");
			    out.println("<td>" + utr + "</td>");
			    out.println("<td>" + address + "</td>");

			    out.println("<td>");
			    out.println("<select class='selectStatus' required>");
			    out.println("<option value='' disabled selected>select</option>");
			    out.println("<option value='SUCCESSFULL'>SUCCESSFULL</option>");
			    out.println("<option value='UNSUCCESSFULL'>UNSUCCESSFULL</option>");
			    out.println("</select>");
			    out.println("</td>");

			    out.println("<td>");
			    out.println("<select class='selectRemark' required>");
			    out.println("<option value='' disabled selected>select</option>");
			    out.println("<option value='THANK YOU'>THANK YOU</option>");
			    out.println("<option value='LESS MONEY PAID'>LESS MONEY PAID</option>");
			    out.println("<option value='LESS RWARDS AVAILABLE'>LESS RWARDS AVAILABLE</option>");
			    out.println("<option value='STOCK FINISHED'>STOCK FINISHED</option>");
			    out.println("<option value='OUT OF AREA'>OUT OF AREA</option>");
			    out.println("<option value='SHOP CLOSED'>SHOP CLOSED FOR TODAY</option>");
			    out.println("<option value='TRANSACTION ISSUES'>TRANSACTION ISSUES</option>");
			    out.println("</select>");
			    out.println("</td>");

			    out.println("<td>");
			    out.println("<button type='button' class='submit-btn'>SUBMIT</button>");
			    out.println("</td>");

			    out.println("</tr>");
			}

			out.println("</table>");

			if (!hasOrder) {
			    out.println("<br><br><h3 class='noOrders'>NO PENDING ORDERS ARE FOUND</h3><br><br>");
			    out.println("YOU CAN GO <a href='AdminHome.jsp'>HOME PAGE</a>");
			} else {
			    out.println("<script>");
			    out.println("document.addEventListener('DOMContentLoaded', function () {");
			    out.println("    document.querySelectorAll('.submit-btn').forEach(button => {");
			    out.println("        button.addEventListener('click', function () {");
			    out.println("            let row = button.closest('tr');");
			    out.println("            let Onumber = row.querySelector('.orderNumber').innerText;");
			    out.println("            let status = row.querySelector('.selectStatus').value;");
			    out.println("            let remark = row.querySelector('.selectRemark').value;");
			    out.println("            let Cname = row.querySelector('.Cname').innerText;");
			    out.println("            if (!status || !remark) {");
			    out.println("                alert('Please select both status and remark!');");
			    out.println("                return;");
			    out.println("            }");
			    out.println("            document.getElementById('hiddenNo').value = Onumber;");
			    out.println("            document.getElementById('hiddenStatus').value = status;");
			    out.println("            document.getElementById('hiddenRemark').value = remark;");
			    out.println("            document.getElementById('hiddenCname').value = Cname;");
			    out.println("            document.getElementById('updateDbForm').submit();");
			    out.println("            row.style.display = 'none';");
			    out.println("        });");
			    out.println("    });");
			    out.println("});");
			    out.println("</script>");
			}

			out.println("</body>");
			out.println("</html>");

				
				
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static void doUpdateByAdmin(Connection con, PreparedStatement pstmnt) {
		
		int min = 2;
        int max = 5;

        // Generate random number between 2 and 5 (inclusive)
        int randomNum = (int)(Math.random() * (max - min + 1)) + min;
		
		String sqlQur = "UPDATE cofeOrders SET orderStatus = ?, remark = ?, rewards = ? WHERE orderNo = ?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sqlQur);
			pstmt.setString(1, toUpdateStatus);  // New status
			pstmt.setString(2, toUpdateRemark);      // Remark
			pstmt.setInt(3, randomNum);
			pstmt.setLong(4, UpdateForOrder); 
			
			pstmt.executeUpdate();
			System.out.println("updated successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}
	
	
	public static void doHistoryOrderWorkByAdmin(Connection con,Statement stmnt ,ResultSet rslt,PrintWriter out){
		String sqlQur = "select * from cofeOrders order by orderTime desc";
		
		try {
			
			stmnt = con.createStatement();
			rslt = stmnt.executeQuery(sqlQur);
			
			
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			    out.println("<head>");
			        out.println("<title>ORDERS HISTORY</title>");
			        out.println("<style>");
			            out.println("body { font-family: Arial, sans-serif; padding: 20px; background-color: #f4f4f4; }");
			            out.println("a { text-decoration: none; color: #4CAF50; font-weight: bold; margin-bottom: 20px; display: inline-block; }");
			            out.println("a:hover { color: #45a049; }");
			            out.println("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
			            out.println("th, td { padding: 10px; text-align: left; border: 1px solid #ddd; }");
			            out.println("th { background-color: #4CAF50; color: white; }");
			            out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
			            out.println("tr:hover { background-color: #ddd; }");
			            out.println("h3 { color: #ff0000; font-size: 18px; }");
			        out.println("</style>");
			    out.println("</head>");
			    
			    out.println("<body>");
			        out.println("<a href = \"AdminHome.jsp\">BACK</a>");
			        out.println("<table>");
			        
			            out.println("<tr>");
			                out.println("<th>ORDER NO.</th>");
			                out.println("<th>PHONE NO.</th>");
			                out.println("<th>USER NAME</th>");
			                out.println("<th>ORDER TIME</th>");
			                out.println("<th>COFFEE NAME</th>");
			                out.println("<th>COFFEE PRICE</th>");
			                out.println("<th>DELIVERY TYPE</th>");
			                out.println("<th>REWARD USED</th>");
			                out.println("<th>FINAL PRICE</th>");
			                out.println("<th>SENDER NAME</th>");
			                out.println("<th>UTR</th>");
			                out.println("<th>ADDRESS</th>");
			                out.println("<th>STATUS</th>");
			                out.println("<th>REWARD GOT</th>");
			                out.println("<th>REMARK</th>");
			            out.println("</tr>");
			            
			            boolean history = false;

			            while(rslt.next()) {
			                history = true;

			                Long orderNo = rslt.getLong("orderNo");
			                Long phNo = rslt.getLong("phNo");
			                String UserName = rslt.getString("userName");
			                Timestamp dtTime = rslt.getTimestamp("orderTime");
			                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm - dd MMMM yyyy");
			                String formattedTime = sdf.format(dtTime);
			                String c_name = rslt.getString("cofeName");
			                int c_price = rslt.getInt("cofePrice");
			                String deliveryType = rslt.getString("deliveryType");
			                int rewardUsed = rslt.getInt("rewardUsed");
			                int t_price = rslt.getInt("totalPrice");
			                String s_name = rslt.getString("senderName");
			                String utr = rslt.getString("utr");
			                String address = rslt.getString("address");
			                String status = rslt.getString("orderStatus");
			                int rewardsGot = rslt.getInt("rewards");
			                String remark = rslt.getString("remark");

			                if(deliveryType.equals("STORE")) {
			                    address = "STORE";
			                }

			                out.println("<tr>");
			                    out.println("<td>" + orderNo + "</td>");
			                    out.println("<td>" + phNo + "</td>");
			                    out.println("<td>" + UserName + "</td>");
			                    out.println("<td>" + formattedTime + "</td>");
			                    out.println("<td>" + c_name + "</td>");
			                    out.println("<td>" + c_price + "</td>");
			                    out.println("<td>" + deliveryType + "</td>");
			                    out.println("<td>" + rewardUsed + "</td>");
			                    out.println("<td>" + t_price + "</td>");
			                    out.println("<td>" + s_name + "</td>");
			                    out.println("<td>" + utr + "</td>");
			                    out.println("<td>" + address + "</td>");
			                    out.println("<td>" + status + "</td>");
			                    out.println("<td>" + rewardsGot + "</td>");
			                    out.println("<td>" + remark + "</td>");
			                out.println("</tr>");
			            }

			        out.println("</table>");

			        if(!history) {
			            out.println("<br><br><h3>NO HISTORY ARE FOUND</h3><br><br>");
			            out.println("YOU CAN GO <a href = \"AdminHome.jsp\">HOME PAGE</a>");
			        }

			    out.println("</body>");
			out.println("</html>");

						
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void doRewardWorkByUser(Connection con,Statement stmnt ,ResultSet rslt,PrintWriter out){
		String sqlQur = "select orderTime, cofeName, rewards, rewardUsed from cofeOrders"
				+ " where phNo = '"+phone+"' AND orderStatus = 'SUCCESSFULL'";
		
		try {
			stmnt = con.createStatement();
			rslt = stmnt.executeQuery(sqlQur);
			
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			    out.println("<head>");
			        out.println("<title>REWARD HISTORY</title>");
			        out.println("<style>");
			            out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; }");
			            out.println("a { text-decoration: none; color: #4CAF50; font-weight: bold; margin-top: 10px; display: inline-block; }");
			            out.println("a:hover { color: #45a049; }");
			            out.println("h3 { color: #4CAF50; font-size: 18px; margin-top: 20px; }");
			            out.println("table { width: 100%; border-collapse: collapse; margin-top: 20px; background-color: white; }");
			            out.println("th, td { padding: 10px; text-align: left; border: 1px solid #ddd; }");
			            out.println("th { background-color: #4CAF50; color: white; }");
			            out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
			            out.println("tr:hover { background-color: #ddd; }");
			            out.println("#totalRdHd, #availRdHd { font-size: 16px; font-weight: bold; color: #ff5722; margin-top: 20px; display: none; }");
			        out.println("</style>");
			    out.println("</head>");
			    
			    out.println("<body>");
			        out.println("<a href = \"user_userSection.jsp\">BACK</a>");
			        out.println("<h3 id = \"totalRdHd\" style=\"display:none;\" ></h3>");
			        out.println("<h3 id = \"availRdHd\" style=\"display:none;\" ></h3>");
			        out.println("<table>");
			            out.println("<tr>");
			                out.println("<th>ORDER TIME</th>");
			                out.println("<th>COFFEE NAME</th>");
			                out.println("<th>REWARDS</th>");
			            out.println("</tr>");

			            boolean hasReward = false;
			            int totalRewardUsed = 0;
			            int totalRewardGot = 0;

			            while(rslt.next()) {
			                hasReward = true;

			                Timestamp dtTime = rslt.getTimestamp("orderTime");
			                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm - dd MMMM yyyy");  // HH for two-digit hours
			                String formattedTime = sdf.format(dtTime);
			                String cName = rslt.getString("cofeName");
			                int rewardUsed = rslt.getInt("rewardUsed");
			                int rewardGot = rslt.getInt("rewards");

			                totalRewardUsed += rewardUsed;
			                totalRewardGot += rewardGot;

			                out.println("<tr>");
			                    out.println("<td class = \"orderNumber\"> " + formattedTime + "</td>");
			                    out.println("<td class = \"phoneNo\"> " + cName + "</td>");
			                    out.println("<td class = \"Uname\"> " + rewardGot + "</td>");
			                out.println("</tr>");
			            }

			        out.println("</table>");
			        
			        int totalAvailableReward = totalRewardGot - totalRewardUsed;

			        if(!hasReward) {
			            out.println("YOU HAVE NOT ANY REWARDS !! <a href = \"user_userSection.jsp\">ORDER NOW</a>");
			        } else {
			            out.println("<script>");
			            out.println("document.addEventListener('DOMContentLoaded', function () {\r\n" + 
			                        "    let availRdHd = document.querySelector(\"#availRdHd\");\r\n" +
			                        "    let totalRdHd = document.querySelector(\"#totalRdHd\");\r\n" +
			                        "    totalRdHd.innerText = \"TOTAL REWARD EARNED >> " + totalRewardGot + "\";\r\n" +
			                        "    availRdHd.innerText = \"AVAILABLE NOW >> " + totalAvailableReward + "\";\r\n" + 
			                        "    totalRdHd.style.display = \"block\";\r\n" +
			                        "    availRdHd.style.display = \"block\";\r\n" +
			                        "});");
			            out.println("</script>");
			        }

			    out.println("</body>");
			out.println("</html>");

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	public static void doDeductStockByUser(Connection con,Statement stmnt,PreparedStatement pstmnt,ResultSet rslt) {
		
		String sqlQur = "select cStocks from cofeDetails where cofeName = '"+toDedStCname+"'";
		System.out.println(toDedStCname);
		try {
			stmnt = con.createStatement();
			rslt = stmnt.executeQuery(sqlQur);
			
			int Stock = 0;
			while(rslt.next()) {
				Stock = rslt.getInt("cStocks");
				System.out.println("deductded from "+Stock);
			}
			Stock = Stock-1;
			
			sqlQur = "update cofeDetails set cStocks = ? where cofeName = ?";
			
			pstmnt = con.prepareStatement(sqlQur);
			
			pstmnt.setInt(1, Stock);
			pstmnt.setString(2, toDedStCname);
			
			pstmnt.executeUpdate();
			
			System.out.println("to "+Stock);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}









