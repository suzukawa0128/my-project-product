package com.amazonaws.compute.apnortheast1.ec21311515583.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.amazonaws.compute.apnortheast1.ec21311515583.dao.RecipeDAO;
import com.amazonaws.compute.apnortheast1.ec21311515583.dto.RecipeDTO;
import com.opensymphony.xwork2.ActionSupport;


public class RecipeDetailAction extends ActionSupport implements SessionAware{

	private String dishId;
	private Map<String, Object> session;

	public String execute(){
		if(session.isEmpty()){
			return "sessionTimeout";
		}
		RecipeDAO dao = new RecipeDAO();
		RecipeDTO recipe = new RecipeDTO();
		recipe = dao.getRecipeInfo(dishId);
		session.put("dishId", dishId);
		session.put("author", recipe.getAuthor());
		session.put("dishName", recipe.getDishName());
		session.put("dishInfo", recipe.getDishInfo());
		session.put("tagList", recipe.getTagList());
		session.put("ingAmountList", recipe.getIngAmountList());
		session.put("procList", recipe.getProcList());
		session.put("cookingTime", recipe.getCookingTime());
		session.put("imageFilePath", recipe.getImageFilePath());
		session.put("imageFileName", recipe.getImageFileName());
		return SUCCESS;
	}

	public String getDishId() {
		return dishId;
	}
	public void setDishId(String dishId) {
		this.dishId = dishId;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
