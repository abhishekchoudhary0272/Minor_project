package com.agrify.servlets;

import java.util.Base64;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agrify.dl.auction.bid.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.sql.Timestamp;

public class Bid extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			String bidAmount = request.getParameter("bid");
			System.out.println(bidAmount);
			BidDTO bid = new BidDTO();
			bid.setOffer(bidAmount);
			javax.servlet.http.Cookie[] ck = request.getCookies();
			if (ck != null) {
				String data = ck[1].getValue();
				if (!data.equals("") || data != null) {
					String data_string = new String(Base64.getDecoder().decode(data));
					JSONParser parser = new JSONParser();
					JSONObject auction_data_cookie = (JSONObject) parser.parse(data_string);
					System.out.println(auction_data_cookie);
					String auction_id = auction_data_cookie.get("auction_id").toString();
					String offerer_id = auction_data_cookie.get("offerer_id").toString();
					Timestamp timestamp = new Timestamp(System.currentTimeMillis());
					String bid_timeString = timestamp.toString();
					bid.setBid_timestamp(bid_timeString);
					bid.setAuction_id(auction_id);
					bid.setOfferer_id(offerer_id);
					BidDAOImpl bidDAO = new BidDAOImpl();
					bidDAO.insertBid(bid);
					System.out.println("donee");
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}