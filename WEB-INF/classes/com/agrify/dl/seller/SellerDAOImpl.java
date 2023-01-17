package com.agrify.dl.seller;

import java.sql.*;
import com.agrify.dl.DAOConnection;

/**
 * SellerDAOImpl
 */
public class SellerDAOImpl implements SellerDAO {

	// Create or insert a seller
	public void insertSeller(SellerDTO seller) throws Exception {
		try {
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("SELECT id FROM retailers WHERE id = ?");
			preparedStatement.setInt(1, seller.getId());
			ResultSet resultSet;
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				resultSet.close();
				preparedStatement.close();
				connection.close();
				throw new Exception("Customer with id : " + seller.getId() + " exists");
			}

			resultSet.close();
			preparedStatement.close();
			
			preparedStatement = connection.prepareStatement("INSERT INTO retailers (id, name, birth) VALUES (?,?,?)",Statement.RETURN_GENERATED_KEYS);			
			preparedStatement.setInt(1, seller.getId());
			preparedStatement.setString(2, seller.getName());
			preparedStatement.setString(3, seller.getBirth());
			preparedStatement.executeUpdate();

			resultSet.next();
			int id = resultSet.getInt(1);

			seller.setId(id);
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return;
	};

	// Update seller
	public void updateSeller(SellerDTO seller) throws Exception {
		try {
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("SELECT id FROM retailers WHERE id = ?");
			preparedStatement.setInt(1, seller.getId());
			ResultSet resultSet;
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				resultSet.close();
				preparedStatement.close();
				connection.close();
				throw new Exception("Customer with id : " + seller.getId() + " does not exist");
			}

			resultSet.close();
			preparedStatement.close();
			
			preparedStatement = connection.prepareStatement("UPDATE retailers SET name = ?, birth = ? WHERE id = ?",Statement.RETURN_GENERATED_KEYS);			
			preparedStatement.setString(1, seller.getName());
			preparedStatement.setString(2, seller.getName());
			preparedStatement.setInt(3, seller.getId());
			preparedStatement.executeUpdate();

			resultSet.next();

			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return;
	};

	// Select seller
	public SellerDTO selectSeller(SellerDTO seller) throws Exception {
		try {
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("SELECT id FROM reatailers WHERE id = ?");
			preparedStatement.setInt(1, seller.getId());
			ResultSet resultSet;
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				resultSet.close();
				preparedStatement.close();
				connection.close();
				throw new Exception("Customer with id : " + seller.getId() + "does not exist");
			}

			resultSet.close();
			preparedStatement.close();
			
			preparedStatement = connection.prepareStatement("SELECT * FROM reatailers WHERE id = ?",Statement.RETURN_GENERATED_KEYS);			
			preparedStatement.setInt(1, seller.getId());
			preparedStatement.executeUpdate();

			resultSet.next();
			seller.SetName(resultSet.getString("name"));
			seller.setBirth(resultSet.getString("birth"));

			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return seller;
	};

	// Delete seller
	public boolean deleteSeller(SellerDTO seller) throws Exception {
		try {
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("SELECT id FROM reatailers WHERE id = ?");
			preparedStatement.setInt(1, seller.getId());
			ResultSet resultSet;
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				resultSet.close();
				preparedStatement.close();
				connection.close();
				throw new Exception("Customer with id : " + seller.getId() + "does not exist");
			}

			resultSet.close();
			preparedStatement.close();
			
			preparedStatement = connection.prepareStatement("DELETE FROM reatailers WHERE id = ?",Statement.RETURN_GENERATED_KEYS);			
			preparedStatement.setInt(1, seller.getId());
			preparedStatement.executeUpdate();

			resultSet.next();
			
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return true;
	};
}