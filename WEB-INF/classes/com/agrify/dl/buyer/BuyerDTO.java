package com.agrify.dl.buyer;

/**
 * SellerDTO
 */
public class BuyerDTO {

	private int id;
	private String name;
	private String birth;
	// private String firstName;
	// private String lastName;

	// public BuyerDTO(int id, String firstName, String lastName) {
	public BuyerDTO(int id, String name, String birth) {
		this.id = id;
		this.name = name;
		this.birth = birth;
		// this.firstName = firstName;
		// this.lastName = lastName;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setBirth(String birth) {
		this.birth = birth;
	}

	// public void setFirstName(String firstName) {
	// 	this.firstName = firstName;
	// }

	// public void setLastName(String lastName) {
	// 	this.lastName = lastName;
	// }
	
	public void SetName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}
	
	public String getBirth() {
		return birth;
	}

	// public String getFirstName() {
	// 	return firstName;
	// }

	// public String getLastName() {
	// 	return lastName;
	// }
	
	public String getName() {
		return name;
	}

}