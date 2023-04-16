package com.agrify.dl.seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
			ResultSet resultSet;
			assert isSeller(seller);

			preparedStatement = connection.prepareStatement(
					"INSERT INTO retailers (email, first_name, last_name, password, birth, phone_number, aadhaar_id) VALUES (?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, seller.getEmail());
			preparedStatement.setString(2, seller.getFirst_name());
			preparedStatement.setString(3, seller.getLast_name());
			preparedStatement.setString(4, seller.getPassword());
			preparedStatement.setString(5, seller.getBirth());
			preparedStatement.setString(6, seller.getPhone_number());
			preparedStatement.setString(7, seller.getAadhaar_id());
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();

			resultSet.next();
			seller.setId(resultSet.getString(1));

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
			assert isSeller(seller);

			preparedStatement = connection.prepareStatement(
					"UPDATE retailers SET first_name = ?, last_name = ?, password = ?, email = ?, birth = ?, phone_number = ?, aadhaar_id = ? WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, seller.getFirst_name());
			preparedStatement.setString(2, seller.getLast_name());
			preparedStatement.setString(3, seller.getPassword());
			preparedStatement.setString(4, seller.getEmail());
			preparedStatement.setString(5, seller.getBirth());
			preparedStatement.setString(6, seller.getPhone_number());
			preparedStatement.setString(7, seller.getAadhaar_id());
			preparedStatement.setString(8, seller.getId());
			preparedStatement.executeUpdate();

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
			ResultSet resultSet;

			assert isSeller(seller);

			preparedStatement = connection.prepareStatement("SELECT * FROM retailers WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, seller.getId());

			resultSet = preparedStatement.executeQuery();

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

			assert isSeller(seller);

			preparedStatement = connection.prepareStatement("DELETE FROM retailers WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, seller.getId());
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return true;
	}

	// Check if seller exists
	public boolean isSeller(SellerDTO seller) throws Exception {

		try {

			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("SELECT email FROM retailers WHERE id = ?");
			preparedStatement.setString(1, seller.getId());
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

	public boolean Validation(SellerDTO seller) throws Exception {
		try {
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;

			ResultSet resultSet;

			preparedStatement = connection.prepareStatement(
					"SELECT retailers.id, retailers.password FROM retailers WHERE email = ?",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, seller.getEmail());
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				return false;
			}

			String password_check = resultSet.getString("password");
			String id = resultSet.getString("id");

			if (password_check.equals(seller.getPassword())) {

				seller.setId(id);
				assert (isSeller(seller));
				seller = selectSeller(seller);

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

	};

}
