package com.agrify.dl.buyer;

// SQL imports
import java.sql.SQLException;

/**
 * BuyerDAO
 */
public interface BuyerDAO {

	// Create or insert a seller
	void insertBuyer(BuyerDTO buyer) throws SQLException;

	// Update seller
	void updateBuyer(BuyerDTO buyer) throws SQLException;

	// Select seller
	void selectBuyer(BuyerDTO buyer);

	// Delete seller
	boolean deleteBuyer(BuyerDTO buyer) throws SQLException;
}