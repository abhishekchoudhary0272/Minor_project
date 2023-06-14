package com.agrify.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.agrify.dl.auction.bid.BidDAOImpl;
import com.agrify.dl.auction.bid.BidDTO;

/**
 * Bid
 */

@Path("/bid")
public class Bid {

	public static Map<String, Object> data = new HashMap<String, Object>();

	@GET
	@Path("/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response giveData(@PathParam("id") String id) {
		BidDTO bid = new BidDTO();
		BidDAOImpl bidDAO = new BidDAOImpl();

		bid.setId(id);

		data.clear();

		try {
			if (bidDAO.isBid(bid)) {
				bid = bidDAO.selectBid(bid);
				data.clear();
				data.put("id", bid.getId());
				data.put("auction_id", bid.getAuction_id());
				data.put("offerer_id", bid.getOfferer_id());
				data.put("offer", bid.getOffer());
				data.put("bid_timestamp", bid.getOffer());
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
	@Path("/all/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response giveAllBids(@PathParam("id") String id) {
		try {
			final JSONArray obj = new JSONArray();

			ArrayList<BidDTO> bids = new ArrayList<>();
			BidDAOImpl bidDAO = new BidDAOImpl();
			BidDTO bid = new BidDTO();
			bid.setAuction_id(id);
			bids = bidDAO.getAllBids(bid);

			for (BidDTO bidDTO : bids) {
				final Map<String, Object> jsonMap = new HashMap<String, Object>();

				jsonMap.put("id", bidDTO.getId());
				jsonMap.put("auction_id", bidDTO.getAuction_id());
				jsonMap.put("bid_timestamp", bidDTO.getBid_timestamp());
				jsonMap.put("offer", bidDTO.getOffer());
				jsonMap.put("offerer_id", bidDTO.getOfferer_id());

				obj.add(jsonMap);
			}

			final JSONArray json_string = obj;

			System.out.println(json_string);
			return Response.status(200).entity(json_string).build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		final JSONObject json_string = new JSONObject(data);

		return Response.status(200).entity(json_string).build();
	}
}