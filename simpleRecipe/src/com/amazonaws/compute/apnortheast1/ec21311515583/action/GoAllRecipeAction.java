package com.amazonaws.compute.apnortheast1.ec21311515583.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.amazonaws.compute.apnortheast1.ec21311515583.dao.RecipeDAO;
import com.amazonaws.compute.apnortheast1.ec21311515583.dto.RecipeDTO;
import com.opensymphony.xwork2.ActionSupport;

public class GoAllRecipeAction extends ActionSupport implements SessionAware{

	private Map<String, Object> session;

	public String execute(){
		RecipeDAO recipeDAO = new RecipeDAO();
		List<RecipeDTO> publicRecipeList = new ArrayList<RecipeDTO>();
		publicRecipeList = recipeDAO.getPublicRecipe();
		session.put("publicRecipeList", publicRecipeList);
		return SUCCESS;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
