package com.agrify.servlets;

import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.agrify.dl.auction.AuctionDTO;
import com.agrify.util.Validation;

public class NewAuction extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			String auctionName = request.getParameter("auction_Name");
			String itemName = request.getParameter("item_Name");
			String quantity = request.getParameter("number");
			String startingBidPrice = request.getParameter("Starting_bid_price");
			String durationOfAuction = request.getParameter("duration");

			Validation valid = new Validation();
			boolean auctionNameValid = valid.validString(auctionName, 60, false);
			boolean itemNameValid = valid.validString(itemName, 60, false);
			boolean quantityValid = valid.validString(quantity, 60, false);
			boolean startingBidPriceValid = valid.validString(startingBidPrice, 60, false);
			boolean durationOfAuctionValid = valid.validString(durationOfAuction, 60, false);

			System.out.println("auction name : " + auctionName);
			System.out.println("item name : " + itemName);
			System.out.println("quantity : " + quantity);
			System.out.println("starting Bid Price : " + startingBidPrice);
			System.out.println("duration Of Auction : " + durationOfAuction);
			System.out.println("auction Name Valid : " + auctionNameValid);
			System.out.println("item Name Valid : " + itemNameValid);
			System.out.println("quantity Valid : " + quantityValid);
			System.out.println("starting Bid Price Valid : " + startingBidPrice);
			System.out.println("duration Of Auction Valid : " + durationOfAuctionValid);

			if (auctionNameValid == false || itemNameValid == false || quantityValid == false
					|| startingBidPriceValid == false || durationOfAuctionValid == false) {
				try {
					RequestDispatcher rd = request.getRequestDispatcher("/");
					rd.forward(request, response);
				} catch (Exception ase) {
					System.out.println(ase);
				}
			}
			AuctionDTO auction = new AuctionDTO();
			auction.setName(auctionName);
			// auction.setItem_id();
			auction.setQuantity_kg(quantity);
			auction.setStart_bid(startingBidPrice);

			javax.servlet.http.Cookie[] ck = request.getCookies();
			if (ck != null) {
				String data = ck[0].getValue();
				if (!data.equals("") || data != null) {
					String data_string = new String(Base64.getDecoder().decode(data));
					JSONParser parser = new JSONParser();
					JSONObject user_data_cookie = (JSONObject) parser.parse(data_string);

				}
			}
		} catch (Exception e) {
			System.out.println(e);
			try {
				RequestDispatcher rd = request.getRequestDispatcher("/registration.html");
				rd.forward(request, response);
			} catch (Exception se) {
				System.out.println(se);
			}
		}
	}
}
