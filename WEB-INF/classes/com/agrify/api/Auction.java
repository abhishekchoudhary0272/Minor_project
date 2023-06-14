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

import com.agrify.dl.auction.AuctionDAOImpl;
import com.agrify.dl.auction.AuctionDTO;

/**
 * Auction
 */

@Path("/auction")
public class Auction {

	public static Map<String, Object> data = new HashMap<String, Object>();

	@GET
	@Path("/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response giveData(@PathParam("id") String id) {
		AuctionDTO auction = new AuctionDTO();
		AuctionDAOImpl auctionDAO = new AuctionDAOImpl();

		auction.setId(id);

		data.clear();

		try {
			if (auctionDAO.isAuction(auction)) {
				auction = auctionDAO.selectAuction(auction);
				data.clear();
				data.put("id", auction.getId());
				data.put("creator_id", auction.getCreator_id());
				data.put("name", auction.getName());
				data.put("item_id", auction.getItem_id());
				data.put("quantity_id", auction.getQuantity_kg());
				data.put("start_bid", auction.getStart_bid());
				data.put("start_time", auction.getStart_time());
				data.put("end_time", auction.getEnd_time());
			} else {
				data.clear();
				data.put("empty", "true");
			}
		} catch (Exception e) {
			data.clear();
			data.put("empty", "true");
			System.out.println(e.getMessage());
		}

		final JSONObject json_string = new JSONObject(data);

		return Response.status(200).entity(json_string).build();
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllAuctions() {
		try {
			final JSONArray obj = new JSONArray();

			ArrayList<AuctionDTO> auctions = new ArrayList<>();
			AuctionDAOImpl auctionDAO = new AuctionDAOImpl();
			auctions = auctionDAO.getALL();

			for (AuctionDTO auctionDTO : auctions) {
				final Map<String, Object> jsonMap = new HashMap<String, Object>();

				jsonMap.put("id", auctionDTO.getId());
				jsonMap.put("creator_id", auctionDTO.getCreator_id());
				jsonMap.put("name", auctionDTO.getName());
				jsonMap.put("item_id", auctionDTO.getItem_id());
				jsonMap.put("quantity_id", auctionDTO.getQuantity_kg());
				jsonMap.put("start_bid", auctionDTO.getStart_bid());
				jsonMap.put("start_time", auctionDTO.getStart_time());
				jsonMap.put("end_time", auctionDTO.getEnd_time());

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

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createAuction(InputStream incomingData) {
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

			String id = jsonData.get("id").toString();
			String creator_id = jsonData.get("creator_id").toString();
			String name = jsonData.get("name").toString();
			String item_id = jsonData.get("item_id").toString();
			String quantity_id = jsonData.get("quantity_id").toString();
			String start_bid = jsonData.get("start_bid").toString();
			String start_time = jsonData.get("start_time").toString();
			String end_time = jsonData.get("end_time").toString();

			AuctionDTO auction = new AuctionDTO();
			auction.setId(id);
			auction.setCreator_id(creator_id);
			auction.setName(name);
			auction.setItem_id(item_id);
			auction.setQuantity_kg(quantity_id);
			auction.setStart_bid(start_bid);
			auction.setStart_time(start_time);
			auction.setEnd_time(end_time);

			AuctionDAOImpl auctionDAO = new AuctionDAOImpl();

			auctionDAO.insertAuction(auction);

			data.put("id", auction.getId());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		final JSONObject json_string = new JSONObject(data);

		return Response.status(200).entity(json_string).build();
	}

	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateAuction(InputStream incomingData) {
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
			String creator_id = jsonData.get("creator_id").toString();
			String name = jsonData.get("name").toString();
			String item_id = jsonData.get("item_id").toString();
			String quantity_id = jsonData.get("quantity_id").toString();
			String start_bid = jsonData.get("start_bid").toString();
			String start_time = jsonData.get("start_time").toString();
			String end_time = jsonData.get("end_time").toString();

			AuctionDTO auction = new AuctionDTO();
			auction.setId(id);
			auction.setCreator_id(creator_id);
			auction.setName(name);
			auction.setItem_id(item_id);
			auction.setQuantity_kg(quantity_id);
			auction.setStart_bid(start_bid);
			auction.setStart_time(start_time);
			auction.setEnd_time(end_time);
			AuctionDAOImpl auctionDAO = new AuctionDAOImpl();

			auctionDAO.insertAuction(auction);

			data.put("id", auction.getId());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return Response.status(200).build();
	}

	// Delete auction
	@GET
	@Path("/delete/{id}")
	public Response deleteAuction(@PathParam("id") String id) {
		try {

			AuctionDTO auction = new AuctionDTO();
			auction.setId(id);
			AuctionDAOImpl auctionDAO = new AuctionDAOImpl();

			auctionDAO.deleteAuction(auction);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return Response.status(200).build();
	}
}