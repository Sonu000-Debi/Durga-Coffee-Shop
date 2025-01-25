package cofeShopDemo.packg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/cofeDetailsServlet")
public class CoffeeDetailsClass extends HttpServlet{
	
	static String dbId = "root";
	static String dbPwd = "12345678";
	static String dbUrl = "jdbc:mysql://localhost:3306/coffeeshopDemo?useSSL=false";
	
	static int cofeNprice;
	static int cofeNo ;
	static String newCofeName ;
	static int newCofePrice;
	static int newCofeStock;
	static int cofeNoDelete;
	static int cofeNoForStock;
	static int cofeNStock;

	private static final long serialVersionUID = 1L;

	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String work = req.getParameter("work");
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		
		if(work.equals("cofePricing")) {
			doWork("cofePricing",out);
		}
		
		else if(work.equals("doUpdatePrice")) {
			cofeNo = Integer.parseInt(req.getParameter("Cnumber"));
			cofeNprice = Integer.parseInt(req.getParameter("cPrice"));
			doWork("doUpdatePrice",out);
			doWork("cofePricing",out);			
		}
		
		else if (work.equals("newCofeAdd")) {
			newCofeName = (req.getParameter("newCofeAddName")).toUpperCase();
			newCofePrice = Integer.parseInt(req.getParameter("newCofeAddPrice"));
			newCofeStock = Integer.parseInt(req.getParameter("newCofeAddStock"));
			
			doWork("newCofeAdd",out);
		}
		else if(work.equals("deleteCoffee")) {
			doWork("deleteCoffee",out);
		}
		else if(work.equals("cNoToWork")){
			cofeNoDelete = Integer.parseInt(req.getParameter("cNoToDel"));
			doWork("cNoToWork",out);
		}
		else if(work.equals("stockInquiry")){
			doWork("stockInquiry",out);
		}
		else if(work.equals("doUpdateStock")) {
			cofeNoForStock = Integer.parseInt(req.getParameter("CnumberStChange"));
			cofeNStock = Integer.parseInt(req.getParameter("cStock"));
			doWork("doUpdateStock",out);
			doWork("stockInquiry",out);
		}
		else {
			out.println("CAN NOT USE WORK IN COFFEE DETAILS CLASS !!");
		}

		
	}
	
	
	public static void doWork(String workType , PrintWriter out) {
		
		Connection con = null;
		Statement stmnt = null;
		PreparedStatement pstmnt = null;
		ResultSet rslt = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl,dbId,dbPwd);
			
			if(workType.equals("cofePricing")) {
				doCofePricingWork(con,stmnt,rslt,out);
			}
			else if(workType.equals("doUpdatePrice")) {
				doCofePriceUpdate(con,pstmnt);
			}
			else if(workType.equals("newCofeAdd")){
				doAddNewCofe(con,pstmnt,out);
			}
			else if(workType.equals("deleteCoffee")) {
				doDeleteWork(con,stmnt,rslt,out);
			}
			else if(workType.equals("cNoToWork")) {
				doDeleteCofeNow(con,pstmnt,out);
			}
			else if(workType.equals("stockInquiry")){
				doStockInq(con,stmnt,rslt,out);
			}
			else if(workType.equals("doUpdateStock")) {
				doStockUpdate(con,pstmnt);
			}
			else {
				out.println("CAN NOT USE DOWORK IN COFFEE DETAILS CLASS !!");
			}
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(con != null) con.close();
				if(stmnt != null) stmnt.close();
				if(pstmnt != null) pstmnt.close();
				if(rslt != null) rslt.close();	
			}catch (Exception e){
				e.printStackTrace();
			}
		}	
	}
	
	
	public static void doCofePricingWork(Connection con, Statement stmnt, ResultSet rslt,PrintWriter out ) {
		
		String sqlQur = "select cofeId, cofeName, cofePrice from cofeDetails";
		
		try {
			stmnt = con.createStatement();
			rslt = stmnt.executeQuery(sqlQur);
			

			out.println("<a href = \"AdminHome.jsp\" style='color: #4CAF50; text-decoration: none; font-size: 16px;'>BACK</a>");

			out.println("<!DOCTYPE html>");
			out.println("<html>");
			    out.println("<head>");
			        out.println("<title>COFE PRICING</title>");
			    out.println("</head>");

			    out.println("<body style='font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 20px;'>");

			        // Hidden Form
			        out.println("<form id='updateDbForm' action = \"cofeDetailsServlet\" style='display:none;'>");
			            out.println("<input type='hidden' id='hiddenNo' name='Cnumber'>");
			            out.println("<input type='hidden' id='hiddenPricing' name='cPrice'>");
			            out.println("<input type='hidden' name='work' value = \"doUpdatePrice\">");
			        out.println("</form>");

			        // Start Table
			        out.println("<table border='1' style='width: 100%; border-collapse: collapse; margin-top: 20px;'>");
			            out.println("<tr style='background-color: #4CAF50; color: white;'>");
			                out.println("<th style='padding: 10px; text-align: left;'>COFFEE NO.</th>");
			                out.println("<th style='padding: 10px; text-align: left;'>COFFEE NAME</th>");
			                out.println("<th style='padding: 10px; text-align: left;'>COFFEE OLD PRICE</th>");
			                out.println("<th style='padding: 10px; text-align: left;'>COFFEE NEW PRICE</th>");
			                out.println("<th style='padding: 10px; text-align: left;'>CHANGE NOW</th>");
			            out.println("</tr>");

			            while(rslt.next()) {
			                int cofeId = rslt.getInt("cofeId");
			                String cofeName = rslt.getString("cofeName");
			                int cofePrice = rslt.getInt("cofePrice");

			                out.println("<tr style='background-color: #f9f9f9;'>");
			                    out.println("<td class='cofeNumber' style='padding: 8px;'>" + cofeId + "</td>");
			                    out.println("<td class='cofeName' style='padding: 8px;'>" + cofeName + "</td>");
			                    out.println("<td class='cofeOldPrice' style='padding: 8px;'>" + cofePrice + "</td>");
			                    out.println("<td style='padding: 8px;'> <input type='number' class='cofeNewPrice' required style='padding: 5px; width: 100px;'></td>");
			                    out.println("<td style='padding: 8px;'>");
			                    out.println("<button type='button' class='submit-btn' style='padding: 6px 12px; background-color: #4CAF50; color: white; border: none; cursor: pointer; border-radius: 5px; font-size: 14px;'>UPDATE</button>");
			                    out.println("</td>");
			                out.println("</tr>");
			            }
			        out.println("</table>");

			        out.println("<script>");
			            out.println("document.addEventListener('DOMContentLoaded', function () {");
			            out.println("    document.querySelectorAll('.submit-btn').forEach(button => {");
			            out.println("        button.addEventListener('click', function () {");
			            out.println("            let row = button.closest('tr');");
			            out.println("            let Cnumber = row.querySelector('.cofeNumber').innerText;");
			            out.println("            let cNprice = row.querySelector('.cofeNewPrice').value;");
			            out.println("            if (!cNprice) {");
			            out.println("                alert('Please enter new price!!');");
			            out.println("                return;");
			            out.println("            }");
			            out.println("            document.getElementById('hiddenNo').value = Cnumber;");
			            out.println("            document.getElementById('hiddenPricing').value = cNprice;");
			            out.println("            document.getElementById('updateDbForm').submit();");
			            out.println("            alert('COFFEE PRICE UPDATED');");
			            out.println("        });");
			            out.println("    });");
			            out.println("});");
			        out.println("</script>");

			    out.println("</body>");
			out.println("</html>");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public static void doCofePriceUpdate(Connection con, PreparedStatement pstmnt) {
		
		String sqlQur = "update cofeDetails set cofePrice = ? where cofeId = ?";
		
		try {
			pstmnt = con.prepareStatement(sqlQur);
			
			pstmnt.setInt(1, cofeNprice);
			pstmnt.setInt(2, cofeNo);
			
			pstmnt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	public static void doAddNewCofe(Connection con,PreparedStatement pstmnt, PrintWriter out) {
		
		String sqlQur = "insert into cofeDetails(cofeName,cofePrice,cStocks) values (?,?,?)";
		
		try {
			pstmnt = con.prepareStatement(sqlQur);
			pstmnt.setString(1,newCofeName);
			pstmnt.setInt(2, newCofePrice);
			pstmnt.setInt(3, newCofeStock);
			
			pstmnt.executeUpdate();
			
			out.println("<div style='text-align: center; margin-top: 20px; padding: 20px; background-color: #4CAF50; color: white; font-size: 18px; border-radius: 5px;'>");
			out.println("NEW COFFEE ADDED SUCCESSFULLY");
			out.println("</div>");

			out.println("<div style='text-align: center; margin-top: 20px;'>");
			out.println("<a href = \"AdminHome.jsp\" style='font-size: 16px; color: #4CAF50; text-decoration: none; padding: 10px 20px; border: 1px solid #4CAF50; border-radius: 5px; background-color: white;'>HOME PAGE</a>");
			out.println("</div>");

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public static void doDeleteWork(Connection con,Statement stmnt,ResultSet rslt, PrintWriter out) {
		
		String sqlQur = "select * from cofeDetails";
		
		try {
			stmnt = con.createStatement();
			rslt = stmnt.executeQuery(sqlQur);
			
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			    out.println("<head>");
			        out.println("<title>COFFEE DELETE</title>");      
			    out.println("</head>");
			    
			    out.println("<body style='font-family: Arial, sans-serif; margin: 20px;'>");
			        
			        out.println("<a href = \"AdminHome.jsp\" style='color: #4CAF50; font-size: 16px; text-decoration: none; margin-bottom: 20px; display: inline-block;'>BACK</a><br><br>");
			        
			        out.println("<label style='font-size: 18px; color: #333;'>ENTER COFFEE NUMBER TO DELETE</label>");
			        out.println("<form action = \"serv6\" style='margin-top: 10px;'>"
			                + "<input type = \"number\" name = \"cNoToDel\" required style='padding: 10px; font-size: 16px; margin-right: 10px;'>"
			                + "<input type = \"hidden\" name = \"work\" value = \"cNoToWork\">"
			                + "<input type = \"submit\" value = \"DELETE\" style='padding: 10px 20px; font-size: 16px; background-color: #4CAF50; color: white; border: none; border-radius: 5px;'>"
			                + "</form>");
			        
			        out.println("<table border='1' style='width: 100%; border-collapse: collapse; margin-top: 20px;'>");
			        
			            out.println("<tr style='background-color: #f2f2f2;'>");
			                out.println("<th style='padding: 10px; text-align: left;'>COFFEE NO.</th>");
			                out.println("<th style='padding: 10px; text-align: left;'>COFFEE NAME</th>");
			                out.println("<th style='padding: 10px; text-align: left;'>COFFEE PRICE</th>");
			                out.println("<th style='padding: 10px; text-align: left;'>COFFEE STOCKS</th>");
			            out.println("</tr>");
			            
			            while(rslt.next()) {
			                
			                int cofeNo = rslt.getInt("cofeId");
			                String c_name = rslt.getString("cofeName");
			                int cofePrice = rslt.getInt("cofePrice");
			                int cofeStock = rslt.getInt("cStocks");
			                
			                out.println("<tr>");
			                    out.println("<td class = \"cofeNumber\" style='padding: 10px;'>" + cofeNo + "</td>");
			                    out.println("<td class = \"cofeName\" style='padding: 10px;'>" + c_name + "</td>");
			                    out.println("<td class = \"cofePrice\" style='padding: 10px;'>" + cofePrice + "</td>");
			                    out.println("<td class = \"cofeStock\" style='padding: 10px;'>" + cofeStock + "</td>");
			                out.println("</tr>");
			            }
			        out.println("</table>");
			    out.println("</body>");
			out.println("</html>");

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public static void doDeleteCofeNow(Connection con, PreparedStatement pstmnt, PrintWriter out) {
		
		String sqlQur = "delete from cofeDetails where cofeId = ?";
		
		try {
			pstmnt = con.prepareStatement(sqlQur);
			
			pstmnt.setInt(1, cofeNoDelete);
			
			pstmnt.executeUpdate();
			
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			    out.println("<head>");
			        out.println("<title>DELETION SUCCESS</title>");
			    out.println("</head>");
			    
			    out.println("<body style='font-family: Arial, sans-serif; text-align: center; margin-top: 50px;'>");
			        
			        out.println("<h2 style='color: #4CAF50; font-size: 24px;'>DELETED SUCCESSFULLY</h2>");
			        
			        out.println("<a href = \"AdminHome.jsp\" style='font-size: 18px; color: #007BFF; text-decoration: none; padding: 10px 20px; background-color: #f1f1f1; border-radius: 5px;'>HOME PAGE</a>");
			        
			    out.println("</body>");
			out.println("</html>");

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void doStockInq(Connection con, Statement stmnt,ResultSet rslt, PrintWriter out) {
		
		String sqlQur = "select cofeId, cofeName, cStocks from cofeDetails";
		
		try {
			stmnt = con.createStatement();
			rslt = stmnt.executeQuery(sqlQur);
			

			out.println("<a href = \"AdminHome.jsp\">BACK</a>");
			
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			    out.println("<head>");
			        out.println("<title>COFFEE STOCKS</title>");
			        out.println("<style>");
			            out.println("body { font-family: Arial, sans-serif; margin: 20px; }");
			            out.println("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
			            out.println("th, td { padding: 12px; text-align: center; border: 1px solid #ddd; }");
			            out.println("th { background-color: #4CAF50; color: white; }");
			            out.println("input[type='number'] { padding: 8px; width: 100px; text-align: center; }");
			            out.println(".submit-btn { padding: 8px 16px; background-color: #4CAF50; color: white; border: none; cursor: pointer; }");
			            out.println(".submit-btn:hover { background-color: #45a049; }");
			        out.println("</style>");
			    out.println("</head>");
			    
			    out.println("<body>");
			    
			        // Hidden Form
			        out.println("<form id='updateDbForm' action = \"cofeDetailsServlet\" style='display:none;'>");
			            out.println("<input type='hidden' id='hiddenCNo' name='CnumberStChange'>");
			            out.println("<input type='hidden' id='hiddenStocks' name='cStock'>");
			            out.println("<input type='hidden' name='work' value = \"doUpdateStock\">");
			        out.println("</form>");
			        
			        out.println("<h2 style='text-align: center;'>Coffee Stock Management</h2>");
			        
			        out.println("<table>");
			        
			        out.println("<tr>");
			            out.println("<th>COFFEE NO.</th>");
			            out.println("<th>COFFEE NAME</th>");
			            out.println("<th>COFFEE OLD STOCK</th>");
			            out.println("<th>COFFEE NEW STOCK</th>");
			            out.println("<th>UPDATE NOW</th>");
			        out.println("</tr>");
			        
			        while(rslt.next()) {
			            int cofeId = rslt.getInt("cofeId");
			            String cofeName = rslt.getString("cofeName");
			            int cStocks = rslt.getInt("cStocks");
			            
			            out.println("<tr>");
			                out.println("<td class='cofeNumber'>" + cofeId + "</td>");
			                out.println("<td class='cofeName'>" + cofeName + "</td>");
			                out.println("<td class='cofeOldStocks'>" + cStocks + "</td>");
			                out.println("<td><input type='number' class='cofeNewStock' required></td>");
			                
			                out.println("<td>");
			                out.println("<button type='button' class='submit-btn'>UPDATE</button>");
			                out.println("</td>");
			            out.println("</tr>");
			        }
			        
			        out.println("</table>");
			        
			        out.println("<script>");
			            out.println("document.addEventListener('DOMContentLoaded', function () {");
			            out.println("    document.querySelectorAll('.submit-btn').forEach(button => {");
			            out.println("        button.addEventListener('click', function () {");
			            out.println("            let row = button.closest('tr');");
			            out.println("            let Cnumber = row.querySelector('.cofeNumber').innerText;");
			            out.println("            let cNstock = row.querySelector('.cofeNewStock').value;");
			            
			            out.println("            if (!cNstock) {");
			            out.println("                alert('Please enter new stock amount!!');");
			            out.println("                return;");
			            out.println("            }");
			            
			            out.println("            document.getElementById('hiddenCNo').value = Cnumber;");
			            out.println("            document.getElementById('hiddenStocks').value = cNstock;");
			            
			            out.println("            document.getElementById('updateDbForm').submit();");
			            out.println("            alert('COFFEE STOCK UPDATED');");
			            out.println("        });");
			            out.println("    });");
			            out.println("});");
			        out.println("</script>");
			        
			    out.println("</body>");
			out.println("</html>");


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public static void doStockUpdate(Connection con,PreparedStatement pstmnt) {
		
		String sqlQur = "update cofeDetails set cStocks = ? where cofeId = ?";
		
		try {
			pstmnt = con.prepareStatement(sqlQur);
			pstmnt.setInt(1, cofeNStock);
			pstmnt.setInt(2, cofeNoForStock);
			
			pstmnt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}








