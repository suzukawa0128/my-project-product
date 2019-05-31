package com.amazonaws.compute.apnortheast1.ec21311515583.saveAction;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class SaveDishNameAction extends ActionSupport implements SessionAware{

	private String dishName;
	private Map<String, Object> session;

	public String execute(){
		session.put("dishName", dishName);
		return SUCCESS;
	}

	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
