package com.agrify;

import javax.servlet.*;
import javax.servlet.http.*;

public class b extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String fName = request.getParameter("fName");
			String uName = request.getParameter("uName");
			String email = request.getParameter("email");
			System.out.println("First name" + fName);
			System.out.println("User name" + uName);
			System.out.println("Email" + email);

			RequestDispatcher rd = request.getRequestDispatcher("/buyer_page.html");
			rd.forward(request, response);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
