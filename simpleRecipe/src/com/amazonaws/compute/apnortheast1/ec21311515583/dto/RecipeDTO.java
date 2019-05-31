package com.amazonaws.compute.apnortheast1.ec21311515583.dto;

import java.util.List;

public class RecipeDTO {

	private String author; //（必須）
	private String imageFilePath;
	private String imageFileName;
	private String dishId; // dish_infoテーブルのid
	private String dishName; //料理の名前（必須）
	private String dishInfo; //料理の説明
	private List<String> tagList; //Actionでtagをリストに格納する
	private List<List<String>> ingAmountList; //Actionで材料・分量をまとめてリストに格納する
	private List<String> procList; //Actionで手順をリストに格納する
	private String cookingTip; //料理のコツ
	private int cookingTime;
	private boolean publicity; //公開するかどうか

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getImageFilePath() {
		return imageFilePath;
	}
	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public String getDishId() {
		return dishId;
	}
	public void setDishId(String dishId) {
		this.dishId = dishId;
	}
	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	public String getDishInfo() {
		return dishInfo;
	}
	public void setDishInfo(String dishInfo) {
		this.dishInfo = dishInfo;
	}
	public List<String> getTagList() {
		return tagList;
	}
	public void setTagList(List<String> tagList) {
		this.tagList = tagList;
	}
	public List<List<String>> getIngAmountList() {
		return ingAmountList;
	}
	public void setIngAmountList(List<List<String>> ingAmountList) {
		this.ingAmountList = ingAmountList;
	}
	public List<String> getProcList() {
		return procList;
	}
	public void setProcList(List<String> procList) {
		this.procList = procList;
	}
	public String getCookingTip() {
		return cookingTip;
	}
	public void setCookingTip(String cookingTip) {
		this.cookingTip = cookingTip;
	}
	public int getCookingTime() {
		return cookingTime;
	}
	public void setCookingTime(int cookingTime) {
		this.cookingTime = cookingTime;
	}
	public boolean isPublicity() {
		return publicity;
	}
	public void setPublicity(boolean publicity) {
		this.publicity = publicity;
	}
}
