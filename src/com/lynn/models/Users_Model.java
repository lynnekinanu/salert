package com.lynn.models;

public class Users_Model {
	String firstname, lastname, email, password;

	public void setUserFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getUserFirstname() {
		return this.firstname;
	}
	public void setUserLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUserLastname() {
		return this.lastname;
	}
	public void setUserEmail(String email) {
		this.email = email;
	} 
	public String getUserEmail() {
		return this.email;
	} 
	public void setUserPassword(String password) {
		this.password = password;
	} 
	public String getUserPassword() {
		return this.password;
	}
	
	
}
