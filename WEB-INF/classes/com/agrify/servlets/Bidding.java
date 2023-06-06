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
import com.agrify.dl.user.*;
import com.agrify.util.*;

/**
 * Bidding
 */
public class Bidding extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println("doGet()");
			javax.servlet.http.Cookie[] ck = request.getCookies();

			// If there is a cookie determine the user and show him his profile page
			if (ck != null) {
				String data = ck[0].getValue();
				if (!data.equals("") || data != null) {

					String data_string = new String(Base64.getDecoder().decode(data));
					// System.out.println(data_string);

					JSONParser parser = new JSONParser();
					JSONObject user_data_cookie = (JSONObject) parser.parse(data_string);

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
						response.addCookie(ck[0]);
						rd.forward(request, response);
					} else {
						RequestDispatcher rd = request.getRequestDispatcher("/index.html");
						response.addCookie(ck[0]);
						rd.forward(request, response);
					}
				}
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
				if (data.equals("") || data != null) {
					String dataString = new String(Base64.getDecoder().decode(data));
					JSONParser parser = new JSONParser();
					JSONObject user_data_cookie = (JSONObject) parser.parse(dataString);

					System.out.println(user_data_cookie);
					String cid = user_data_cookie.get("id").toString();
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
					user.setId(cid);
					UserDAOImpl userDAO = new UserDAOImpl();
					userDAO.selectUser(user);
					String user_role = user.getUser_role().toString();
					if (user_role.equals("Buyer")) {
						String id = request.getParameter("id");
						System.out.println("Auction id = " + id);

						AuctionDTO auction = new AuctionDTO();
						AuctionDAOImpl auctionDAO = new AuctionDAOImpl();
						auction.setId(id);

						boolean logged_in = false;
						String offererId = "";
						javax.servlet.http.Cookie[] cookies = request.getCookies();
						if (cookies != null) {
							String da = cookies[0].getValue();
							if (!da.equals("") || da != null) {
								String data_string = new String(Base64.getDecoder().decode(da));
								JSONObject user_cookie = (JSONObject) parser.parse(data_string);
								offererId = user_cookie.get("id").toString();
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
								final Map<String, Object> dataa = new HashMap<String, Object>();
								dataa.put("auction_id", auction.getId());
								dataa.put("creator_id", auction.getCreator_id());
								dataa.put("name", auction.getName());
								dataa.put("item_id", auction.getItem_id());
								dataa.put("quantity_id", auction.getQuantity_kg());
								dataa.put("start_bid", auction.getStart_bid());
								dataa.put("start_time", auction.getStart_time());
								dataa.put("end_time", auction.getEnd_time());
								dataa.put("offerer_id", offererId);

								final JSONObject json_string = new JSONObject(dataa);

								// Encoding the cookie data into base64 to avoid using unsupported characters
								final String auction_data_cookie = Base64.getEncoder()
										.encodeToString((json_string.toString()).getBytes());

								// Cookies accept strings as value so change json to string
								Cookie ckk = new Cookie("auction_data_cookie", auction_data_cookie);
								response.addCookie(ckk);

								RequestDispatcher rd = request.getRequestDispatcher("/buyer_auction_page.html");
								rd.forward(request, response);
							} else {
								System.out.println("The auction you are looking for is not active");
								RequestDispatcher rd = request.getRequestDispatcher("/index_logged_in.html");
								rd.forward(request, response);
							}
						}
					} else if (user_role.equals("Seller")) {
						System.out.println("You are seller you can't access it.");
						RequestDispatcher rd = request.getRequestDispatcher("/index.html");
						rd.forward(request, response);
					} else {
						System.out.println("Hey plz login");
						RequestDispatcher rd = request.getRequestDispatcher("/index.html");
						rd.forward(request, response);
					}
				}
			} else {
				System.out.println("Empty");
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
