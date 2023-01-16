package com.agrify.dl.seller;

// SQL imports
import java.sql.SQLException;

/**
 * SellerDAO
 */
public interface SellerDAO {

	// Create or insert a seller
	void insertSeller(SellerDTO seller) throws SQLException;

	// Update seller
	void updateSeller(SellerDTO seller) throws SQLException;

	// Select seller
	void selectSeller(SellerDTO seller);

	// Delete seller
	boolean deleteSeller(SellerDTO seller) throws SQLException;
}
