package com.agrify.seller.dao;

// SQL imports
import java.sql.SQLException;

import com.agrify.seller.model.Seller;

/**
 * SellerDAO
 */
public interface SellerDAO {

	// Create or insert a seller
	void insertSeller(Seller seller) throws SQLException;

	// Update seller
	void updateSeller(Seller seller) throws SQLException;

	// Select seller
	void selectSeller(Seller seller);

	// Delete seller
	boolean deleteSeller(Seller seller) throws SQLException;
}