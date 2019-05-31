package com.amazonaws.compute.apnortheast1.ec21311515583.dto;

public class TempUserDTO{

	private String userId;
	private String mail;
	private String pass;

	public TempUserDTO(String userId, String mail, String pass){
		this.userId = userId;
		this.mail = mail;
		this.pass = pass;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMail(){
		return this.mail;
	}
	public void setMail(String mail){
		this.mail = mail;
	}
	public String getPass(){
		return this.pass;
	}
	public void setPass(String pass){
		this.pass = pass;
	}

}