package com.agrify.dl.user;

import com.agrify.dl.user.UserRole.role;

/**
 * UserDTO
 */
public class UserDTO {
	
	private String id;
	private String first_name;
	private String last_name;
	private String birth;
	private String password;
	private String email;
	private String phone_number;
	private String aadhaar_id;
	private role user_role;

	public UserDTO() {
		this.id = "";
		this.first_name = "";
		this.last_name = "";
		this.birth = "";
		this.password = "";
		this.email = "";
		this.phone_number = "";
		this.aadhaar_id = "";
		this.user_role = role.User;
	}
	
	public UserDTO(String id, String first_name, String last_name, String birth, String password, String email,
	String phone_number, String aadhaar_id, role role) {
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.birth = birth;
		this.password = password;
		this.email = email;
		this.phone_number = phone_number;
		this.aadhaar_id = aadhaar_id;
		this.user_role = role;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public void setFirst_name(String name) {
		this.first_name = name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	
	public void setAadhaar_id(String aadhaar_id) {
		this.aadhaar_id = aadhaar_id;
	}
	
	public void setUser_role(role user_role) {
		this.user_role = user_role;
	}

	public String getId() {
		return id;
	}

	public String getBirth() {
		return birth;
	}

	public String getFirst_name() {
		return first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public String getAadhaar_id() {
		return aadhaar_id;
	}

	public role getUser_role() {
		return user_role;
	}

}