package com.agrify.servlets;

import javax.servlet.*;
import javax.servlet.http.*;

public class Buyer extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String fName = request.getParameter("fName");
			String uName = request.getParameter("uName");
			String email = request.getParameter("email");
			System.out.println(fName);
			System.out.println(uName);
			System.out.println(email);
			
			RequestDispatcher rd = request.getRequestDispatcher("/buyer_page.html");
			rd.forward(request, response);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
