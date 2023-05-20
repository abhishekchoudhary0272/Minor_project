package com.agrify.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.agrify.dl.buyer.BuyerDAOImpl;
import com.agrify.dl.buyer.BuyerDTO;
import com.agrify.dl.seller.SellerDAOImpl;
import com.agrify.dl.seller.SellerDTO;
import com.agrify.dl.user.UserDAOImpl;
import com.agrify.dl.user.UserDTO;
import com.agrify.util.Validation;

/**
 * User
 */

@Path("/user")
public class User {

	public static Map<String, Object> data = new HashMap<String, Object>();

	// Get the user data with id
	@GET
	@Path("/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("id") String id) {
		SellerDTO seller = new SellerDTO();
		BuyerDTO buyer = new BuyerDTO();

		SellerDAOImpl sellerDAO = new SellerDAOImpl();
		BuyerDAOImpl buyerDAO = new BuyerDAOImpl();

		seller.setId(id);
		buyer.setId(id);

		data.clear();

		try {
			if (buyerDAO.isBuyer(buyer)) {
				buyer = buyerDAO.selectBuyer(buyer);
				data.put("id", buyer.getId());
				data.put("first_name", buyer.getFirst_name());
				data.put("last_name", buyer.getLast_name());
				data.put("birth", buyer.getBirth());
				data.put("email", buyer.getEmail());
				data.put("phone_number", buyer.getPhone_number());
				data.put("aadhaar_id", buyer.getAadhaar_id());
			} else if (sellerDAO.isSeller(seller)) {
				seller = sellerDAO.selectSeller(seller);
				data.put("id", seller.getId());
				data.put("first_name", seller.getFirst_name());
				data.put("last_name", seller.getLast_name());
				data.put("birth", seller.getBirth());
				data.put("email", seller.getEmail());
				data.put("phone_number", seller.getPhone_number());
				data.put("aadhaar_id", seller.getAadhaar_id());
			} else {
				data.put("id", id);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		final JSONObject json_string = new JSONObject(data);

		return Response.status(200).entity(json_string).build();
	}

	// Get data of all the users in the database
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers() {
		try {
			final JSONArray obj = new JSONArray();

			ArrayList<UserDTO> users = new ArrayList<>();
			UserDAOImpl userDAO = new UserDAOImpl();
			users = userDAO.getALL();

			for (UserDTO userDTO : users) {
				final Map<String, Object> jsonMap = new HashMap<String, Object>();
				
				jsonMap.put("id", userDTO.getId());
				jsonMap.put("role", userDTO.getUser_role());
				jsonMap.put("first_name", userDTO.getFirst_name());
				jsonMap.put("last_name", userDTO.getLast_name());
				jsonMap.put("email", userDTO.getEmail());
				jsonMap.put("password", userDTO.getPassword());
				jsonMap.put("birth", userDTO.getBirth());
				jsonMap.put("phone_number", userDTO.getPhone_number());
				jsonMap.put("aadhaar_id", userDTO.getAadhaar_id());

				obj.add(jsonMap);
			}

			final JSONArray json_string = obj;

			return Response.status(200).entity(json_string).build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		final JSONObject json_string = new JSONObject(data);

		return Response.status(200).entity(json_string).build();
	}

	// Need to send data to POST request or it'll send an invalid response and it'll throw error
	@POST
	@Path("/check")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRole(InputStream incomingData) {
		data.clear();
		StringBuilder jsonStringBuilder = new StringBuilder();

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			while ((line = in.readLine()) != null) {
				jsonStringBuilder.append(line);
			}

			System.out.println(jsonStringBuilder.toString());
			JSONParser parser = new JSONParser();
			JSONObject jsonData = (JSONObject) parser.parse(jsonStringBuilder.toString());

			String email = jsonData.get("email").toString();
			String password = jsonData.get("password").toString();

			Validation valid = new Validation();
			boolean emailValid = valid.mailCheck(email);
			boolean passwordValid = valid.validString(password, 30, false);

			if (passwordValid == false) {
				data.put("password", "invalid");
				System.out.println("Invalid password");

				if (!emailValid) {
					data.put("email", "invalid");
					System.out.println("Invalid email");
				}

				final JSONObject json_string = new JSONObject(data);
				return Response.status(200).entity(json_string).build();
			}

			UserDTO user = new UserDTO();
			user.setEmail(email);
			user.setPassword(password);
			UserDAOImpl userDAO = new UserDAOImpl();

			System.out.println(user.getEmail());
			System.out.println(user.getPassword());

			if (!userDAO.present(user)) {
				data.put("email", "not-registered");
				final JSONObject json_string = new JSONObject(data);

				return Response.status(200).entity(json_string).build();
			} else if (!userDAO.validation(user)) {
				data.put("password", "not-matching");
				final JSONObject json_string = new JSONObject(data);

				return Response.status(200).entity(json_string).build();
			} else {
				data.clear();
				user = userDAO.selectUser(user);
				data.put("role", user.getUser_role().toString());
				final JSONObject json_string = new JSONObject(data);
				return Response.status(200).entity(json_string).build();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		final JSONObject json_string = new JSONObject(data);

		return Response.status(200).entity(json_string).build();
	}

	// Verify that the service is running
	@GET
	@Path("/verify")
	@Produces(MediaType.TEXT_PLAIN)
	public Response verifyRESTService() {
		String result = "User services are running successfully...";

		// return HTTP response 200 in case of success
		return Response.status(200).entity(result).build();
	}

}