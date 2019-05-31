package com.amazonaws.compute.apnortheast1.ec21311515583.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class GoCreateRecipeAction extends ActionSupport implements SessionAware{

	private Map<String, Object> session;

	public String execute(){
		session.remove("dishId");
		session.remove("dishName");
		session.remove("dishInfo");
		session.remove("tagList");
		session.remove("ingAmountList");
		session.remove("procList");
		session.remove("cookingTime");
		session.remove("imageFilePath");
		session.remove("imageFileName");
		return SUCCESS;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
