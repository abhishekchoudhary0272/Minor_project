package com.agrify.seller.dao;

// SQL imports
import java.sql.SQLException;

/**
 * sellerDAO
 */
public interface sellerDAO {

	// Create or insert a seller
	void insertSeller(int seller) throws SQLException;

	// Update seller
	void updateSeller(int seller) throws SQLException;

	// Select seller
	void selectSeller(int seller);

	// Delete seller
	boolean deleteSeller(int seller) throws SQLException;
}