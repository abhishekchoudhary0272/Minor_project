package com.agrify.dl.auction;

import java.util.ArrayList;

/**
 * AuctionDAO
 */
public interface AuctionDAO {

	// Create or insert a seller
	void insertAuction(AuctionDTO auction) throws Exception;

	// Update auction
	void updateAuction(AuctionDTO auction) throws Exception;

	// Select auction
	AuctionDTO selectAuction(AuctionDTO auction) throws Exception;

	// Delete auction
	boolean deleteAuction(AuctionDTO auction) throws Exception;

	// Is auction
	boolean isAuction(AuctionDTO auction) throws Exception;

	// Get all auctions present in the database
	ArrayList<AuctionDTO> getALL() throws Exception;
}
