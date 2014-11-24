package com.lynn.models;

public class Customer_Model {
	String fullnames, mobile, email, facebook,twitter;
	int id;
	public void setCustomerID(int id) {
		this.id = id;
	}

	public int getCustomerID() {
		return this.id;
	}
	public void setCustomerFullnames(String fullnames) {
		this.fullnames = fullnames;
	}

	public String getCustomerFullnames() {
		return this.fullnames;
	}
	
	public void setCustomerMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCustomerMobile() {
		return this.mobile;
	}
	
	public void setCustomerEmail(String email) {
		this.email = email;
	} 
	public String getCustomerEmail() {
		return this.email;
	}
	public void setCustomerFacebook(String facebook) {
		this.facebook = facebook;
	} 
	public String getCustomerFacebook() {
		return this.facebook;
	}
	public void setCustomerTwitter(String twitter) {
		this.twitter = twitter;
	} 
	public String getCustomerTwitter() {
		return this.twitter;
	}
	
}
