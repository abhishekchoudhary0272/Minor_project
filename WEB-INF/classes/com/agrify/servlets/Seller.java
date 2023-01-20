package com.agrify.servlets;

import javax.servlet.*;
import javax.servlet.http.*;

public class Seller extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String name = request.getParameter("fname");
			String city = request.getParameter("lname");
			String gender = request.getParameter("gdr");
			System.out.println("name = " + name);
			System.out.println("city = " + city);
			System.out.println("gender = " + gender);
			
			RequestDispatcher rd = request.getRequestDispatcher("/seller.html");
			rd.forward(request, response);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
