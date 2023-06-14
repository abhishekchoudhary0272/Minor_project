package com.agrify.dl.auction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
			ResultSet resultSet;
			assert isAuction(auction);

			preparedStatement = connection.prepareStatement(
					"INSERT INTO auctions (creator_id, name, item_id, quantity_kg, start_bid, start_time, end_time) VALUES (?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, auction.getCreator_id());
			preparedStatement.setString(2, auction.getName());
			preparedStatement.setString(3, auction.getItem_id());
			preparedStatement.setString(4, auction.getQuantity_kg());
			preparedStatement.setString(5, auction.getStart_bid());
			preparedStatement.setString(6, auction.getStart_time());
			preparedStatement.setString(7, auction.getEnd_time());
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();

			resultSet.next();
			auction.setId(resultSet.getString(1));

			resultSet.close();
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
					"UPDATE auctions SET creator_id = ?, name = ?, item_id = ?, quantity_kg = ?, start_bid = ?, start_time = ?, end_time WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, auction.getCreator_id());
			preparedStatement.setString(2, auction.getName());
			preparedStatement.setString(3, auction.getItem_id());
			preparedStatement.setString(4, auction.getQuantity_kg());
			preparedStatement.setString(5, auction.getStart_bid());
			preparedStatement.setString(6, auction.getStart_time());
			preparedStatement.setString(7, auction.getEnd_time());
			preparedStatement.setString(8, auction.getId());
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
			auction.setCreator_id(resultSet.getString("creator_id"));
			auction.setName(resultSet.getString("name"));
			auction.setQuantity_kg(resultSet.getString("quantity_kg"));
			auction.setItem_id(resultSet.getString("item_id"));
			auction.setStart_bid(resultSet.getString("start_bid"));
			auction.setStart_time(resultSet.getString("start_time"));
			auction.setEnd_time(resultSet.getString("end_time"));

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

	// Get all auctions present in the database
	public ArrayList<AuctionDTO> getALL() throws Exception {
		try {
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			ResultSet resultSet;

			ArrayList<AuctionDTO> auction = new ArrayList<AuctionDTO>();

			preparedStatement = connection.prepareStatement("SELECT * FROM auctions", Statement.RETURN_GENERATED_KEYS);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				AuctionDTO tempAuction = new AuctionDTO();

				tempAuction.setId(resultSet.getString("id"));
				tempAuction.setCreator_id(resultSet.getString("creator_id"));
				tempAuction.setName(resultSet.getString("name"));
				tempAuction.setQuantity_kg(resultSet.getString("quantity_kg"));
				tempAuction.setItem_id(resultSet.getString("item_id"));
				tempAuction.setStart_bid(resultSet.getString("start_bid"));
				tempAuction.setStart_time(resultSet.getString("start_time"));
				tempAuction.setEnd_time(resultSet.getString("end_time"));

				auction.add(tempAuction);
			}

			resultSet.close();
			preparedStatement.close();
			connection.close();
			return auction;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}