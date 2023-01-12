package com.agrify;

import java.io.*;
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
			/*
			 * PrintWriter pw;
			 * pw = response.getWriter();
			 * response.setContentType("text/html");
			 * pw.println("<!DOCTYPE HTML>");
			 * pw.println("<html lang=en>");
			 * pw.println("<head>");
			 * pw.println("<meta charset='utf-8'>");
			 * pw.println("<title>something</title>");
			 * pw.println("</head>");
			 * pw.println("<body>");
			 * pw.println("<p>You are a seller</p>");
			 * pw.println("</body>");
			 * pw.println("</html>");
			 */

			RequestDispatcher rd = request.getRequestDispatcher("/seller.html");
			rd.forward(request, response);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
