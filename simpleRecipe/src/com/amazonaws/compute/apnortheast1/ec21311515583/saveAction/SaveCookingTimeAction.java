package com.amazonaws.compute.apnortheast1.ec21311515583.saveAction;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class SaveCookingTimeAction extends ActionSupport implements SessionAware{

	private int cookingTime;
	private Map<String, Object> session;

	public String execute(){
		session.put("cookingTime", cookingTime);
		return SUCCESS;
	}

	public int getCookingTime() {
		return cookingTime;
	}
	public void setCookingTime(int cookingTime) {
		this.cookingTime = cookingTime;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
