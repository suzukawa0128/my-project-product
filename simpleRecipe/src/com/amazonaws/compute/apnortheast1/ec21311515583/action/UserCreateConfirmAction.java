package com.amazonaws.compute.apnortheast1.ec21311515583.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.amazonaws.compute.apnortheast1.ec21311515583.dao.UserInfoDAO;
import com.amazonaws.compute.apnortheast1.ec21311515583.dto.TempUserDTO;
import com.opensymphony.xwork2.ActionSupport;

public class UserCreateConfirmAction extends ActionSupport implements SessionAware{

	private String userId;
	private String mail;
	private String pass;
	private String errorMsg;
	private Map<String, Object> session;

	public String execute(){
		TempUserDTO tempUser = new TempUserDTO(userId, mail, pass);
		UserInfoDAO dao = new UserInfoDAO();
		boolean userIdRes = dao.TempUserIdCheck(tempUser);
		boolean mailRes = dao.TempUserMailCheck(tempUser);
		String result = SUCCESS;

		if(userIdRes && mailRes && !(mail.equals("")) && !(pass.equals(""))){
			session.put("tempUser", tempUser);
			return result;
		}else if(userIdRes == false){
			errorMsg = "既に登録されているユーザーIDです";
			result = ERROR;
			return result;
		}else if(mailRes == false){
			errorMsg = "既に登録されているメールアドレスです";
			result = ERROR;
			return result;
		}else{
			errorMsg = "記入されていない項目があります";
			result = ERROR;
			return result;
		}
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
