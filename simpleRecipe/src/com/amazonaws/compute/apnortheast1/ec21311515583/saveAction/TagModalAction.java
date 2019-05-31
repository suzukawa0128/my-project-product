package com.amazonaws.compute.apnortheast1.ec21311515583.saveAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class TagModalAction extends ActionSupport implements SessionAware{

	private String dishName;
	private String tag;
	private List<String> tagList = new ArrayList<String>();
	private Map<String, Object> session;

	public String execute(){

		//test
//		System.out.println("tagList");
//		System.out.println(tagList==null);
//		System.out.println(tagList.size()==0);
//		for(String tag:tagList){
//			System.out.println(tag);
//		}
//		System.out.println("tag");
//		System.out.println(tag);
		//test

//		何も変更せずokを押下した場合、tag==null
//		tag.isEmpty()でヌルポを避けるためSUCCESSを返す
//		tagListが変更されている場合はtagListをセッションに入れ直す必要がある
		if(tag==null){
			session.put("tagList", tagList);
			return SUCCESS;
		}else if(tag==null && tagList!=null){

		}
//		add rowを1回だけ押下して何も書かずokした場合、tag.isEmpty()
//		add rowを2回以上押下するとカンマ+スペースが入るのでsplitで処理
		if(!session.containsKey("tagList") && !(tag.isEmpty())){
			tagList = Arrays.asList(tag.split("[\\s]*,[\\s]*"));
		}else if(session.containsKey("tagList") && !(tag.isEmpty())){
			List<String> tagListByTag = new ArrayList<String>();
			tagListByTag = Arrays.asList(tag.split("[\\s]*,[\\s]*"));
			tagList.addAll(tagListByTag);
		}
//		tag.isEmpty()かつtagListに変更が加わった場合はセッションに戻す必要がある
		session.put("tagList", tagList);
		return SUCCESS;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List<String> getTagList() {
		return tagList;
	}

	public void setTagList(List<String> tagList) {
		this.tagList = tagList;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
