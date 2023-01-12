package com.agrify;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * s
 */
public class s extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String name = request.getParameter("fname");
			String city = request.getParameter("lname");
			String geneder = request.getParameter("gdr");
			System.out.println("geneder");
			
			System.out.println("name = " + name);
			System.out.println("city = " + city);
			System.out.println("gender = " + geneder);

			RequestDispatcher rd = request.getRequestDispatcher("/seller.html");
			rd.forward(request, response);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
