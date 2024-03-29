package com.agrify.dl.auction.bid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.agrify.dl.DAOConnection;

/**
 * BidDAOImpl
 */
public class BidDAOImpl implements BidDAO {

	// Create or insert a bid
	public void insertBid(BidDTO bid) throws Exception {
		try {
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			ResultSet resultSet;
			assert isBid(bid);

			preparedStatement = connection.prepareStatement(
					"INSERT INTO bids (auction_id, offerer_id, offer) VALUES (?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, bid.getAuction_id());
			preparedStatement.setString(2, bid.getOfferer_id());
			preparedStatement.setString(3, bid.getOffer());
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();

			resultSet.next();
			bid.setId(resultSet.getString(1));

			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return;
	};

	// Update bid
	public void updateBid(BidDTO bid) throws Exception {
		try {
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			assert isBid(bid);

			preparedStatement = connection.prepareStatement(
					"UPDATE bids SET auction_id = ?, offerer_id = ?, offer = ? WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, bid.getAuction_id());
			preparedStatement.setString(2, bid.getOfferer_id());
			preparedStatement.setString(3, bid.getOffer());
			preparedStatement.setString(4, bid.getId());
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return;
	};

	// Select bid
	public BidDTO selectBid(BidDTO bid) throws Exception {
		try {
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			ResultSet resultSet;

			assert isBid(bid);

			preparedStatement = connection.prepareStatement("SELECT * FROM bids WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, bid.getAuction_id());
			preparedStatement.setString(2, bid.getOfferer_id());
			preparedStatement.setString(3, bid.getOffer());
			preparedStatement.setString(4, bid.getId());

			resultSet = preparedStatement.executeQuery();

			resultSet.next();
			bid.setAuction_id(resultSet.getString("auction_id"));
			bid.setOfferer_id(resultSet.getString("offerer_id"));
			bid.setOffer(resultSet.getString("offer"));

			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return bid;
	};

	// Delete bid
	public boolean deleteBid(BidDTO bid) throws Exception {
		try {
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;

			assert isBid(bid);

			preparedStatement = connection.prepareStatement("DELETE FROM bids WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, bid.getId());
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return true;
	}

	// Check if bid exists
	public boolean isBid(BidDTO bid) throws Exception {

		try {

			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("SELECT id FROM bids WHERE id = ?");
			preparedStatement.setString(1, bid.getId());
			ResultSet resultSet;
			resultSet = preparedStatement.executeQuery();

			// If the bid already exits that means we have a bid conflict
			// All bid ids must be different, hence we'll return false here
			if (resultSet.next()) {
				resultSet.close();
				preparedStatement.close();
				connection.close();
				return false;
			}

			resultSet.close();
			preparedStatement.close();
			connection.close();

			// We return true if the bid does not already exits in our database
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public boolean Validation(BidDTO bid) throws Exception {
		try {
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;

			ResultSet resultSet;

			assert isBid(bid);

			preparedStatement = connection.prepareStatement("SELECT bids.password FROM bids WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, bid.getId());
			resultSet = preparedStatement.executeQuery();

			resultSet.next();
			String password_check = resultSet.getString("password");

			if (password_check.equals(bid.getId())) {
				resultSet.close();
				preparedStatement.close();
				connection.close();
				return true;
			}

			resultSet.close();
			preparedStatement.close();
			connection.close();

			return false;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

}