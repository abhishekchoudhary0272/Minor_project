package com.agrify.dl.buyer;

/**
 * BuyerDAO
 */
public interface BuyerDAO {

	// Create or insert a seller
	void insertBuyer(BuyerDTO buyer) throws Exception;

	// Update seller
	void updateBuyer(BuyerDTO buyer) throws Exception;

	// Select seller
	BuyerDTO selectBuyer(BuyerDTO buyer) throws Exception;

	// Delete seller
	boolean deleteBuyer(BuyerDTO buyer) throws Exception;
}