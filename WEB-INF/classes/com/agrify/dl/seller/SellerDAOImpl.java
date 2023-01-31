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
			preparedStatement = connection.prepareStatement("SELECT email FROM retailers WHERE email = ?");
			preparedStatement.setString(1, seller.getEmail());
			ResultSet resultSet;
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				resultSet.close();
				preparedStatement.close();
				connection.close();
				throw new Exception("Customer with email : " + seller.getEmail() + " exists");
			}

			// resultSet.close();
			preparedStatement.close();
			
			preparedStatement = connection.prepareStatement("INSERT INTO retailers (first_name, last_name, password, email, birth, phone_number, aadhaar_id) VALUES (?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);	
			preparedStatement.setString(1, seller.getFirst_name());
			preparedStatement.setString(2, seller.getLast_name());
			preparedStatement.setString(3, seller.getPassword());
			preparedStatement.setString(4, seller.getEmail());
			preparedStatement.setString(5, seller.getBirth());
			preparedStatement.setString(6, seller.getPhone_number());
			preparedStatement.setString(7, seller.getAadhaar_id());
			preparedStatement.executeUpdate();

			resultSet.next();
			String email = resultSet.getString(1);

			seller.setEmail(email);
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
			preparedStatement = connection.prepareStatement("SELECT email FROM retailers WHERE email = ?");
			preparedStatement.setString(1, seller.getEmail());
			ResultSet resultSet;
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				resultSet.close();
				preparedStatement.close();
				connection.close();
				throw new Exception("Customer with email : " + seller.getEmail() + " does not exist");
			}

			// resultSet.close();
			preparedStatement.close();
			
			preparedStatement = connection.prepareStatement("UPDATE retailers SET first_name = ?, last_name = ?, password = ?, email = ?, birth = ?, phone_number = ?, aadhaar_id = ? WHERE email = ?",Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, seller.getFirst_name());
			preparedStatement.setString(2, seller.getLast_name());
			preparedStatement.setString(3, seller.getPassword());
			preparedStatement.setString(4, seller.getEmail());
			preparedStatement.setString(5, seller.getBirth());
			preparedStatement.setString(6, seller.getPhone_number());
			preparedStatement.setString(7, seller.getAadhaar_id());
			preparedStatement.setString(8, seller.getEmail());
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
			preparedStatement = connection.prepareStatement("SELECT email FROM reatailers WHERE email = ?");
			preparedStatement.setString(1, seller.getEmail());
			ResultSet resultSet;
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				resultSet.close();
				preparedStatement.close();
				connection.close();
				throw new Exception("Customer with email : " + seller.getEmail() + "does not exist");
			}

			// resultSet.close();
			preparedStatement.close();
			
			preparedStatement = connection.prepareStatement("SELECT * FROM reatailers WHERE email = ?",Statement.RETURN_GENERATED_KEYS);			
			preparedStatement.setString(1, seller.getEmail());
			preparedStatement.executeUpdate();

			resultSet.next();
			seller.setFirst_name(resultSet.getString("first_name"));
			seller.setLast_name(resultSet.getString("last_name"));
			seller.setBirth(resultSet.getString("birth"));
			seller.setEmail(resultSet.getString("email"));
			seller.setPassword(resultSet.getString("password"));
			seller.setPhone_number(resultSet.getString("phone_number"));
			seller.setAadhaar_id(resultSet.getString("aadhaar_id"));

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
			preparedStatement = connection.prepareStatement("SELECT email FROM reatailers WHERE email = ?");
			preparedStatement.setString(1, seller.getEmail());
			ResultSet resultSet;
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				resultSet.close();
				preparedStatement.close();
				connection.close();
				throw new Exception("Customer with email : " + seller.getEmail() + "does not exist");
			}

			// resultSet.close();
			preparedStatement.close();
			
			preparedStatement = connection.prepareStatement("DELETE FROM reatailers WHERE email = ?",Statement.RETURN_GENERATED_KEYS);			
			preparedStatement.setString(1, seller.getEmail());
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