package com.amazonaws.compute.apnortheast1.ec21311515583.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class EditRecipeAction extends ActionSupport implements SessionAware{

	private Map<String, Object> session;

	public String execute(){
		return SUCCESS;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
