package com.agrify.servlets;

// import com.agrify.dl.*;
import com.agrify.util.*;
import com.agrify.dl.buyer.*;
import com.agrify.dl.seller.*;
import java.io.*;
// import java.text.*;
import javax.servlet.*;
import javax.servlet.http.*;
// import java.sql.*;
// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;

/**
 * r
 */
public class Registration extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			// Varibales
			// SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String fName = request.getParameter("fName");
			String lName = request.getParameter("lName");
			String email = request.getParameter("email");
			String pNo = request.getParameter("number");
			String password = request.getParameter("password");
			String govtNum = request.getParameter("govtNum");
			String cnfPassword = request.getParameter("cnfPassword");
			// java.util.Date dateOfBirth =
			// simpleDateFormat.parse(request.getParameter("dateOfBirth"));
			String dateOfBirth = request.getParameter("dateOfBirth");
			String gender = request.getParameter("gender");
			String buyerSeller = request.getParameter("BuyerSeller");

			Validation valid = new Validation();
			boolean fNameValid = valid.validString(fName, 60, false);
			boolean lNameValid = valid.validString(lName, 60, false);
			boolean passwordValid = valid.validString(password, 30, false);
			boolean emailValid = valid.validString(email, 40, false);
			// boolean dateValid = valid.validString(date,,false);
			boolean pNoValid = valid.validString(pNo, 20, false);
			boolean govtNumValid = valid.validString(govtNum, 12, false);

			boolean mailValid = valid.mailCheck(email);

			System.out.println("Full name = " + fName);
			System.out.println("Last name = " + lName);
			System.out.println("Email = " + email);
			System.out.println("Phone number = " + pNo);
			System.out.println("Password = " + password);
			System.out.println("Confirm password = " + cnfPassword);
			System.out.println("Date of birth = " + dateOfBirth);
			System.out.println("Gender = " + gender);
			System.out.println("BuyerSeller = " + buyerSeller);
			System.out.println(fNameValid);
			System.out.println(lNameValid);
			System.out.println(passwordValid);
			System.out.println(emailValid);
			System.out.println(pNoValid);
			System.out.println(govtNumValid);
			System.out.println(mailValid);

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
			
			// Validation
			if (fNameValid == false || lNameValid == false || passwordValid == false || emailValid == false
					|| pNoValid == false || govtNumValid == false || mailValid == false) {
				try {
					RequestDispatcher rd = request.getRequestDispatcher("/registration.html");
					rd.forward(request, response);
				} catch (Exception ase) {
					System.out.println(ase);
				}
			}
			if (buyerSeller.equals("buyer") == true) {
				BuyerDTO buyer = new BuyerDTO();
				// buyer.setId(243);
				buyer.setFirst_name(fName);
				buyer.setLast_name(lName);
				buyer.setPassword(password);
				buyer.setEmail(email);
				buyer.setPhone_number(pNo);
				buyer.setBirth(dateOfBirth);
				buyer.setAadhaar_id(govtNum);
				BuyerDAOImpl buyerDAO = new BuyerDAOImpl();
				buyerDAO.insertBuyer(buyer);
				pw.println("location.href = \"/Agrify/b\"");
			} else {
				SellerDTO seller = new SellerDTO();
				// seller.setId(458);
				seller.setFirst_name(fName);
				seller.setLast_name(lName);
				seller.setBirth(dateOfBirth);
				seller.setPassword(password);
				seller.setEmail(email);
				seller.setPhone_number(pNo);
				seller.setAadhaar_id(govtNum);
				SellerDAOImpl sellerDAO = new SellerDAOImpl();
				sellerDAO.insertSeller(seller);
				pw.println("location.href = \"/Agrify/s\"");
			}
			pw.println("</script>");
			pw.println("</head>");
			pw.println("<body>");
			pw.println("<p>Hello from the server</p>");
			pw.println("</body>");
			pw.println("</html>");
		} catch (Exception e) {
			System.out.println(e);
			try {
				RequestDispatcher rd = request.getRequestDispatcher("/registration.html");
				rd.forward(request, response);
			} catch (Exception se) {
				System.out.println(se);
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
