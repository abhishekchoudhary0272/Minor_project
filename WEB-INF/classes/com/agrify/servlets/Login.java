package com.agrify.servlets;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.agrify.dl.buyer.BuyerDAOImpl;
import com.agrify.dl.buyer.BuyerDTO;
import com.agrify.dl.seller.SellerDAOImpl;
import com.agrify.dl.seller.SellerDTO;
import com.agrify.util.Validation;

public class Login extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {

			String email = request.getParameter("email");
			String password = request.getParameter("password");
			System.out.println("email : " + email);
			System.out.println("password : " + password);

			Validation valid = new Validation();
			boolean emailValid = valid.validString(email, 40, false);
			boolean passwordValid = valid.validString(password, 30, false);
			System.out.println("emailValid " + emailValid);
			System.out.println("passwordValid " + passwordValid);

			if (emailValid == false || passwordValid == false) {
				try {
					RequestDispatcher rd = request.getRequestDispatcher("/registration.html");
					rd.forward(request, response);
				} catch (Exception ase) {
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

			if (isBuyer == true) {
				boolean b = buyerDAO.Validation(buyer);
				if (b == true) {

					// Create a Map to store data
					final Map<String, Object> data = new HashMap<String, Object>();
					data.put("email", buyer.getEmail());
					data.put("password", buyer.getPassword());

					final JSONObject json_string = new JSONObject(data);

					// Encoding the cookie data into base64 to avoid using unsupported characters
					final String cookie_data = Base64.getEncoder().encodeToString((json_string.toString()).getBytes());

					// Cookies accept strings as value so change json to string
					Cookie ck = new Cookie("cookie_data", cookie_data);
					response.addCookie(ck);

					RequestDispatcher rd = request
							.getRequestDispatcher("/buyer_profile.html");
					rd.forward(request, response);
				} else {
					RequestDispatcher rd = request.getRequestDispatcher("/login.html");
					rd.forward(request, response);
				}
			} else if (isSeller == true) {
				boolean s = sellerDAO.Validation(seller);
				if (s == true) {

					// Create a Map to store data
					final Map<String, Object> data = new HashMap<String, Object>();
					data.put("email", seller.getEmail());
					data.put("password", seller.getPassword());

					final JSONObject json_string = new JSONObject(data);

					// Encoding the cookie data into base64 to avoid using unsupported characters
					final String cookie_data = Base64.getEncoder().encodeToString((json_string.toString()).getBytes());

					// Cookies accept strings as value so change json to string
					Cookie ck = new Cookie("cookie_data", cookie_data);
					response.addCookie(ck);

					RequestDispatcher rd = request
							.getRequestDispatcher("/seller_profile.html");
					rd.forward(request, response);
				} else {
					RequestDispatcher rd = request.getRequestDispatcher("/login.html");
					rd.forward(request, response);
				}
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}