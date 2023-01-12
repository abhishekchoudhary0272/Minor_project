package com.agrify;

import java.io.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * r
 */
public class r extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String fname = request.getParameter("fName");
			String userName = request.getParameter("uName");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String cnfPassword = request.getParameter("cnfPassword");
			String number = request.getParameter("number");
			String buyer = request.getParameter("buyer");
			String seller = request.getParameter("seller");
			System.out.println("Full name = " + fname);
			System.out.println("User name = " + userName);
			System.out.println("Email = " + email);
			System.out.println("Password = " + password);
			System.out.println("Confirm password = " + cnfPassword);
			System.out.println("Number = " + number);
			System.out.println("Buyer = " + buyer);
			System.out.println("Seller = " + seller);

			// JDBC mysql test
			Connection connection = null;
			try {
				// Old class do not use
				// Class.forName("com.mysql.jdbc.Driver");
				// New class, but class should now loaded automatically although will still
				// produce warning
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mandi", "chinmay", "chinmay");
				Statement statement;
				statement = connection.createStatement();
				ResultSet resultSet;
				resultSet = statement.executeQuery("SELECT * FROM customers");
				while (resultSet.next()) {
					System.out.println("id: " + resultSet.getString("id") + " name: " + resultSet.getString("name"));
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			;
			PrintWriter pw;
			pw = response.getWriter();
			response.setContentType("text/html");
			pw.println("<!DOCTYPE HTML>");
			pw.println("<html lang=en>");
			pw.println("<head>");
			pw.println("<meta charset='utf-8'>");
			pw.println("<title>something</title>");
			pw.println("<script>");

			// TODO: Fix null pointer exception when one of the option is not selected
			try {
				if (buyer.equals("on")) {
					pw.println("location.href = \"/Agrify/b\";");
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			try {
				if (seller.equals("on")) {
					pw.println("location.href = \"/Agrify/s\"");
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			pw.println("</script>");
			pw.println("</head>");
			pw.println("<body>");
			pw.println("<p>Hello from the server</p>");
			pw.println("</body>");
			pw.println("</html>");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
