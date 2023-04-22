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

import com.agrify.dl.buyer.BuyerDAOImpl;
import com.agrify.dl.buyer.BuyerDTO;
import com.agrify.dl.seller.SellerDAOImpl;
import com.agrify.dl.seller.SellerDTO;

/**
 * Data
 */

@Path("/user")
public class User {

	public static Map<String, Object> data = new HashMap<String, Object>();

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response giveData(@PathParam("id") String id) {
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

}