package com.amazonaws.compute.apnortheast1.ec21311515583.saveAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class SaveProcAction extends ActionSupport implements SessionAware{

	private String proc;
	private List<String> procList = new ArrayList<String>();
	private Map<String, Object> session;

	public String execute(){
		if(session.isEmpty()){
			return "sessionTimeout";
		}
//		手順がない、かつ手順の入力なし。もしくは、手順がある、かつ手順の入力なし
		if(proc==null){
			session.put("procList", procList);
			return SUCCESS;
		}
//		手順がない、かつ手順を入力した
		if(!session.containsKey("procList") && !(proc.isEmpty())){
			procList = Arrays.asList(proc.split("[\\s]*,[\\s]*"));
//		手順がある、かつ手順を入力した
		}else if(session.containsKey("procList") && !(proc.isEmpty())){
			List<String> procListByProc = new ArrayList<String>();
			procListByProc = Arrays.asList(proc.split("[\\s]*,[\\s]*"));
			procList.addAll(procListByProc);
		}
		session.put("procList", procList);
		return SUCCESS;
	}

	public String getProc() {
		return proc;
	}
	public void setProc(String proc) {
		this.proc = proc;
	}
	public List<String> getProcList() {
		return procList;
	}
	public void setProcList(List<String> procList) {
		this.procList = procList;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
