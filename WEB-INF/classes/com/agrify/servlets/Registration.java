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
import com.agrify.dl.user.UserDAOImpl;
import com.agrify.dl.user.UserDTO;
import com.agrify.util.Validation;
import com.agrify.dl.user.UserRole.role;

/**
 * r
 */
public class Registration extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			RequestDispatcher rd = request.getRequestDispatcher("/registration.html");
			rd.forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

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
				UserDTO user = new UserDTO();
				user.setFirst_name(fName);
				user.setLast_name(lName);
				user.setPassword(password);
				user.setEmail(email);
				user.setPhone_number(pNo);
				user.setBirth(dateOfBirth);
				user.setAadhaar_id(govtNum);
				user.setUser_role(role.Buyer);
				UserDAOImpl userDAO = new UserDAOImpl();
				userDAO.insertUser(user);

				// Create a Map to store data
				final Map<String, Object> data = new HashMap<String, Object>();
				data.put("id", user.getId());
				data.put("first_name", user.getFirst_name());
				data.put("last_name", user.getLast_name());
				data.put("birth", user.getBirth());
				data.put("email", user.getEmail());
				data.put("password", user.getPassword());
				data.put("phone_number", user.getPhone_number());
				data.put("aadhaar_id", user.getAadhaar_id());
				data.put("user_role", user.getUser_role().toString());

				final JSONObject json_string = new JSONObject(data);

				// Encoding the cookie data into base64 to avoid using unsupported characters
				final String user_data_cookie = Base64.getEncoder().encodeToString((json_string.toString()).getBytes());

				// Cookies accept strings as value so change json to string
				Cookie ck = new Cookie("user_data_cookie", user_data_cookie);
				response.addCookie(ck);

				RequestDispatcher rd = request.getRequestDispatcher("/buyer_profile.html");
				rd.forward(request, response);
			} else if (buyerSeller.equals("seller")) {
				UserDTO user = new UserDTO();
				user.setFirst_name(fName);
				user.setLast_name(lName);
				user.setPassword(password);
				user.setEmail(email);
				user.setPhone_number(pNo);
				user.setBirth(dateOfBirth);
				user.setAadhaar_id(govtNum);
				user.setUser_role(role.Seller);
				UserDAOImpl userDAO = new UserDAOImpl();
				userDAO.insertUser(user);

				// Create a Map to store data
				final Map<String, Object> data = new HashMap<String, Object>();
				data.put("id", user.getId());
				data.put("first_name", user.getFirst_name());
				data.put("last_name", user.getLast_name());
				data.put("birth", user.getBirth());
				data.put("email", user.getEmail());
				data.put("password", user.getPassword());
				data.put("phone_number", user.getPhone_number());
				data.put("aadhaar_id", user.getAadhaar_id());
				data.put("user_role", user.getUser_role().toString());

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
