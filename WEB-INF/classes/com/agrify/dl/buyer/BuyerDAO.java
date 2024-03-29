package com.agrify.dl.buyer;

/**
 * BuyerDAO
 */
public interface BuyerDAO {

	// Create or insert a buyer
	void insertBuyer(BuyerDTO buyer) throws Exception;

	// Update buyer
	void updateBuyer(BuyerDTO buyer) throws Exception;

	// Select buyer
	BuyerDTO selectBuyer(BuyerDTO buyer) throws Exception;

	// Delete buyer
	boolean deleteBuyer(BuyerDTO buyer) throws Exception;

	// Is buyer
	boolean isBuyer(BuyerDTO buyer) throws Exception;

	// Validation
	boolean Validation(BuyerDTO buyer) throws Exception;
}