package com.agrify.servlets;

// import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Seller extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String name = request.getParameter("fname");
			String city = request.getParameter("lname");
			String gender = request.getParameter("gdr");
			System.out.println("geneder = " + gender);
			System.out.println("name = " + name);
			System.out.println("city = " + city);

			RequestDispatcher rd = request.getRequestDispatcher("/seller.html");
			rd.forward(request, response);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			String name = request.getParameter("fname");
			String city = request.getParameter("lname");
			String gender = request.getParameter("gdr");
			System.out.println("geneder = " + gender);
			System.out.println("name = " + name);
			System.out.println("city = " + city);

			RequestDispatcher rd = request.getRequestDispatcher("/seller.html");
			rd.forward(request, response);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
