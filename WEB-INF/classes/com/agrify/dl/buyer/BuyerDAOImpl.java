package com.agrify.dl.buyer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.agrify.dl.DAOConnection;

/**
 * BuyerDAOImpl
 */
public class BuyerDAOImpl implements BuyerDAO {

	// Create or insert a buyer
	public void insertBuyer(BuyerDTO buyer) throws Exception {
		try {
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
      
			assert isBuyer(buyer);

			preparedStatement = connection.prepareStatement(
					"INSERT INTO customers (email, first_name, last_name, password, birth, phone_number, aadhaar_id) VALUES (?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, buyer.getEmail());
			preparedStatement.setString(2, buyer.getFirst_name());
			preparedStatement.setString(3, buyer.getLast_name());
			preparedStatement.setString(4, buyer.getPassword());
			preparedStatement.setString(5, buyer.getBirth());
			preparedStatement.setString(6, buyer.getPhone_number());
			preparedStatement.setString(7, buyer.getAadhaar_id());
			preparedStatement.executeUpdate();

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

			assert isBuyer(buyer);

			preparedStatement = connection.prepareStatement(
					"UPDATE customers SET first_name = ?, last_name = ?, password = ?, email = ?, birth = ?, phone_number = ?, aadhaar_id = ? WHERE email = ?",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, buyer.getFirst_name());
			preparedStatement.setString(2, buyer.getLast_name());
			preparedStatement.setString(3, buyer.getPassword());
			preparedStatement.setString(4, buyer.getEmail());
			preparedStatement.setString(5, buyer.getBirth());
			preparedStatement.setString(6, buyer.getPhone_number());
			preparedStatement.setString(7, buyer.getAadhaar_id());
			preparedStatement.setString(8, buyer.getEmail());
			preparedStatement.executeUpdate();

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
			ResultSet resultSet;
			assert isBuyer(buyer);

			preparedStatement = connection.prepareStatement("SELECT * FROM customers WHERE email = ?",
			Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, buyer.getEmail());
			resultSet = preparedStatement.executeQuery();

			resultSet.next();
			buyer.setFirst_name(resultSet.getString("first_name"));
			buyer.setLast_name(resultSet.getString("last_name"));
			buyer.setBirth(resultSet.getString("birth"));
			buyer.setEmail(resultSet.getString("email"));
			buyer.setPassword(resultSet.getString("password"));
			buyer.setPhone_number(resultSet.getString("phone_number"));
			buyer.setAadhaar_id(resultSet.getString("aadhaar_id"));

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

			assert isBuyer(buyer);

			preparedStatement = connection.prepareStatement("DELETE FROM customers WHERE email = ?",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, buyer.getEmail());
			preparedStatement.executeUpdate();


			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return true;
	};

	// Check if buyer exists
	public boolean isBuyer(BuyerDTO buyer) throws Exception {

		try {

			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("SELECT email FROM customers WHERE email = ?");
			preparedStatement.setString(1, buyer.getEmail());
			ResultSet resultSet;
			resultSet = preparedStatement.executeQuery();

			return resultSet.next();

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public boolean Validation(BuyerDTO buyer) throws Exception {
		try {
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;

			ResultSet resultSet;

			assert isBuyer(buyer);

			preparedStatement = connection.prepareStatement("SELECT customers.password FROM customers WHERE email = ?",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, buyer.getEmail());
			resultSet = preparedStatement.executeQuery();

			resultSet.next();
			String password_check = resultSet.getString("password");

			if (password_check.equals(buyer.getPassword())) {
				resultSet.close();
				preparedStatement.close();
				connection.close();
				return true;
			} else {

				resultSet.close();
				preparedStatement.close();
				connection.close();

				return false;
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

}
