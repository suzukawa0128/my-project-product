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

public class GoAdminRecipeAction extends ActionSupport implements SessionAware{

	private Map<String, Object> session;

	public String execute(){
		if(session.isEmpty()){
			return "sessionTimeout";
		}
		RecipeDAO dao = new RecipeDAO();
		List<RecipeDTO> allRecipeList = new ArrayList<RecipeDTO>();
		allRecipeList = dao.getAllRecipe();
		session.put("allRecipeList", allRecipeList);
		Pagination pagination = new Pagination();
		PaginationDTO paginationDTO = new PaginationDTO();
		paginationDTO = pagination.getPage(allRecipeList, 20, 1);
		session.put("totalPageSize", paginationDTO.getTotalPageSize());
		session.put("currentPageNo", paginationDTO.getCurrentPageNo());
		session.put("allRecipeListForCurrentPage", paginationDTO.getRecipeListForCurrentPage());
		return SUCCESS;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
