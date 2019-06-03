package com.amazonaws.compute.apnortheast1.ec21311515583.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.amazonaws.compute.apnortheast1.ec21311515583.dto.PaginationDTO;
import com.amazonaws.compute.apnortheast1.ec21311515583.dto.RecipeDTO;
import com.amazonaws.compute.apnortheast1.ec21311515583.util.Pagination;
import com.opensymphony.xwork2.ActionSupport;

public class GoThisPageAction extends ActionSupport implements SessionAware{

	private boolean publicAllFlg;
	private boolean publicTagFlg;
	private boolean myPageAllFlg;
	private boolean myPageTagFlg;
	private boolean adminPageFlg;
	private int pageNo;
	private Map<String, Object> session;

	@SuppressWarnings("unchecked")
	public String execute(){
		if(session.isEmpty()){
			return "sessionTimeout";
		}
		String result = null;
		Pagination pagination = new Pagination();
		if(publicAllFlg){
			PaginationDTO dto = new PaginationDTO();
			dto = pagination.getPage((List<RecipeDTO>)session.get("publicRecipeList"), 20, pageNo);
			session.put("totalPageSize", dto.getTotalPageSize());
			session.put("currentPageNo", dto.getCurrentPageNo());
			session.put("publicRecipeListForCurrentPage", dto.getRecipeListForCurrentPage());
			result = "publicRecipe";
		}else if(publicTagFlg){
			PaginationDTO dto = new PaginationDTO();
			dto = pagination.getPage((List<RecipeDTO>)session.get("publicTagRecipeList"), 20, pageNo);
			session.put("totalPageSize", dto.getTotalPageSize());
			session.put("currentPageNo", dto.getCurrentPageNo());
			session.put("publicTagRecipeListForCurrentPage", dto.getRecipeListForCurrentPage());
			result = "publicRecipe";
		}else if(myPageAllFlg){
			PaginationDTO dto = new PaginationDTO();
			dto = pagination.getPage((List<RecipeDTO>)session.get("myRecipeList"), 20, pageNo);
			session.put("totalPageSize", dto.getTotalPageSize());
			session.put("currentPageNo", dto.getCurrentPageNo());
			session.put("myRecipeListForCurrentPage", dto.getRecipeListForCurrentPage());
			result = "myPage";
		}else if(myPageTagFlg){
			PaginationDTO dto = new PaginationDTO();
			dto = pagination.getPage((List<RecipeDTO>)session.get("myTagRecipeList"), 20, pageNo);
			session.put("totalPageSize", dto.getTotalPageSize());
			session.put("currentPageNo", dto.getCurrentPageNo());
			session.put("myTagRecipeListForCurrentPage", dto.getRecipeListForCurrentPage());
			result = "myPage";
		}else if(adminPageFlg){
			PaginationDTO dto = new PaginationDTO();
			dto = pagination.getPage((List<RecipeDTO>)session.get("allRecipeList"), 20, pageNo);
			session.put("totalPageSize", dto.getTotalPageSize());
			session.put("currentPageNo", dto.getCurrentPageNo());
			session.put("allRecipeListForCurrentPage", dto.getRecipeListForCurrentPage());
			result = "admin";
		}
		return result;
	}

	public boolean isPublicAllFlg() {
		return publicAllFlg;
	}
	public void setPublicAllFlg(boolean publicAllFlg) {
		this.publicAllFlg = publicAllFlg;
	}
	public boolean isPublicTagFlg() {
		return publicTagFlg;
	}
	public void setPublicTagFlg(boolean publicTagFlg) {
		this.publicTagFlg = publicTagFlg;
	}
	public boolean isMyPageAllFlg() {
		return myPageAllFlg;
	}
	public void setMyPageAllFlg(boolean myPageAllFlg) {
		this.myPageAllFlg = myPageAllFlg;
	}
	public boolean isMyPageTagFlg() {
		return myPageTagFlg;
	}
	public void setMyPageTagFlg(boolean myPageTagFlg) {
		this.myPageTagFlg = myPageTagFlg;
	}
	public boolean isAdminPageFlg() {
		return adminPageFlg;
	}
	public void setAdminPageFlg(boolean adminPageFlg) {
		this.adminPageFlg = adminPageFlg;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
