package com.amazonaws.compute.apnortheast1.ec21311515583.saveAction;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.amazonaws.compute.apnortheast1.ec21311515583.util.CommonUtility;
import com.opensymphony.xwork2.ActionSupport;


public class SaveImageAction extends ActionSupport implements SessionAware{

	private Map<String,Object> session;
	private String imageFilePath;
	private String imageFileName;
//	画像用
	private File itemImage;
	private String itemImageContentType;
	private String itemImageFileName; //画像ファイルのファイル名

	private List<String> imageFilePathErrorMessageList=new ArrayList<String>();
	private List<String> itemImageFileNameErrorMessageList=new ArrayList<String>();

	public String execute(){
		String result=ERROR;

		session.put("imageFilePath",imageFilePath);
		session.put("imageFileName",imageFileName);
		session.put("itemImage",itemImage);

//		画像のファイルパス修正部分
		if(itemImage!=null){
			long fileMaxSize=3145728;//3MB
			String filePath = ServletActionContext.getServletContext().getRealPath("/").concat("images");

			System.out.println(filePath);
			System.out.println(itemImageFileName);

			File fileToCreate = new File(filePath, itemImageFileName);
//			TODO
//			itemImageFileNameのチェック
			if(!(itemImage(itemImageContentType))){
				itemImageFileNameErrorMessageList.add("画像ファイルが異なります。jpegのみ挿入できます。");
				result=ERROR;
			}
			if(itemImage.length()>fileMaxSize){
				itemImageFileNameErrorMessageList.add("3MBより大きい画像ファイルは挿入できません。");
				result=ERROR;
			}
			try{
				FileUtils.copyFile(itemImage, fileToCreate);
				session.put("image_file_name", itemImageFileName);
				session.put("image_file_path", "./images");
				session.put("image_flg", itemImageFileName);
			}catch(IOException e){
				e.printStackTrace();
			}

//		選択した画像ファイルをサーバーに保存する
			filePath = ServletActionContext.getServletContext().getRealPath("/").concat("images");
			CommonUtility commonUtility = new CommonUtility();
			itemImageFileName = commonUtility.getRandomValue()+itemImageFileName;

			//test
			System.out.println("二回目");
			System.out.println(filePath);
			System.out.println(itemImageFileName);

			fileToCreate = new File(filePath,itemImageFileName);
			try{
				FileUtils.copyFile(itemImage, fileToCreate);
				session.put("imageFileName", itemImageFileName);
				session.put("imageFilePath", "./images");
				session.put("image_flg", itemImageFileName);
				imageFileName = itemImageFileName;
				imageFilePath = "./images/";
			}catch(IOException e){
				e.printStackTrace();
			}
		}else{
			session.put("imageFileName","");
			result = ERROR;
		}

		if(imageFilePathErrorMessageList.size()==0 && itemImageFileNameErrorMessageList.size()==0){
				result=SUCCESS;
			}else{
				session.put("imageFileNameErrorMessageList",imageFilePathErrorMessageList);
				session.put("itemImageFileNameErrorMessageList",itemImageFileNameErrorMessageList);
				result=ERROR;
			}
		return result;
	}

	private boolean itemImage(String extension){
		return (extension.equals("image/jpeg") || extension.equals("image/png"));
	}
	public File getItemImage() {
		return itemImage;
	}
	public void setItemImage(File itemImage) {
		this.itemImage = itemImage;
	}
	public String getItemImageFileName() {
		return itemImageFileName;
	}
	public void setItemImageFileName(String itemImageFileName) {
		this.itemImageFileName = itemImageFileName;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
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
	public List<String> getImageFilePathErrorMessageList() {
		return imageFilePathErrorMessageList;
	}
	public void setImageFilePathErrorMessageList(List<String> imageFilePathErrorMessageList) {
		this.imageFilePathErrorMessageList = imageFilePathErrorMessageList;
	}
	public String getItemImageContentType() {
		return itemImageContentType;
	}
	public void setItemImageContentType(String itemImageContentType) {
		this.itemImageContentType = itemImageContentType;
	}
	public List<String> getItemImageFileNameErrorMessageList() {
		return itemImageFileNameErrorMessageList;
	}
	public void setItemImageFileNameErrorMessageList(List<String> itemImageFileNameErrorMessageList) {
		this.itemImageFileNameErrorMessageList = itemImageFileNameErrorMessageList;
	}
}
