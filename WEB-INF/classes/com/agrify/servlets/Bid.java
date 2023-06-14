package com.agrify.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import com.agrify.dl.auction.bid.*;
import com.agrify.util.Cookie;

import org.json.simple.JSONObject;
import java.sql.Timestamp;

public class Bid extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			String bidAmount = request.getParameter("bid");
			System.out.println(bidAmount);
			BidDTO bid = new BidDTO();
			bid.setOffer(bidAmount);
			String cookie_name = "auction_data_cookie";
			javax.servlet.http.Cookie cookie = Cookie.getCookie(request, cookie_name);
			if (cookie == null) {
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.forward(request, response);
				return;
			}
			JSONObject auction_data_cookie = Cookie.getCookieData(request, cookie_name);
			if (auction_data_cookie == null) {
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.forward(request, response);
				return;
			}
			System.out.println(auction_data_cookie);
			String auction_id = auction_data_cookie.get("auction_id").toString();
			String cookie_na = "user_data_cookie";
			javax.servlet.http.Cookie cooki = Cookie.getCookie(request, cookie_na);
			if (cooki == null) {
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.forward(request, response);
				return;
			}
			JSONObject user_data_cookie = Cookie.getCookieData(request, cookie_na);
			if (user_data_cookie == null) {
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.forward(request, response);
				return;
			}
			System.out.println(user_data_cookie);
			String offerer_id = user_data_cookie.get("id").toString();
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String bid_timeString = timestamp.toString();
			bid.setBid_timestamp(bid_timeString);
			bid.setAuction_id(auction_id);
			bid.setOfferer_id(offerer_id);
			BidDAOImpl bidDAO = new BidDAOImpl();
			bidDAO.insertBid(bid);
			System.out.println("hey");
			RequestDispatcher rd = request.getRequestDispatcher("/buyer_auction_page.html");
			rd.forward(request, response);
			System.out.println("hello");
		} catch (Exception e) {
			System.out.println(e);
			try {
				RequestDispatcher rd = request.getRequestDispatcher("/Agrify/index.html");
				rd.forward(request, response);
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
	}
}