package com.agrify.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import com.agrify.dl.user.UserDAOImpl;
import com.agrify.dl.user.UserDTO;
import com.agrify.util.Cookie;
import com.agrify.util.Validation;

/**
 * Index
 */
public class Index extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String cookie_name = "user_data_cookie";
			javax.servlet.http.Cookie cookie = Cookie.getCookie(request, cookie_name);

			// If there is a cookie determine the user and show him his profile page
			if (cookie == null) {
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.forward(request, response);
				return;
			}
			
			JSONObject user_data_cookie = Cookie.getCookieData(request, cookie_name);
			
			if (user_data_cookie == null) {
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.forward(request, response);
				return;
			}

			System.out.println(user_data_cookie);

			String id = user_data_cookie.get("id").toString();
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

			UserDTO user = new UserDTO();
			user.setId(id);
			UserDAOImpl userDAO = new UserDAOImpl();
			userDAO.selectUser(user);
			String user_role = user.getUser_role().toString();

			// If the email and password are valid log the user in
			if (user_role.equals("Buyer")) {
				RequestDispatcher rd = request.getRequestDispatcher("/index_logged_in.html");
				response.addCookie(cookie);
				rd.forward(request, response);
			} else if (user_role.equals("Seller")) {
				RequestDispatcher rd = request.getRequestDispatcher("/index_logged_in.html");
				response.addCookie(cookie);
				rd.forward(request, response);
			} else {
				// Server the default page
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
