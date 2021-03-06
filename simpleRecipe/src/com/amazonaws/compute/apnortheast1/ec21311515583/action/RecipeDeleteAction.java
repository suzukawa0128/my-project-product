package com.amazonaws.compute.apnortheast1.ec21311515583.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.amazonaws.compute.apnortheast1.ec21311515583.dao.RecipeDAO;
import com.amazonaws.compute.apnortheast1.ec21311515583.dao.TagDAO;
import com.amazonaws.compute.apnortheast1.ec21311515583.dto.PaginationDTO;
import com.amazonaws.compute.apnortheast1.ec21311515583.dto.RecipeDTO;
import com.amazonaws.compute.apnortheast1.ec21311515583.util.Pagination;
import com.opensymphony.xwork2.ActionSupport;

public class RecipeDeleteAction extends ActionSupport implements SessionAware{

	private Map<String, Object> session;

	public String execute(){
		if(session.isEmpty()){
			return "sessionTimeout";
		}
		String result = ERROR;
		boolean hasTag = false;
		boolean tagRes = false;
		boolean recipeRes = false;
		RecipeDAO recipeDAO = new RecipeDAO();
		TagDAO tagDAO = new TagDAO();
		String dishId = String.valueOf(session.get("dishId"));
		hasTag = tagDAO.existsTagMap(dishId);
		if(hasTag){
			tagRes = tagDAO.deleteRecipesTagMap(dishId);
			if(!tagRes){
				return result;
			}
		}
		recipeRes = recipeDAO.deleteRecipe(dishId);
		if(recipeRes){
			result = SUCCESS;
			List<RecipeDTO> recipeList = new ArrayList<RecipeDTO>();
			recipeList = recipeDAO.getUsersRecipe(String.valueOf(session.get("userId")));
			session.put("recipeList", recipeList);
		}
		List<RecipeDTO> myRecipeList = new ArrayList<RecipeDTO>();
		myRecipeList = recipeDAO.getUsersRecipe(String.valueOf(session.get("userId")));
		session.put("myRecipeList", myRecipeList);
		Pagination pagination = new Pagination();
		PaginationDTO paginationDTO = new PaginationDTO();
		paginationDTO = pagination.getPage(myRecipeList, 20, 1);
		session.put("totalPageSize", paginationDTO.getTotalPageSize());
		session.put("currentPageNo", paginationDTO.getCurrentPageNo());
		session.put("myRecipeListForCurrentPage", paginationDTO.getRecipeListForCurrentPage());
		return result;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
