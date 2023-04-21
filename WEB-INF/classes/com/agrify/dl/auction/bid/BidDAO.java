package com.agrify.dl.auction.bid;

/**
 * BidDAO
 */
public interface BidDAO {

	// Create or insert a seller
	void insertBid(BidDTO bid) throws Exception;

	// Update bid
	void updateBid(BidDTO bid) throws Exception;

	// Select bid
	BidDTO selectBid(BidDTO bid) throws Exception;

	// Delete bid
	boolean deleteBid(BidDTO bid) throws Exception;

	// Is bid
	boolean isBid(BidDTO bid) throws Exception;

	// Validation
	boolean Validation(BidDTO bid) throws Exception;
}