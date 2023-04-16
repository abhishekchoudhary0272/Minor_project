package com.agrify.servlets;

import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.agrify.dl.buyer.BuyerDAOImpl;
import com.agrify.dl.buyer.BuyerDTO;
import com.agrify.dl.seller.SellerDAOImpl;
import com.agrify.dl.seller.SellerDTO;
import com.agrify.util.Validation;

/**
 * Index
 */
public class Index extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			javax.servlet.http.Cookie[] ck = request.getCookies();

			// If there is a cookie determine the user and show him his profile page
			if (ck != null) {
				String data = ck[0].getValue();
				if (!data.equals("") || data != null) {

					String data_string = new String(Base64.getDecoder().decode(data));
					System.out.println(data_string);

					JSONParser parser = new JSONParser();
					JSONObject user_data_cookie = (JSONObject) parser.parse(data_string);

					System.out.println(user_data_cookie);

					String email = user_data_cookie.get("email").toString();
					String password = user_data_cookie.get("password").toString();

					Validation valid = new Validation();
					boolean emailValid = valid.mailCheck(email);
					boolean passwordValid = valid.validString(password, 30, false);

					if (emailValid == false || passwordValid == false) {
						System.out.println("Cookie contains corrupt data");
						RequestDispatcher rd = request.getRequestDispatcher("/index.html");
						rd.forward(request, response);
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

					// If the email and password are valid log the user in
					if (isBuyer) {
						boolean b = buyerDAO.Validation(buyer);
						if (b) {
							RequestDispatcher rd = request.getRequestDispatcher("/buyer_profile.html");
							response.addCookie(ck[0]);
							rd.forward(request, response);
						}

					} else if (isSeller) {
						boolean s = sellerDAO.Validation(seller);
						if (s) {
							RequestDispatcher rd = request.getRequestDispatcher("/seller_profile.html");
							response.addCookie(ck[0]);
							rd.forward(request, response);
						}
					} else {
						// Server the default page
						RequestDispatcher rd = request.getRequestDispatcher("/index.html");
						rd.forward(request, response);
					}
				}
			} else {
				// Server the default page
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
