package com.amazonaws.compute.apnortheast1.ec21311515583.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.amazonaws.compute.apnortheast1.ec21311515583.dao.UserInfoDAO;
import com.amazonaws.compute.apnortheast1.ec21311515583.dto.TempUserDTO;
import com.opensymphony.xwork2.ActionSupport;

public class UserCreateCompleteAction extends ActionSupport implements SessionAware{

	private String successMsg;
	private String errorMsg;
	private Map<String, Object> session;

	public String execute(){

		boolean res = true;
		String result = SUCCESS;
		TempUserDTO tempUser = (TempUserDTO)session.get("tempUser");

		UserInfoDAO dao = new UserInfoDAO();
		res = dao.CreateUser(tempUser);

		if(res){
			successMsg = "ユーザー登録が完了しました。ログインしてください。";
			return result;
		}else{
			errorMsg = "正常に登録できませんでした。管理者に問い合わせてください。";
			result = ERROR;
			return result;
		}
	}


	public String getSuccessMsg() {
		return successMsg;
	}
	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
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
