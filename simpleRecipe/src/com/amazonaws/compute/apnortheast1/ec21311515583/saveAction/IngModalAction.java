package com.amazonaws.compute.apnortheast1.ec21311515583.saveAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class IngModalAction extends ActionSupport implements SessionAware{

	private String ing;
	private String amount;
	private List<List<String>> ingAmountList = new ArrayList<List<String>>(); //DAOに渡すリストはこれ
	private List<String> ingList = new ArrayList<String>();
	private List<String> amountList = new ArrayList<String>();
	private Map<String, Object> session;

	public String execute(){
		if(session.isEmpty()){
			return "sessionTimeout";
		}
//		TODO
//		分量のみの場合はエラーメッセージを表示する
		if(!session.containsKey("ingAmountList") && ing!=null){
			ingAmountList = createIngAmountList(ing, amount);
		}else if(session.containsKey("ingAmountList") && ing!=null){
			ingAmountList = createIngAmountList(ing, amount);
		}
		session.put("ingAmountList", ingAmountList);
		return SUCCESS;
	}

	public List<List<String>> createIngAmountList(String ing, String amount){
		if(amount==null){
			amount = " ";
		}else if(amount.startsWith(",")){
			amount = " " + amount;
		}
		ingList = Arrays.asList(ing.split("[\\s]*,[\\s]*"));
		amountList = Arrays.asList(amount.split(","));
		for(int i=0; i<ingList.size(); i++){
			if(!ingList.get(i).isEmpty()){
				List<String> ingSetList = new ArrayList<String>();
				if(ingList.get(i)==""){
					continue;
				}
				ingSetList.add(ingList.get(i));
				if(!(amountList.get(i)=="")){
					ingSetList.add(amountList.get(i));
				}else{
					ingSetList.add(" ");
				}
				ingAmountList.add(ingSetList);
			}
		}
		return ingAmountList;
	}

	public String getIng() {
		return ing;
	}
	public void setIng(String ing) {
		this.ing = ing;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public List<List<String>> getIngAmountList() {
		return ingAmountList;
	}
	public void setIngAmountList(List<List<String>> ingAmountList) {
		this.ingAmountList = ingAmountList;
	}
	public List<String> getIngList() {
		return ingList;
	}
	public void setIngList(List<String> ingList) {
		this.ingList = ingList;
	}
	public List<String> getAmountList() {
		return amountList;
	}
	public void setAmountList(List<String> amountList) {
		this.amountList = amountList;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
