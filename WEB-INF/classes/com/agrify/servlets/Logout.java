package com.agrify.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Logout
 */
public class Logout extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			javax.servlet.http.Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					cookie.setValue("");
					cookie.setPath("/");
					cookie.setMaxAge(0);
				}
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.forward(request, response);
			} else {
				// Serve the default page
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			javax.servlet.http.Cookie[] ck = request.getCookies();
			if (ck != null) {
				String data = ck[0].getValue();
				if (!data.equals("") || data != null) {
					ck[0].setValue("");
					ck[0].setPath("/");
					ck[0].setMaxAge(0);

					response.addCookie(ck[0]);

					RequestDispatcher rd = request.getRequestDispatcher("/index.html");
					rd.forward(request, response);
				}
			} else {
				// Serve the default page
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
