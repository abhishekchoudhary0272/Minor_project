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

/**
 * r
 */
public class Registration extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {

			String fName = request.getParameter("fName");
			String lName = request.getParameter("lName");
			String email = request.getParameter("email");
			String pNo = request.getParameter("number");
			String password = request.getParameter("password");
			String govtNum = request.getParameter("govtNum");
			String cnfPassword = request.getParameter("cnfPassword");
			String dateOfBirth = request.getParameter("dateOfBirth");
			String gender = request.getParameter("gender");
			String buyerSeller = request.getParameter("BuyerSeller");

			Validation valid = new Validation();
			boolean fNameValid = valid.validString(fName, 60, false);
			boolean lNameValid = valid.validString(lName, 60, false);
			boolean passwordValid = valid.validString(password, 30, false);
			boolean emailValid = valid.mailCheck(email);
			boolean pNoValid = valid.validString(pNo, 20, false);
			boolean govtNumValid = valid.validString(govtNum, 12, false);

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

			System.out.println("hahahaha");

			// Validation
			if (fNameValid == false || lNameValid == false || passwordValid == false || emailValid == false
					|| pNoValid == false || govtNumValid == false) {
				try {
					RequestDispatcher rd = request.getRequestDispatcher("/registration.html");
					rd.forward(request, response);
				} catch (Exception ase) {
					System.out.println(ase);
				}
			}
			if (buyerSeller.equals("buyer") == true) {
				BuyerDTO buyer = new BuyerDTO();
				buyer.setFirst_name(fName);
				buyer.setLast_name(lName);
				buyer.setPassword(password);
				buyer.setEmail(email);
				buyer.setPhone_number(pNo);
				buyer.setBirth(dateOfBirth);
				buyer.setAadhaar_id(govtNum);
				BuyerDAOImpl buyerDAO = new BuyerDAOImpl();
				buyerDAO.insertBuyer(buyer);

				// Create a Map to store data
				final Map<String, Object> data = new HashMap<String, Object>();
				data.put("id", buyer.getId());
				data.put("first_name", buyer.getFirst_name());
				data.put("last_name", buyer.getLast_name());
				data.put("birth", buyer.getBirth());
				data.put("email", buyer.getEmail());
				data.put("password", buyer.getPassword());
				data.put("phone_number", buyer.getPhone_number());
				data.put("aadhaar_id", buyer.getAadhaar_id());

				final JSONObject json_string = new JSONObject(data);

				// Encoding the cookie data into base64 to avoid using unsupported characters
				final String user_data_cookie = Base64.getEncoder().encodeToString((json_string.toString()).getBytes());

				// Cookies accept strings as value so change json to string
				Cookie ck = new Cookie("user_data_cookie", user_data_cookie);
				response.addCookie(ck);

				RequestDispatcher rd = request.getRequestDispatcher("/buyer_profile.html");
				rd.forward(request, response);
			} else {
				SellerDTO seller = new SellerDTO();
				seller.setFirst_name(fName);
				seller.setLast_name(lName);
				seller.setBirth(dateOfBirth);
				seller.setPassword(password);
				seller.setEmail(email);
				seller.setPhone_number(pNo);
				seller.setAadhaar_id(govtNum);
				SellerDAOImpl sellerDAO = new SellerDAOImpl();
				sellerDAO.insertSeller(seller);

				// Create a Map to store data
				final Map<String, Object> data = new HashMap<String, Object>();
				data.put("id", seller.getId());
				data.put("first_name", seller.getFirst_name());
				data.put("last_name", seller.getLast_name());
				data.put("birth", seller.getBirth());
				data.put("email", seller.getEmail());
				data.put("password", seller.getPassword());
				data.put("phone_number", seller.getPhone_number());
				data.put("aadhaar_id", seller.getAadhaar_id());

				final JSONObject json_string = new JSONObject(data);

				// Encoding the cookie data into base64 to avoid using unsupported characters
				final String user_data_cookie = Base64.getEncoder().encodeToString((json_string.toString()).getBytes());

				// Cookies accept strings as value so change json to string
				Cookie ck = new Cookie("user_data_cookie", user_data_cookie);
				response.addCookie(ck);

				RequestDispatcher rd = request.getRequestDispatcher("/seller_profile.html");
				rd.forward(request, response);
			}
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
}
