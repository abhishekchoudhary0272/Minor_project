package com.agrify.dl;
import com.agrify.dl.*;
public class SellerDTO implements java.io.Serializable , Comparable<SellerDTO>{
	private String sellerId;
	private String fName;
	private String uName;
	private String govtNum;
	private int pNum;
	private String password;
	private String confPassword;
	private String gender;
	private String sellerBuyer;
	public SellerDTO(){
		this.sellerId = "";
		this.fName = "";
		this.uName = "";
		this.govtNum = "";
		this.pNum = 0;
		this.password = "";
		this.confPassword = "";
		this.gender = "";
		this.sellerBuyer = "";
	}
	public void setSellerId(java.lang.String sellerId){
		this.sellerId = sellerId;
	}
	public java.lang.String getSellerId(){
		return this.sellerId;
	}
	public void setFName(java.lang.String fName){
		this.fName = fName;
	}
	public java.lang.String getFName(){
		return this.fName;
	}
	public void setUName(java.lang.String uName){
		this.uName = uName;
	}
	public java.lang.String getUName(){
		return this.uName;
	}
	public void setGovtNum(java.lang.String govtNum){
		this.govtNum = govtNum;
	}
	public java.lang.String getGovtNum(){
		return this.govtNum;
	}
	public void setPNum(int pNum){
		this.pNum = pNum;
	}
	public int getPNum(){
		return this.pNum;
	}
	public void setPassword(java.lang.String password){
		this.password = password;
	}
	public java.lang.String getPassword(){
		return this.password;
	}
	public void setConfPassword(java.lang.String confPassword){
		this.confPassword = confPassword;
	}
	public java.lang.String getConfPassword(){
		return this.confPassword;
	}
	public void setGender(java.lang.String gender){
		this.gender = gender;
	}
	public java.lang.String getGender(){
		return this.gender;
	}
	public void setSellerBuyer(java.lang.String sellerBuyer){
		this.sellerBuyer = sellerBuyer;
	}
	public java.lang.String getSellerBuyer(){
		return this.sellerBuyer;
	}
	public int hashCode(){
		return sellerId.hashCode();
	}
	public boolean equals(Object object){
		if(!(object instanceof SellerDTO)) return false;
		SellerDTO seller = (SellerDTO)object;
		return this.sellerId == seller.sellerId;
	}
	public int compareTo(SellerDTO seller){
		return this.sellerId.compareTo(seller.sellerId);	
	}
}
