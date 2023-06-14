package com.agrify.servlets;

import com.agrify.dl.user.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Admin
 */
public class Admin extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			UserDTO user = new UserDTO();
			user.setEmail(email);
			user.setPassword(password);
			UserDAOImpl userDAO = new UserDAOImpl();
			boolean valid = userDAO.validation(user);
			System.out.println(valid);
			if (valid == true) {
				System.out.print("hey");
				user = userDAO.selectUser(user);
				String role = user.getUser_role().toString();
				if (role.equals("Admin")) {
					RequestDispatcher rd = request.getRequestDispatcher("/admin-panel.html");
					rd.forward(request, response);
				} else {
					RequestDispatcher rd = request.getRequestDispatcher("/admin-login.html");
					rd.forward(request, response);
				}
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/admin-login.html");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			RequestDispatcher rd = request.getRequestDispatcher("/admin-login.html");
			rd.forward(request, response);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
