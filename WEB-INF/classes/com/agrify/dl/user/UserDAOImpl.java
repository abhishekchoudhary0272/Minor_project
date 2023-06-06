package com.agrify.dl.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.agrify.dl.DAOConnection;
import com.agrify.dl.user.UserRole.role;

/**
 * UserDAOImpl
 */
public class UserDAOImpl implements UserDAO {

	// Create or insert a user
	public void insertUser(UserDTO user) throws Exception {
		try {
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			ResultSet resultSet;
			assert isUser(user);

			preparedStatement = connection.prepareStatement(
					"INSERT INTO users (role, email, first_name, last_name, password, birth, phone_number, aadhaar_id) VALUES (?,?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, user.getUser_role().toString());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getFirst_name());
			preparedStatement.setString(4, user.getLast_name());
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.setString(6, user.getBirth());
			preparedStatement.setString(7, user.getPhone_number());
			preparedStatement.setString(8, user.getAadhaar_id());
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();

			resultSet.next();
			user.setId(resultSet.getString(1));

			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return;
	};

	// Update user data
	public void updateUser(UserDTO user) throws Exception {
		try {
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			assert isUser(user);

			preparedStatement = connection.prepareStatement(
					"UPDATE users SET first_name = ?, last_name = ?, password = ?, email = ?, birth = ?, phone_number = ?, aadhaar_id = ?, role = ? WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, user.getFirst_name());
			preparedStatement.setString(2, user.getLast_name());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getBirth());
			preparedStatement.setString(6, user.getPhone_number());
			preparedStatement.setString(7, user.getAadhaar_id());
			preparedStatement.setString(8, user.getUser_role().toString());
			preparedStatement.setString(9, user.getId());
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return;
	};

	// Select/Get user data
	public UserDTO selectUser(UserDTO user) throws Exception {
		try {
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			ResultSet resultSet;

			assert isUser(user);

			preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, user.getId());

			resultSet = preparedStatement.executeQuery();

			resultSet.next();
			user.setFirst_name(resultSet.getString("first_name"));
			user.setLast_name(resultSet.getString("last_name"));
			user.setBirth(resultSet.getString("birth"));
			user.setEmail(resultSet.getString("email"));
			user.setPassword(resultSet.getString("password"));
			user.setPhone_number(resultSet.getString("phone_number"));
			user.setAadhaar_id(resultSet.getString("aadhaar_id"));
			user.setUser_role(role.valueOf(resultSet.getString("role")));

			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return user;
	};

	// Delete user
	public boolean deleteUser(UserDTO user) throws Exception {
		try {
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;

			assert isUser(user);

			preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, user.getId());
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return true;
	}

	// Is user, check if user exists
	public boolean isUser(UserDTO user) throws Exception {

		try {

			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("SELECT email FROM users WHERE id = ?");
			preparedStatement.setString(1, user.getId());
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

	// Validate user's email and password
	public boolean validation(UserDTO user) throws Exception {
		try {
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;

			ResultSet resultSet;

			preparedStatement = connection.prepareStatement(
					"SELECT users.id, users.password FROM users WHERE email = ?",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, user.getEmail());
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				return false;
			}

			String password_check = resultSet.getString("password");
			String id = resultSet.getString("id");

			if (password_check.equals(user.getPassword())) {

				user.setId(id);
				assert (isUser(user));
				user = selectUser(user);

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

	// Present, check if the user's email is present in the database
	public boolean present(UserDTO user) throws Exception {
		try {
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;

			ResultSet resultSet;

			preparedStatement = connection.prepareStatement(
					"SELECT users.email FROM users WHERE email = ?",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, user.getEmail());
			resultSet = preparedStatement.executeQuery();

			return resultSet.next();

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	// Get all users present in the database
	public ArrayList<UserDTO> getAll() throws Exception {
		try {
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			ResultSet resultSet;

			ArrayList<UserDTO> user = new ArrayList<UserDTO>();
			
			preparedStatement = connection.prepareStatement("SELECT * FROM users", Statement.RETURN_GENERATED_KEYS);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				UserDTO tempUser = new UserDTO();
				
				tempUser.setId(resultSet.getString("id"));
				tempUser.setFirst_name(resultSet.getString("first_name"));
				tempUser.setLast_name(resultSet.getString("last_name"));
				tempUser.setBirth(resultSet.getString("birth"));
				tempUser.setEmail(resultSet.getString("email"));
				tempUser.setPassword(resultSet.getString("password"));
				tempUser.setPhone_number(resultSet.getString("phone_number"));
				tempUser.setAadhaar_id(resultSet.getString("aadhaar_id"));
				tempUser.setUser_role(role.valueOf(resultSet.getString("role")));

				user.add(tempUser);
			}

			resultSet.close();
			preparedStatement.close();
			connection.close();
			
			return user;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
