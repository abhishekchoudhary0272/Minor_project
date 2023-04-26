package com.agrify.dl.auction.bid;

/**
 * BidDTO
 */
public class BidDTO {

	private String id;
	private String auction_id;
	private String offerer_id;
	private String offer;
	private String bid_timestamp;

	public BidDTO() {
		this.id = "";
		this.auction_id = "";
		this.offerer_id = "";
		this.offer = "";
		this.bid_timestamp = "";
	}
	
	public BidDTO(String id, String auction_id, String offerer_id, String offer, String bid_timestamp) {
		this.id = id;
		this.auction_id = auction_id;
		this.offerer_id = offerer_id;
		this.offer = offer;
		this.bid_timestamp = bid_timestamp;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuction_id() {
		return auction_id;
	}

	public void setAuction_id(String auction_id) {
		this.auction_id = auction_id;
	}

	public String getOfferer_id() {
		return offerer_id;
	}

	public void setOfferer_id(String offerer_id) {
		this.offerer_id = offerer_id;
	}

	public String getOffer() {
		return offer;
	}

	public void setOffer(String offer) {
		this.offer = offer;
	}

	public String getBid_timestamp() {
		return bid_timestamp;
	}

	public void setBid_timestamp(String bid_timestamp) {
		this.bid_timestamp = bid_timestamp;
	}
}
