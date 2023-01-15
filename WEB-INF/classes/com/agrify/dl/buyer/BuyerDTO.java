package com.agrify.dl;
import com.agrify.dl.*;
public class BuyerDTO implements java.io.Serializable , Comparable<BuyerDTO>{
	private String buyerId;
	private String fName;
	private String uName;
	private String govtNum;
	private int pNum;
	private String password;
	private String confPassword;
	private String gender;
	private String sellerBuyer;
	public BuyerDTO(){
		this.buyerId = "";
		this.fName = "";
		this.uName = "";
		this.govtNum = "";
		this.pNum = 0;
		this.password = "";
		this.confPassword = "";
		this.gender = "";
		this.sellerBuyer = "";
	}
	public void setBuyerId(java.lang.String buyerId){
		this.buyerId = buyerId;
	}
	public java.lang.String getBuyerId(){
		return this.buyerId;
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
	public int hashCode(){
		return buyerId.hashCode();
	}
	public boolean equals(Object object){
		if(!(object instanceof BuyerDTO)) return false;
		BuyerDTO buyer = (BuyerDTO)object;
		return this.buyerId == buyer.buyerId;
	}
	public int compareTo(BuyerDTO buyer){
		return this.buyerId.compareTo(buyer.buyerId);	
	}
}
