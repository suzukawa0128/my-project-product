package com.amazonaws.compute.apnortheast1.ec21311515583.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.amazonaws.compute.apnortheast1.ec21311515583.dao.SearchTagDAO;
import com.amazonaws.compute.apnortheast1.ec21311515583.dao.TagDAO;
import com.amazonaws.compute.apnortheast1.ec21311515583.dto.PaginationDTO;
import com.amazonaws.compute.apnortheast1.ec21311515583.dto.RecipeDTO;
import com.amazonaws.compute.apnortheast1.ec21311515583.util.Pagination;
import com.opensymphony.xwork2.ActionSupport;

public class SearchTagAction extends ActionSupport implements SessionAware{

	private String tagName;
	private String errorMsg;
	private boolean myPageFlg;
	private Map<String, Object> session;

	public String execute(){
		if(session.isEmpty()){
			return "sessionTimeout";
		}
		TagDAO tagDAO = new TagDAO();
		SearchTagDAO searchTagDAO = new SearchTagDAO();
		List<RecipeDTO> publicTagRecipeList = new ArrayList<RecipeDTO>();
		String tagId = tagDAO.getTagId(tagName);
		if(tagId==null){
			errorMsg = "一致するレシピはありませんでした。";
		}
		if(myPageFlg){
			List<RecipeDTO> myTagRecipeList = new ArrayList<RecipeDTO>();
			myTagRecipeList = searchTagDAO.searchTagFromMyRecipe(tagId, String.valueOf(session.get("userId")));
			if(myTagRecipeList==null){
				errorMsg = "一致するあなたのレシピはありませんでした。";
			}
			session.put("myTagRecipeList", myTagRecipeList);

			Pagination pagination = new Pagination();
			PaginationDTO paginationDTO = new PaginationDTO();
			paginationDTO = pagination.getPage(myTagRecipeList, 20, 1);
			session.put("totalPageSize", paginationDTO.getTotalPageSize());
			session.put("currentPageNo", paginationDTO.getCurrentPageNo());
			session.put("myTagRecipeListForCurrentPage", paginationDTO.getRecipeListForCurrentPage());
			return "myPage";
		}else{
			publicTagRecipeList = searchTagDAO.searchTagFromPublic(tagId);
			session.put("publicTagRecipeList", publicTagRecipeList);

			Pagination pagination = new Pagination();
			PaginationDTO paginationDTO = new PaginationDTO();
			paginationDTO = pagination.getPage(publicTagRecipeList, 20, 1);
			session.put("totalPageSize", paginationDTO.getTotalPageSize());
			session.put("currentPageNo", paginationDTO.getCurrentPageNo());
			session.put("publicTagRecipeListForCurrentPage", paginationDTO.getRecipeListForCurrentPage());
			return "publicRecipe";
		}
	}

	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public boolean isMyPageFlg() {
		return myPageFlg;
	}
	public void setMyPageFlg(boolean myPageFlg) {
		this.myPageFlg = myPageFlg;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
