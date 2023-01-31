package com.agrify.servlets;
// import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
// import com.agrify.dl.*;
import com.agrify.util.*;
import com.agrify.dl.buyer.*;
import com.agrify.dl.seller.*;
// import java.sql.*;
// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;
public class Login extends HttpServlet{
	public void doGet(HttpServletRequest request ,HttpServletResponse response){
		try{
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			System.out.println("email : "+email);
			System.out.println("password : "+password);


			Validation valid = new Validation();
			boolean emailValid = valid.validString(email,40,false);
			boolean passwordValid = valid.validString(password,30,false);
			boolean mailValid = valid.mailCheck(email);
			System.out.println("emailValid "+ emailValid);
			System.out.println("passwordValid "+ passwordValid);
			System.out.println("mailValid "+ mailValid);
			

			if(emailValid == false || passwordValid == false || mailValid == false){
				try{
					RequestDispatcher rd = request.getRequestDispatcher("/registration.html");
					rd.forward(request, response);
				}catch(Exception ase){
					System.out.println(ase);
				}	
			}
			BuyerDTO buyer = new BuyerDTO();
			buyer.setEmail(email);
			buyer.setPassword(password);
			SellerDTO seller = new SellerDTO();
			seller.setEmail(email);
			seller.setPassword(password);
			BuyerDAOImpl buyerDAO = new BuyerDAOImpl(); 
			boolean isBuyer = buyerDAO.isBuyer(buyer);
			SellerDAOImpl sellerDAO = new SellerDAOImpl();
			boolean isSeller = sellerDAO.isSeller(seller);
			
			if(isBuyer == true){
				boolean b = buyerDAO.Validation(buyer);
				if(b==true){
					RequestDispatcher rd = request.getRequestDispatcher("/buyer_page.html");
					rd.forward(request, response);
				}
				else{
					RequestDispatcher rd = request.getRequestDispatcher("/login.html");
					rd.forward(request, response);
				}
			}
			else if(isSeller == true){
				boolean s = sellerDAO.Validation(seller);
				if(s ==true){
					RequestDispatcher rd = request.getRequestDispatcher("/seller.html");
					rd.forward(request, response);
				}
				else{
					RequestDispatcher rd = request.getRequestDispatcher("/login.html");
					rd.forward(request, response);
				}
			}
			else{
				RequestDispatcher rd = request.getRequestDispatcher("/login.html");
				rd.forward(request, response);
			}
			/*RequestDispatcher rd = request.getRequestDispatcher("/buyer_page.html");
			rd.forward(request, response);*/

		}catch(Exception e){
			System.out.println(e);
		}	
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		try{
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			System.out.println("email : "+email);
			System.out.println("password : "+password);


			Validation valid = new Validation();
			boolean emailValid = valid.validString(email,40,false);
			boolean passwordValid = valid.validString(password,30,false);
			boolean mailValid = valid.mailCheck(email);
			System.out.println("emailValid "+ emailValid);
			System.out.println("passwordValid "+ passwordValid);
			System.out.println("mailValid "+ mailValid);
			

			if(emailValid == false || passwordValid == false || mailValid == false){
				try{
					RequestDispatcher rd = request.getRequestDispatcher("/registration.html");
					rd.forward(request, response);
				}catch(Exception ase){
					System.out.println(ase);
				}	
			}


			BuyerDTO buyer = new BuyerDTO();
			buyer.setEmail(email);
			buyer.setPassword(password);
			SellerDTO seller = new SellerDTO();
			seller.setEmail(email);
			seller.setPassword(password);
			BuyerDAOImpl buyerDAO = new BuyerDAOImpl(); 
			boolean isBuyer = buyerDAO.isBuyer(buyer);
			SellerDAOImpl sellerDAO = new SellerDAOImpl();
			boolean isSeller = sellerDAO.isSeller(seller);
			
			if(isBuyer == true){
				boolean b = buyerDAO.Validation(buyer);
				if(b==true){
					RequestDispatcher rd = request.getRequestDispatcher("/buyer_page.html");
					rd.forward(request, response);
				}
				else{
					RequestDispatcher rd = request.getRequestDispatcher("/login.html");
					rd.forward(request, response);
				}
			}
			else if(isSeller == true){
				boolean s = sellerDAO.Validation(seller);
				if(s ==true){
					RequestDispatcher rd = request.getRequestDispatcher("/seller.html");
					rd.forward(request, response);
				}
				else{
					RequestDispatcher rd = request.getRequestDispatcher("/login.html");
					rd.forward(request, response);
				}
			}
			else{
				RequestDispatcher rd = request.getRequestDispatcher("/login.html");
				rd.forward(request, response);
			}
			/*RequestDispatcher rd = request.getRequestDispatcher("/buyer_page.html");
			rd.forward(request, response);*/

		}catch(Exception e){
			System.out.println(e);
		}	
	}
}
