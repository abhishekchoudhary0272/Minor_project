package com.agrify.dl.auction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.agrify.dl.DAOConnection;

/**
 * AuctionDAOImpl
 */
public class AuctionDAOImpl implements AuctionDAO {

	// Create or insert a auction
	public void insertAuction(AuctionDTO auction) throws Exception {
		try {
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			assert isAuction(auction);

			preparedStatement = connection.prepareStatement(
					"INSERT INTO auctions (id, auction_id, offerer_id, offer, phone_number, aadhaar_id) VALUES (?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(2, auction.getId());
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return;
	};

	// Update auction
	public void updateAuction(AuctionDTO auction) throws Exception {
		try {
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			assert isAuction(auction);

			preparedStatement = connection.prepareStatement(
					"UPDATE auctions SET auction_id = ?, offerer_id = ?, id = ?, offer = ?, phone_number = ?, aadhaar_id = ? WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, auction.getId());
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return;
	};

	// Select auction
	public AuctionDTO selectAuction(AuctionDTO auction) throws Exception {
		try {
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			ResultSet resultSet;

			assert isAuction(auction);

			preparedStatement = connection.prepareStatement("SELECT * FROM auctions WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, auction.getId());

			resultSet = preparedStatement.executeQuery();

			resultSet.next();
			auction.setId(resultSet.getString("auction_id"));

			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return auction;
	};

	// Delete auction
	public boolean deleteAuction(AuctionDTO auction) throws Exception {
		try {
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;

			assert isAuction(auction);

			preparedStatement = connection.prepareStatement("DELETE FROM auctions WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, auction.getId());
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return true;
	}

	// Check if auction exists
	public boolean isAuction(AuctionDTO auction) throws Exception {

		try {

			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("SELECT id FROM auctions WHERE id = ?");
			preparedStatement.setString(1, auction.getId());
			ResultSet resultSet;
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
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

	public boolean Validation(AuctionDTO auction) throws Exception {
		try {
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;

			ResultSet resultSet;

			assert isAuction(auction);

			preparedStatement = connection.prepareStatement("SELECT auctions.password FROM auctions WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, auction.getId());
			resultSet = preparedStatement.executeQuery();

			resultSet.next();
			String password_check = resultSet.getString("password");

			if (password_check.equals(auction.getId())) {
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