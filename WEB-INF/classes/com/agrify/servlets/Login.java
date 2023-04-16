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
			boolean emailValid = valid.mailCheck(email);
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
			boolean isBuyer = buyerDAO.Validation(buyer);
			SellerDAOImpl sellerDAO = new SellerDAOImpl();
			boolean isSeller = sellerDAO.Validation(seller);
			System.out.println("isBuyer " + isBuyer);
			System.out.println("isSeller " + isSeller);

			if (isBuyer == true) {
				// Create a Map to store data
				final Map<String, Object> data = new HashMap<String, Object>();
				data.put("id", buyer.getId());
				data.put("first_name", buyer.getFirst_name());
				data.put("last_name", buyer.getLast_name());
				data.put("birth", buyer.getBirth());
				data.put("password", buyer.getPassword());
				data.put("email", buyer.getEmail());
				data.put("phone_number", buyer.getPhone_number());
				data.put("aadhar_id", buyer.getAadhaar_id());

				final JSONObject json_string = new JSONObject(data);

				// Encoding the cookie data into base64 to avoid using unsupported characters
				final String user_data_cookie = Base64.getEncoder()
						.encodeToString((json_string.toString()).getBytes());

				// Cookies accept strings as value so change json to string
				Cookie ck = new Cookie("user_data_cookie", user_data_cookie);
				response.addCookie(ck);
				// response.setHeader("Set-Cookie", "SameSite=Strict");

				RequestDispatcher rd = request
						.getRequestDispatcher("/buyer_profile.html");
				rd.forward(request, response);
			} else if (isSeller == true) {
				// Create a Map to store data
				final Map<String, Object> data = new HashMap<String, Object>();
				data.put("id", seller.getId());
				data.put("first_name", seller.getFirst_name());
				data.put("last_name", seller.getLast_name());
				data.put("birth", seller.getBirth());
				data.put("password", seller.getPassword());
				data.put("email", seller.getEmail());
				data.put("phone_number", seller.getPhone_number());
				data.put("aadhar_id", seller.getAadhaar_id());

				final JSONObject json_string = new JSONObject(data);

				// Encoding the cookie data into base64 to avoid using unsupported characters
				final String user_cookie_data = Base64.getEncoder().encodeToString((json_string.toString()).getBytes());

				// Cookies accept strings as value so change json to string
				Cookie ck = new Cookie("user_cookie_data", user_cookie_data);
				response.addCookie(ck);
				// response.setHeader("Set-Cookie", "SameSite=Strict");

				RequestDispatcher rd = request
						.getRequestDispatcher("/seller_profile.html");
				rd.forward(request, response);

			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/auction_form.html");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}