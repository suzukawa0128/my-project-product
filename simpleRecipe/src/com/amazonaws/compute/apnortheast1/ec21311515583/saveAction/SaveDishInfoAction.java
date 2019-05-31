package com.amazonaws.compute.apnortheast1.ec21311515583.saveAction;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class SaveDishInfoAction extends ActionSupport implements SessionAware{

	private String dishInfo;
	private Map<String, Object> session;

	public String execute(){
		session.put("dishInfo", dishInfo);
		return SUCCESS;
	}

	public String getDishInfo() {
		return dishInfo;
	}
	public void setDishInfo(String dishInfo) {
		this.dishInfo = dishInfo;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
