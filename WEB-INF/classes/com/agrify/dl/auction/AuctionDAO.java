package com.agrify.dl.auction;

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

}
