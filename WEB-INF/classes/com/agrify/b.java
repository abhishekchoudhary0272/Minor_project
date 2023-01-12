package com.agrify;

import javax.servlet.*;
import javax.servlet.http.*;

public class b extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String fName = request.getParameter("fName");
			String uName = request.getParameter("uName");
			String email = request.getParameter("email");
			System.out.println(fName);
			System.out.println(uName);
			System.out.println(email);
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
			 * pw.println("<p>You are a buyer</p>");
			 * pw.println("</body>");
			 * pw.println("</html>");
			 */

			RequestDispatcher rd = request.getRequestDispatcher("/buyer_page.html");
			rd.forward(request, response);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
