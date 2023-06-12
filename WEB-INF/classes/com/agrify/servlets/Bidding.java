package com.agrify.servlets;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import com.agrify.util.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.agrify.dl.auction.AuctionDAOImpl;
import com.agrify.dl.auction.AuctionDTO;
import com.agrify.dl.user.*;
import com.agrify.util.*;

/**
 * Bidding
 */
public class Bidding extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println("doGet()");
			String cookie_name = "user_data_cookie";
			javax.servlet.http.Cookie cookie = Cookie.getCookie(request, cookie_name);

			// If there is a cookie determine the user and show him his profile page
			if (cookie == null) {
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.forward(request, response);
				return;
			}

			JSONObject user_data_cookie = Cookie.getCookieData(request, cookie_name);

			if (user_data_cookie == null) {
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.forward(request, response);
				return;
			}

			System.out.println(user_data_cookie);

			String id = user_data_cookie.get("id").toString();
			String email = user_data_cookie.get("email").toString();
			String password = user_data_cookie.get("password").toString();

			Validation valid = new Validation();
			boolean emailValid = valid.mailCheck(email);
			boolean passwordValid = valid.validString(password, 30, false);

			if (emailValid == false || passwordValid == false) {
				System.out.println("Cookie contains corrupt data");
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.forward(request, response);
			}

			UserDTO user = new UserDTO();
			user.setId(id);
			UserDAOImpl userDAO = new UserDAOImpl();
			userDAO.selectUser(user);
			String user_role = user.getUser_role().toString();

			// If the email and password are valid log the user in
			if (user_role.equals("Buyer")) {
				Bidding bid = new Bidding();
				bid.doPost(request, response);
			} else if (user_role.equals("Seller")) {
				RequestDispatcher rd = request.getRequestDispatcher("/login.html");
				response.addCookie(cookie);
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				response.addCookie(cookie);
				rd.forward(request, response);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			String cookie_name = "user_data_cookie";
			javax.servlet.http.Cookie cookie = Cookie.getCookie(request, cookie_name);

			// If there is a cookie determine the user and show him his profile page
			if (cookie == null) {
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.forward(request, response);
				return;
			}

			JSONObject user_data_cookie = Cookie.getCookieData(request, cookie_name);

			if (user_data_cookie == null) {
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.forward(request, response);
				return;
			}

			System.out.println(user_data_cookie);

			String id = user_data_cookie.get("id").toString();
			String email = user_data_cookie.get("email").toString();
			String password = user_data_cookie.get("password").toString();

			Validation valid = new Validation();
			boolean emailValid = valid.mailCheck(email);
			boolean passwordValid = valid.validString(password, 30, false);

			if (emailValid == false || passwordValid == false) {
				System.out.println("Cookie contains corrupt data");
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.forward(request, response);
			}

			UserDTO user = new UserDTO();
			user.setId(id);
			UserDAOImpl userDAO = new UserDAOImpl();
			userDAO.selectUser(user);
			String user_role = user.getUser_role().toString();

			// If the email and password are valid log the user in
			if (user_role.equals("Buyer")) {
				String auction_id = request.getParameter("id");
				AuctionDTO auction = new AuctionDTO();
				auction.setId(auction_id);
				AuctionDAOImpl auctionDAOImpl = new AuctionDAOImpl();
				auctionDAOImpl.selectAuction(auction);
				final Map<String, Object> auction_data = new HashMap<>();
				auction_data.put("auction_id", auction_id);
				auction_data.put("name", auction.getName());
				auction_data.put("creator_id", auction.getCreator_id());
				auction_data.put("item_id", auction.getItem_id());
				auction_data.put("quantity_kg", auction.getQuantity_kg());
				auction_data.put("start_bid", auction.getStart_bid());
				auction_data.put("start_time", auction.getStart_time());
				auction_data.put("end_time", auction.getEnd_time());
				final JSONObject json_string = new JSONObject(auction_data);
				final String auction_data_cookie = Base64.getEncoder()
						.encodeToString(json_string.toString().getBytes());
				javax.servlet.http.Cookie ckk = new javax.servlet.http.Cookie("auction_data_cookie",
						auction_data_cookie);
				response.addCookie(ckk);
				RequestDispatcher rd = request.getRequestDispatcher("/buyer_auction_page.html");
				rd.forward(request, response);
			} else if (user_role.equals("Seller")) {
				RequestDispatcher rd = request.getRequestDispatcher("/index_logged_in.html");
				response.addCookie(cookie);
				rd.forward(request, response);
			} else {
				// Server the default page
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
