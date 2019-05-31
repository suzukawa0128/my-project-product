package com.amazonaws.compute.apnortheast1.ec21311515583.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.amazonaws.compute.apnortheast1.ec21311515583.dao.RecipeDAO;
import com.amazonaws.compute.apnortheast1.ec21311515583.dto.RecipeDTO;
import com.opensymphony.xwork2.ActionSupport;

public class GoMyPageAction extends ActionSupport implements SessionAware{

	private Map<String, Object> session;

	public String execute(){
		String result = ERROR;

		if(session.containsKey("userId")){
			result = SUCCESS;
			RecipeDAO recipeDAO = new RecipeDAO();
			List<RecipeDTO> recipeList = new ArrayList<RecipeDTO>();
			recipeList = recipeDAO.getUsersRecipe(String.valueOf(session.get("userId")));
			session.put("recipeList", recipeList);
		}
		return result;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
