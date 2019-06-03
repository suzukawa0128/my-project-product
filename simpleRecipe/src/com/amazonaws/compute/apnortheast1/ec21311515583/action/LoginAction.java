package com.amazonaws.compute.apnortheast1.ec21311515583.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.amazonaws.compute.apnortheast1.ec21311515583.dao.RecipeDAO;
import com.amazonaws.compute.apnortheast1.ec21311515583.dao.UserInfoDAO;
import com.amazonaws.compute.apnortheast1.ec21311515583.dto.PaginationDTO;
import com.amazonaws.compute.apnortheast1.ec21311515583.dto.RecipeDTO;
import com.amazonaws.compute.apnortheast1.ec21311515583.util.Pagination;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware{

	private String mail;
	private String pass;
	private Map<String, Object> session;

	public String execute(){
		String result = ERROR;
		boolean adminRes = false;
		boolean res = false;
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		adminRes = userInfoDAO.adminCheck(mail, pass);
		if(adminRes){
			RecipeDAO dao = new RecipeDAO();
			String userId = userInfoDAO.getUserIdFromDB(mail);
			session.put("userId", userId);
			session.put("adminFlg", "1");
			List<RecipeDTO> allRecipeList = new ArrayList<RecipeDTO>();
			allRecipeList = dao.getAllRecipe();
			session.put("allRecipeList", allRecipeList);
			Pagination pagination = new Pagination();
			PaginationDTO paginationDTO = new PaginationDTO();
			paginationDTO = pagination.getPage(allRecipeList, 20, 1);
			session.put("totalPageSize", paginationDTO.getTotalPageSize());
			session.put("currentPageNo", paginationDTO.getCurrentPageNo());
			session.put("allRecipeListForCurrentPage", paginationDTO.getRecipeListForCurrentPage());
			return "admin";
		}
		res = userInfoDAO.UserLogin(mail, pass);

		if(res){
			result = SUCCESS;
			String userId = userInfoDAO.getUserIdFromDB(mail);
			session.put("userId",userId);

			RecipeDAO recipeDAO = new RecipeDAO();
			List<RecipeDTO> recipeList = new ArrayList<RecipeDTO>();
			recipeList = recipeDAO.getUsersRecipe(userId);
			session.put("myRecipeList", recipeList);

			Pagination pagination = new Pagination();
			PaginationDTO paginationDTO = new PaginationDTO();
			paginationDTO = pagination.getPage(recipeList, 20, 1);
			session.put("totalPageSize", paginationDTO.getTotalPageSize());
			session.put("currentPageNo", paginationDTO.getCurrentPageNo());
			session.put("myRecipeListForCurrentPage", paginationDTO.getRecipeListForCurrentPage());
		}
		return result;
	}

	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
