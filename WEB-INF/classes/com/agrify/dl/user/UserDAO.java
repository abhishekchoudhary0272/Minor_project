package com.agrify.dl.user;

import java.util.ArrayList;

/**
 * UserDAO
 */
public interface UserDAO {

	// Create or insert a user
	void insertUser(UserDTO user) throws Exception;

	// Update user data
	void updateUser(UserDTO user) throws Exception;

	// Select/Get user data
	UserDTO selectUser(UserDTO user) throws Exception;

	// Delete user
	boolean deleteUser(UserDTO user) throws Exception;

	// Is user, check if user exists
	boolean isUser(UserDTO user) throws Exception;

	// Validate user's email and password
	boolean validation(UserDTO user) throws Exception;

	// Present, check if the user's email is present in the database
	boolean present(UserDTO user) throws Exception;

	// Get all users present in the database
	ArrayList<UserDTO> getAll() throws Exception;
}