package com.agrify.dl.seller;

/**
 * SellerDTO
 */
public class SellerDTO {

	private int id;
	private String name;
	private String birth;

	public SellerDTO(int id, String name, String birth) {
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
	
	public void SetName(String name) {
		this.name = name;
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

}
