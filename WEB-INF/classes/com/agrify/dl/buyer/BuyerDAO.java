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
	public boolean isBuyer(String email) throws Exception;

	// Validation
	public boolean Validation(String email, String password) throws Exception;
}