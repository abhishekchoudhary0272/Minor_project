package com.agrify.dl.buyer;

import com.agrify.dl.DAOConnection;
import java.sql.*;

/**
 * BuyerDAOImpl
 */
public class BuyerDAOImpl implements BuyerDAO {

	// Create or insert a buyer
	public void insertBuyer(BuyerDTO buyer) throws Exception {
		try {
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("SELECT id FROM customers WHERE id = ?");
			preparedStatement.setInt(1, buyer.getId());
			ResultSet resultSet;
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				resultSet.close();
				preparedStatement.close();
				connection.close();
				throw new Exception("Customer with id : " + buyer.getId() + " already exists");
			}

			resultSet.close();
			preparedStatement.close();
			
			preparedStatement = connection.prepareStatement("INSERT INTO customers (id, name, password, email, birth, phone_number, aadhaar_id) VALUES (?,?,?,?,?,,?)",Statement.RETURN_GENERATED_KEYS);	
			preparedStatement.setInt(1, buyer.getId());
			preparedStatement.setString(2, buyer.getName());
			preparedStatement.setString(3, buyer.getPassword());
			preparedStatement.setString(4, buyer.getEmail());
			preparedStatement.setString(5, buyer.getBirth());
			preparedStatement.setString(6, buyer.getPhone_number());
			preparedStatement.setString(7, buyer.getAadhaar_id());
			preparedStatement.executeUpdate();

			resultSet.next();
			int id = resultSet.getInt(1);

			buyer.setId(id);
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return;
	};

	// Update buyer
	public void updateBuyer(BuyerDTO buyer) throws Exception {
		try {
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("SELECT id FROM customers WHERE id = ?");
			preparedStatement.setInt(1, buyer.getId());
			ResultSet resultSet;
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				resultSet.close();
				preparedStatement.close();
				connection.close();
				throw new Exception("Customer with id : " + buyer.getId() + " does not exist");
			}

			resultSet.close();
			preparedStatement.close();
			
			preparedStatement = connection.prepareStatement("UPDATE customers SET name = ?, password = ?, email = ?, birth = ?, phone_number = ?, aadhaar_id = ? WHERE id = ?",Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, buyer.getName());
			preparedStatement.setString(3, buyer.getPassword());
			preparedStatement.setString(4, buyer.getEmail());
			preparedStatement.setString(5, buyer.getBirth());
			preparedStatement.setString(6, buyer.getPhone_number());
			preparedStatement.setString(7, buyer.getAadhaar_id());
			preparedStatement.setInt(3, buyer.getId());
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

	// Select buyer
	public BuyerDTO selectBuyer(BuyerDTO buyer) throws Exception {
		try {
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("SELECT id FROM customers WHERE id = ?");
			preparedStatement.setInt(1, buyer.getId());
			ResultSet resultSet;
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				resultSet.close();
				preparedStatement.close();
				connection.close();
				throw new Exception("Customer with id : " + buyer.getId() + "does not exist");
			}

			resultSet.close();
			preparedStatement.close();
			
			preparedStatement = connection.prepareStatement("SELECT * FROM customers WHERE id = ?",Statement.RETURN_GENERATED_KEYS);			
			preparedStatement.setInt(1, buyer.getId());
			preparedStatement.executeUpdate();

			resultSet.next();
			buyer.setName(resultSet.getString("name"));
			buyer.setBirth(resultSet.getString("birth"));

			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return buyer;
};

	// Delete buyer
	public boolean deleteBuyer(BuyerDTO buyer) throws Exception {
		try {
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("SELECT id FROM customers WHERE id = ?");
			preparedStatement.setInt(1, buyer.getId());
			ResultSet resultSet;
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				resultSet.close();
				preparedStatement.close();
				connection.close();
				throw new Exception("Customer with id : " + buyer.getId() + "does not exist");
			}

			resultSet.close();
			preparedStatement.close();
			
			preparedStatement = connection.prepareStatement("DELETE FROM customers WHERE id = ?",Statement.RETURN_GENERATED_KEYS);			
			preparedStatement.setInt(1, buyer.getId());
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