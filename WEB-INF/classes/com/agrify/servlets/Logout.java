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
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				System.out.println("There were cookies but now they are deleted");
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.forward(request, response);
			} else {
				// Serve the default page
				System.out.println("There are no cookies");
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			javax.servlet.http.Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				System.out.println("There were cookies but now they are deleted");
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.forward(request, response);
			} else {
				// Serve the default page
				System.out.println("There are no cookies");
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
