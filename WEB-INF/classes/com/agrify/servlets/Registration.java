package com.agrify.servlets;
import com.agrify.dl.*;
import com.agrify.dl.buyer.*;
import com.agrify.dl.seller.*;
import java.io.*;
import java.text.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * r
 */
public class Registration extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			//varibales 
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String fName = request.getParameter("fName");
			String lName = request.getParameter("lName");
			String email = request.getParameter("email");
			String pNo = request.getParameter("number");
			String password = request.getParameter("password");
			String govtNum = request.getParameter("govtNum");
			String cnfPassword = request.getParameter("cnfPassword");
			java.util.Date dateOfBirth = simpleDateFormat.parse(request.getParameter("dateOfBirth"));
			String gender = request.getParameter("gender");
			String buyerSeller = request.getParameter("BuyerSeller");

			System.out.println("Full name = " + fName);
			System.out.println("Last name = " + lName);
			System.out.println("Email = " + email);
			System.out.println("Phone number = "+pNo);
			System.out.println("Password = " + password);
			System.out.println("Confirm password = " + cnfPassword);
			System.out.println("Date of birth = "+dateOfBirth);
			System.out.println("Gender = "+ gender);
			System.out.println("BuyerSeller = "+ buyerSeller);
			
			PrintWriter pw;
			pw = response.getWriter();
			response.setContentType("text/html");
			pw.println("<!DOCTYPE HTML>");
			pw.println("<html lang=en>");
			pw.println("<head>");
			pw.println("<meta charset='utf-8'>");
			pw.println("<title>something</title>");
			pw.println("<script>");
			System.out.println("hahahaha");
			if(buyerSeller.equals("buyer")==true){
				BuyerDTO buyer = new BuyerDTO();
				buyer.setId(237);
				buyer.setFirst_name(fName);
				buyer.setLast_name(lName);
				buyer.setPassword(password);
				buyer.setEmail(email);
				buyer.setPhone_number(pNo);
				buyer.setBirth(dateOfBirth);
				buyer.setAadhaar_id(govtNum);
				BuyerDAOImpl buyerDAO = new BuyerDAOImpl();
				buyerDAO.insertBuyer(buyer);
				pw.println("location.href = \"/Agrify/buyer\"");
			}
			else{
				SellerDTO seller = new SellerDTO();
				seller.setId(457);
				seller.setFirst_name(fName);
				seller.setLast_name(lName);
				seller.setBirth(dateOfBirth);
				seller.setPassword(password);
				seller.setEmail(email);
				seller.setPhone_number(pNo);
				seller.setAadhaar_id(govtNum);
				SellerDAOImpl sellerDAO = new SellerDAOImpl();
				sellerDAO.insertSeller(seller);
				pw.println("location.href = \"/Agrify/seller\"");
			}		
			pw.println("</script>");
			pw.println("</head>");
			pw.println("<body>");
			pw.println("<p>Hello from the server</p>");
			pw.println("</body>");
			pw.println("</html>");
		} catch (Exception e) {
			System.out.println(e);
			try{
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.forward(request, response);
			}catch(Exception se){
				System.out.println(se);
			}	
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		doGet(request,response);
	}
}
