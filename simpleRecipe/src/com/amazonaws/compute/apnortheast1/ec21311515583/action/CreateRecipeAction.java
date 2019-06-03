package com.amazonaws.compute.apnortheast1.ec21311515583.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.amazonaws.compute.apnortheast1.ec21311515583.dao.RecipeDAO;
import com.amazonaws.compute.apnortheast1.ec21311515583.dto.PaginationDTO;
import com.amazonaws.compute.apnortheast1.ec21311515583.dto.RecipeDTO;
import com.amazonaws.compute.apnortheast1.ec21311515583.util.Pagination;
import com.opensymphony.xwork2.ActionSupport;


public class CreateRecipeAction extends ActionSupport implements SessionAware{

	private boolean publicity;
	private String errorMsg;
	private Map<String, Object> session;

	@SuppressWarnings("unchecked")
	public String execute(){
		if(session.isEmpty()){
			return "sessionTimeout";
		}
		String result = SUCCESS;
		RecipeDAO dao = new RecipeDAO();
		RecipeDTO recipe = new RecipeDTO();
		boolean res = false;

		String dishName = String.valueOf(session.get("dishName"));
		if(dishName =="" || dishName == "null"){
			errorMsg = "料理名は必ず登録してください";
			return ERROR;
		}
		recipe.setAuthor(String.valueOf(session.get("userId")));
		recipe.setDishId(String.valueOf(session.get("dishId")));
		recipe.setDishName(String.valueOf(session.get("dishName")));
		if(String.valueOf(session.get("dishInfo"))!="null"){
			recipe.setDishInfo(String.valueOf(session.get("dishInfo")));
		}
		recipe.setTagList((List<String>)session.get("tagList"));
		recipe.setIngAmountList((List<List<String>>)session.get("ingAmountList"));
		recipe.setProcList((List<String>)session.get("procList"));
		recipe.setImageFilePath(String.valueOf(session.get("imageFilePath")));
		recipe.setImageFileName(String.valueOf(session.get("imageFileName")));
		recipe.setPublicity(publicity);
		if(session.get("cookingTime")!=null){
			recipe.setCookingTime(Integer.parseInt(session.get("cookingTime").toString()));
		}

		String dishId = recipe.getDishId();
		if(!(session.containsKey("dishId"))){
			res = dao.insertRecipeInfo(recipe);
			if(!res){
				errorMsg = "失敗しました";
			}
		}else{
			res = dao.updateRecipeInfo(recipe, dishId);
			if(res){
				errorMsg = "既に登録してあるレシピを更新しました。";
			}
		}
		session.remove("dishName");
		session.remove("dishInfo");
		session.remove("tagList");
		session.remove("ingAmountList");
		session.remove("procList");
		session.remove("cookingTime");
		session.remove("imageFilePath");
		session.remove("imageFileName");
		List<RecipeDTO> myRecipeList = new ArrayList<RecipeDTO>();
		myRecipeList = dao.getUsersRecipe(String.valueOf(session.get("userId")));
		session.put("myRecipeList", myRecipeList);
		Pagination pagination = new Pagination();
		PaginationDTO paginationDTO = new PaginationDTO();
		paginationDTO = pagination.getPage(myRecipeList, 20, 1);
		session.put("totalPageSize", paginationDTO.getTotalPageSize());
		session.put("currentPageNo", paginationDTO.getCurrentPageNo());
		session.put("myRecipeListForCurrentPage", paginationDTO.getRecipeListForCurrentPage());
		return result;
	}

	public boolean isPublicity() {
		return publicity;
	}
	public void setPublicity(boolean publicity) {
		this.publicity = publicity;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
