package com.agrify.servlets;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.agrify.dl.auction.AuctionDAOImpl;
import com.agrify.dl.auction.AuctionDTO;

/**
 * Bidding
 */
public class Bidding extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			System.out.println("Auction id = " + id);

			AuctionDTO auction = new AuctionDTO();
			AuctionDAOImpl auctionDAO = new AuctionDAOImpl();
			auction.setId(id);

			String offererId = "";
			javax.servlet.http.Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				String data = cookies[0].getValue();
				if (!data.equals("") || data != null) {
					String data_string = new String(Base64.getDecoder().decode(data));
					JSONParser parser = new JSONParser();
					JSONObject user_data_cookie = (JSONObject) parser.parse(data_string);
					offererId = user_data_cookie.get("id").toString();
				}
			} else {
				System.out.println("Buyer is not logged in");
				RequestDispatcher rd = request.getRequestDispatcher("/login.html");
				rd.forward(request, response);
			}

			if (auctionDAO.isAuction(auction)) {
				// Create a Map to store data
				final Map<String, Object> data = new HashMap<String, Object>();
				data.put("auction_id", auction.getId());
				data.put("creator_id", auction.getCreator_id());
				data.put("name", auction.getName());
				data.put("item_id", auction.getItem_id());
				data.put("quantity_id", auction.getQuantity_kg());
				data.put("start_bid", auction.getStart_bid());
				data.put("start_time", auction.getStart_time());
				data.put("end_time", auction.getEnd_time());
				data.put("offerer_id", offererId);

				final JSONObject json_string = new JSONObject(data);

				// Encoding the cookie data into base64 to avoid using unsupported characters
				final String auction_data_cookie = Base64.getEncoder()
						.encodeToString((json_string.toString()).getBytes());

				// Cookies accept strings as value so change json to string
				Cookie ck = new Cookie("auction_data_cookie", auction_data_cookie);
				response.addCookie(ck);

				RequestDispatcher rd = request.getRequestDispatcher("/buyer_auction_page.html");
				rd.forward(request, response);
			} else {
				System.out.println("The auction you are looking for is not active");
				RequestDispatcher rd = request.getRequestDispatcher("/index_logged_in.html");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			System.out.println("Auction id = " + id);

			AuctionDTO auction = new AuctionDTO();
			AuctionDAOImpl auctionDAO = new AuctionDAOImpl();
			auction.setId(id);

			boolean logged_in = false;
			String offererId = "";
			javax.servlet.http.Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				String data = cookies[0].getValue();
				if (!data.equals("") || data != null) {
					String data_string = new String(Base64.getDecoder().decode(data));
					JSONParser parser = new JSONParser();
					JSONObject user_data_cookie = (JSONObject) parser.parse(data_string);
					offererId = user_data_cookie.get("id").toString();
					logged_in = true;
				}
			} else {
				System.out.println("Buyer is not logged in");
				RequestDispatcher rd = request.getRequestDispatcher("/login.html");
				rd.forward(request, response);
			}

			if (logged_in) {
				if (auctionDAO.isAuction(auction)) {
					// Create a Map to store data
					final Map<String, Object> data = new HashMap<String, Object>();
					data.put("auction_id", auction.getId());
					data.put("creator_id", auction.getCreator_id());
					data.put("name", auction.getName());
					data.put("item_id", auction.getItem_id());
					data.put("quantity_id", auction.getQuantity_kg());
					data.put("start_bid", auction.getStart_bid());
					data.put("start_time", auction.getStart_time());
					data.put("end_time", auction.getEnd_time());
					data.put("offerer_id", offererId);

					final JSONObject json_string = new JSONObject(data);

					// Encoding the cookie data into base64 to avoid using unsupported characters
					final String auction_data_cookie = Base64.getEncoder()
							.encodeToString((json_string.toString()).getBytes());

					// Cookies accept strings as value so change json to string
					Cookie ck = new Cookie("auction_data_cookie", auction_data_cookie);
					response.addCookie(ck);

					RequestDispatcher rd = request.getRequestDispatcher("/buyer_auction_page.html");
					rd.forward(request, response);
				} else {
					System.out.println("The auction you are looking for is not active");
					RequestDispatcher rd = request.getRequestDispatcher("/index_logged_in.html");
					rd.forward(request, response);
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
