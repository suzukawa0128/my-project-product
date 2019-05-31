package com.amazonaws.compute.apnortheast1.ec21311515583.dto;

public class LoginDTO {

	private String mail;
	private String pass;

	public LoginDTO(String mail, String pass){
		this.mail = mail;
		this.pass = pass;
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
