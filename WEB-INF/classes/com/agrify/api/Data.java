package com.agrify.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.HashMap;  
import java.util.Map;
import org.json.simple.JSONObject;

/**
 * Data
 */

@Path("/data")
public class Data {

	public static Map<String, Object> data = new HashMap<String,Object>();

	
	@GET
	@Path("/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response giveData(@PathParam("email") String email) {
		data.put("email", email);
		final JSONObject json_string = new JSONObject(data);
		return Response.status(200).entity(json_string).build();
	}

}