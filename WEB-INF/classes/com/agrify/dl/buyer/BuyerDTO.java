package com.agrify.dl.buyer;

/**
 * BuyerDTO
 */
public class BuyerDTO {

	private int id;
	private String name;
	private String birth;
	private String aadhaar_id;

	public BuyerDTO(int id, String name, String birth) {
		this.id = id;
		this.name = name;
		this.birth = birth;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setBirth(String birth) {
		this.birth = birth;
	}
	
	public void setName(String name) {
		this.name = name;
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
	
	public String getName() {
		return name;
	}

	public String getAadhaar_id() {
		return aadhaar_id;
	}
}
