package com.agrify.dl.auction;

/**
 * AuctionDTO
 */
public class AuctionDTO {

	private String id;
	private String creator_id;
	private String name;
	private String item_id;
	private String quantity_kg;
	private String start_bid;
	private String start_time;
	private String end_time;

	public AuctionDTO() {
		this.id = "";
		this.creator_id = "";
		this.name = "";
		this.item_id = "";
		this.quantity_kg = "";
		this.start_bid = "";
		this.start_time = "";
		this.end_time = "";
	}

	public AuctionDTO(String id, String creator_id, String name, String item_id, String quantity_kg, String start_bid,
			String start_time, String end_time) {
		this.id = id;
		this.creator_id = creator_id;
		this.name = name;
		this.item_id = item_id;
		this.quantity_kg = quantity_kg;
		this.start_bid = start_bid;
		this.start_time = start_time;
		this.end_time = end_time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreator_id() {
		return creator_id;
	}

	public void setCreator_id(String creator_id) {
		this.creator_id = creator_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getQuantity_kg() {
		return quantity_kg;
	}

	public void setQuantity_kg(String quantity_kg) {
		this.quantity_kg = quantity_kg;
	}

	public String getStart_bid() {
		return start_bid;
	}

	public void setStart_bid(String start_bid) {
		this.start_bid = start_bid;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

}
