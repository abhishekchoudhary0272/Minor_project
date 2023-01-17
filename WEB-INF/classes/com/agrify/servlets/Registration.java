package com.agrify.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


/**
 * r
 */
public class Registration extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			// varibales
			String fName = request.getParameter("fName");
			String userName = request.getParameter("uName");
			String email = request.getParameter("email");
			String pNo = request.getParameter("number");
			String password = request.getParameter("password");
			String govtNum = request.getParameter("govtNum");
			String cnfPassword = request.getParameter("cnfPassword");
			String gender = request.getParameter("gender");
			String buyerSeller = request.getParameter("BuyerSeller");

			System.out.println("Full name = " + fName);
			System.out.println("User name = " + userName);
			System.out.println("Email = " + email);
			System.out.println("Phone number = " + pNo);
			System.out.println("Password = " + password);
			System.out.println("Confirm password = " + cnfPassword);
			System.out.println("Gender = " + gender);
			System.out.println("BuyerSeller = " + buyerSeller);

			PrintWriter pw;
			pw = response.getWriter();
			response.setContentType("text/html");
			pw.println("<!DOCTYPE HTML>");
			pw.println("<html lang=en>");
			pw.println("<head>");
			pw.println("<meta charset='utf-8'>");
			pw.println("<title>something</title>");
			pw.println("<script>");
			if (buyerSeller.equals("seller") == true) {
				pw.println("location.href = \"/Agrify/s\"");
			} else {
				pw.println("location.href = \"/Agrify/b\"");
			}

			pw.println("</script>");
			pw.println("</head>");
			pw.println("<body>");
			pw.println("<p>Hello from the server</p>");
			pw.println("</body>");
			pw.println("</html>");
		} catch (Exception e) {
			System.out.println(e);
			try {
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.forward(request, response);
			} catch (Exception se) {
				System.out.println(se);
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
