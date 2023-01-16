package com.agrify.dl.seller;

/**
 * SellerDAO
 */
public interface SellerDAO {

	// Create or insert a seller
	void insertSeller(SellerDTO seller) throws Exception;

	// Update seller
	void updateSeller(SellerDTO seller) throws Exception;

	// Select seller
	void selectSeller(SellerDTO seller);

	// Delete seller
	boolean deleteSeller(SellerDTO seller) throws Exception;
}