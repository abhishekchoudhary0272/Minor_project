package com.agrify;

import javax.servlet.*;
import javax.servlet.http.*;

public class l extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			System.out.println("email : " + email);
			System.out.println("password : " + password);

			RequestDispatcher rd = request.getRequestDispatcher("/buyer_page.html");
			rd.forward(request, response);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
