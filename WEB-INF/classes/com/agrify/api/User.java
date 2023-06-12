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
import com.agrify.dl.user.UserDAOImpl;
import com.agrify.dl.user.UserDTO;
import com.agrify.dl.user.UserRole.role;
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
		UserDTO user = new UserDTO();

		UserDAOImpl userDAO = new UserDAOImpl();

		user.setId(id);

		data.clear();

		try {
			user = userDAO.selectUser(user);
			data.put("id", user.getId());
			data.put("role", user.getUser_role());
			data.put("first_name", user.getFirst_name());
			data.put("last_name", user.getLast_name());
			data.put("birth", user.getBirth());
			data.put("email", user.getEmail());
			data.put("phone_number", user.getPhone_number());
			data.put("password", user.getPassword());
			data.put("aadhaar_id", user.getAadhaar_id());
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
			users = userDAO.getAll();

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

	// Need to send data to POST request or it'll send an invalid response and it'll
	// throw error
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

	// Delete user
	@GET
	@Path("/delete/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteUser(@PathParam("id") String id) {
		try {

			UserDTO user = new UserDTO();
			user.setId(id);
			UserDAOImpl userDAO = new UserDAOImpl();

			userDAO.deleteUser(user);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace(System.out);
			
			String result = "Error deleting user...";
			return Response.status(200).entity(result).build();
		}

		String result = "User deleted successfully...";
		return Response.status(200).entity(result).build();
	}

	// Need to send data to POST request or it'll send an invalid response and it'll
	// throw an error
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(InputStream incomingData) {
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

			String firstName = jsonData.get("first-name").toString();
			String lastName = jsonData.get("last-name").toString();
			String userRole = jsonData.get("role").toString();
			String email = jsonData.get("email").toString();
			String password = jsonData.get("password").toString();
			String phoneNumber = jsonData.get("phone-number").toString();
			String birthDate = jsonData.get("birth-date").toString();
			String aadhaarID = jsonData.get("aadhaar-id").toString();

			UserDTO user = new UserDTO();
			user.setEmail(email);
			user.setUser_role(role.valueOf(userRole));
			user.setAadhaar_id(aadhaarID);
			user.setFirst_name(firstName);
			user.setLast_name(lastName);
			user.setPhone_number(phoneNumber);
			user.setBirth(birthDate);
			user.setPassword(password);
			UserDAOImpl userDAO = new UserDAOImpl();

			userDAO.insertUser(user);

			data.put("id", user.getId());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		final JSONObject json_string = new JSONObject(data);

		return Response.status(200).entity(json_string).build();
	}

	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response updateUser(InputStream incomingData) {
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

			String id = jsonData.get("id").toString();
			String firstName = jsonData.get("first-name").toString();
			String lastName = jsonData.get("last-name").toString();
			String userRole = jsonData.get("role").toString();
			String email = jsonData.get("email").toString();
			String password = jsonData.get("password").toString();
			String phoneNumber = jsonData.get("phone-number").toString();
			String birthDate = jsonData.get("birth-date").toString();
			String aadhaarID = jsonData.get("aadhaar-id").toString();

			UserDTO user = new UserDTO();
			user.setId(id);
			user.setEmail(email);
			user.setPassword(password);
			user.setUser_role(role.valueOf(userRole));
			user.setFirst_name(firstName);
			user.setLast_name(lastName);
			user.setAadhaar_id(aadhaarID);
			user.setPhone_number(phoneNumber);
			user.setBirth(birthDate);
			UserDAOImpl userDAO = new UserDAOImpl();

			userDAO.updateUser(user);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		String result = "User updated successfully...";
		return Response.status(200).entity(result).build();
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