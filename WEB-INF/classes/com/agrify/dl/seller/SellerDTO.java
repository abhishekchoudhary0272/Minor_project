package com.agrify.dl.seller;

/**
 * SellerDTO
 */
public class SellerDTO {

	private int id;
	private String first_name;
	private String last_name;
	private String birth;
	private String password;
	private String email;
	private String phone_number;
	private String aadhaar_id;

	public SellerDTO() {
		this.id = 000000;
		this.first_name = "";
		this.birth = "";
		this.password = "";
		this.email = "";
		this.phone_number = "";
		this.aadhaar_id = "";
	}

	public SellerDTO(int id, String name, String birth) {
		this.id = id;
		this.first_name = name;
		this.birth = birth;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setBirth(String birth) {
		this.birth = birth;
	}
	
	public void setFirst_name(String name) {
		this.first_name = name;
	}
	
	public void setLast_name(String name) {
		this.last_name = name;
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

	public int getId() {
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

}
