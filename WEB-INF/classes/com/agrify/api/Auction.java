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

import com.agrify.dl.auction.AuctionDAOImpl;
import com.agrify.dl.auction.AuctionDTO;

/**
 * Auction
 */

@Path("/auction")
public class Auction {

	public static Map<String, Object> data = new HashMap<String, Object>();

	@GET
	@Path("/{id}")
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

}