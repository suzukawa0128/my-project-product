package com.amazonaws.compute.apnortheast1.ec21311515583.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.amazonaws.compute.apnortheast1.ec21311515583.dto.RecipeDTO;
import com.amazonaws.compute.apnortheast1.ec21311515583.util.DBConnector;


public class RecipeDAO{

//	insertDishInfoメソッドとinsertTagInfoメソッドを一度にやるメソッド
	public boolean insertRecipeInfo(RecipeDTO recipe){
		boolean ret = false;
		boolean dishRes = false;
		boolean tagRes = false;
		TagDAO tagDAO = new TagDAO();
		dishRes = insertDishInfo(recipe);
		tagRes = tagDAO.insertRecipesTagInfo(recipe);
		if(dishRes && tagRes){
			ret = true;
		}
		return ret;
	}

//	updateDishInfoとupdateTagInfoを一度にやるメソッド
	public boolean updateRecipeInfo(RecipeDTO recipe, String dishId){
		boolean ret = false;
		boolean dishRes = false;
		boolean tagRes = false;
		TagDAO tagDAO = new TagDAO();
		dishRes = updateDishInfo(recipe, dishId);
		tagRes = tagDAO.updateTagInfo(recipe, dishId);
		if(dishRes && tagRes){
			ret = true;
		}
		return ret;
	}

//	dish_infoテーブルに新しいレシピを登録するメソッド
	public boolean insertDishInfo(RecipeDTO recipe){
		boolean ret = false;
		String sql = "INSERT INTO dish_info"
				+ "(author, registered_date, updated_date, dish_name, dish_info, ing, " //1~4
				+ "proc_1, proc_2, proc_3, proc_4, proc_5, proc_6, proc_7, proc_8, proc_9, proc_10, " //5~14
				+ "cooking_time, image_file_path, image_file_name, publicity) " //15~18
				+ "VALUES(?,now(),now(),?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?)";
		Connection conn = null;
		try{
			DBConnector db = new DBConnector();
			conn = db.getConnection();
			PreparedStatement dishInfoPs = conn.prepareStatement(sql);
			dishInfoPs.setString(1, recipe.getAuthor());
			dishInfoPs.setString(2, recipe.getDishName());
			dishInfoPs.setString(3, recipe.getDishInfo());
			List<List<String>> ingAmountList = recipe.getIngAmountList();
			List<String> procList = recipe.getProcList();
			StringBuilder sb = new StringBuilder();
//			listをプレースホルダーにセットする
			if(ingAmountList!=null && ingAmountList.size()!=0){
				for(List<String> ingSetList:ingAmountList){
					for(String ingAmount:ingSetList){
						sb.append(ingAmount).append("/");
					}
					sb.setLength(sb.length()-1); //末尾のスラッシュを除去
					sb.append(",");
				}
				sb.setLength(sb.length()-1); //末尾のカンマを除去
				dishInfoPs.setString(4, sb.toString());
			}else{
				dishInfoPs.setString(4, null);
			}
			if(procList!=null){
				setPsForList(procList, dishInfoPs, 10, 5);
			}else{
				for(int i=0;i<10;i++){
					dishInfoPs.setString(i+5, null);
				}
			}
			dishInfoPs.setInt(15, recipe.getCookingTime());
			if(recipe.getImageFilePath()=="null"){
				dishInfoPs.setString(16, "./images");
			}else{
				dishInfoPs.setString(16, recipe.getImageFilePath());
			}
			if(recipe.getImageFileName()=="null"){
				dishInfoPs.setString(17, "img1.jpg");
			}else{
				dishInfoPs.setString(17, recipe.getImageFileName());
			}
			dishInfoPs.setBoolean(18, recipe.isPublicity());
			int r;
			r = dishInfoPs.executeUpdate();
			if(r>0){
				ret = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return ret;
	}

	public boolean updateDishInfo(RecipeDTO recipe, String dishId){
		boolean ret = false;
		int res = 0;
		String sql = "UPDATE dish_info SET updated_date=now(), dish_name=?, dish_info=?, ing=?, " //1~3
				+ "proc_1=?, proc_2=?, proc_3=?, proc_4=?, proc_5=?, proc_6=?, proc_7=?, proc_8=?, proc_9=?, proc_10=?, " //4~13
				+ "cooking_time=?, image_file_path=?, image_file_name=?, publicity=? " //14~17
				+ "WHERE id=?"; //18
		Connection conn = null;
		try{
			DBConnector db = new DBConnector();
			conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, recipe.getDishName());
			ps.setString(2, recipe.getDishInfo());
			List<List<String>> ingAmountList = recipe.getIngAmountList();
			List<String> procList = recipe.getProcList();
			StringBuilder sb = new StringBuilder();
//			listをプレースホルダーにセットする
			if(ingAmountList!=null && ingAmountList.size()!=0){
				for(List<String> ingSetList:ingAmountList){
					for(String ingAmount:ingSetList){
						sb.append(ingAmount).append("/");
					}
					sb.setLength(sb.length()-1); //末尾のスラッシュを除去
					sb.append(",");
				}
				sb.setLength(sb.length()-1); //末尾のカンマを除去
				ps.setString(3, sb.toString());
			}else{
				ps.setString(3, null);
			}
			if(procList!=null){
				setPsForList(procList, ps, 10, 4);
			}else{
				for(int i=0;i<10;i++){
					ps.setString(i+4, null);
				}
			}
			ps.setInt(14, recipe.getCookingTime());
			ps.setString(15, recipe.getImageFilePath());
			ps.setString(16, recipe.getImageFileName());
			ps.setBoolean(17, recipe.isPublicity());
			ps.setInt(18, Integer.parseInt(dishId));
			res = ps.executeUpdate();
			if(res>0){
				ret = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return ret;
	}

//	dishIdからレシピを削除するメソッド
	public boolean deleteRecipe(String dishId){
		boolean ret = false;
		int res = 0;
		String sql = "DELETE FROM dish_info WHERE id=?";
		Connection conn = null;
		try{
			DBConnector db = new DBConnector();
			conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(dishId));
			res = ps.executeUpdate();
			if(res>0){
				ret = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return ret;
	}

//	リストをプレースホルダーにセットするメソッド。listがcountに満たないと残りはnullをセットする。
	public void setPsForList(List<String> list, PreparedStatement ps, int count, int position){
		int size = list.size();
		int empty = count - size;
		try{
			for(int i=0; i<size; i++){
				ps.setString(i+position, list.get(i));
			}
			if(empty>0){
				for(int i=0; i<empty; i++){
					ps.setString(i+size+position, null);
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

//	dish_idをDBから引っ張ってくるメソッド
	public String getDishId(RecipeDTO recipe){
		String ret = null;
		String sql = "SELECT id FROM dish_info WHERE author = ? AND dish_name = ?";
		String userId = recipe.getAuthor();
		String dishName = recipe.getDishName();
		Connection conn = null;
		try{
			DBConnector db = new DBConnector();
			conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, dishName);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				ret = rs.getString("id");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return ret;
	}

//	admin権限で全てのレシピを持ってくるメソッド
	public List<RecipeDTO> getAllRecipe(){
		List<RecipeDTO> recipeList = new ArrayList<RecipeDTO>();
		String sql = "SELECT id, author, dish_name, dish_info, ing, "
				+ "proc_1, proc_2, proc_3, proc_4, proc_5, proc_6, proc_7, proc_8, proc_9, proc_10, "
				+ "cooking_time, image_file_path, image_file_name "
				+ "FROM dish_info ORDER BY updated_date DESC";
		Connection conn = null;
		try{
			DBConnector db = new DBConnector();
			conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				RecipeDTO recipe = new RecipeDTO();
				TagDAO tagDAO = new TagDAO();
				List<String> tagList = new ArrayList<String>();
				List<List<String>> ingAmountList = new ArrayList<List<String>>();
				List<String> procList = new ArrayList<String>();
				recipe.setDishId(String.valueOf(rs.getInt("id")));
				recipe.setAuthor(rs.getString("author"));
				recipe.setDishName(rs.getString("dish_name"));
				recipe.setDishInfo(rs.getString("dish_info"));
				tagList = tagDAO.getTagListByDB(rs.getString("id"));
				recipe.setTagList(tagList);
				String ingAmount = rs.getString("ing");
				if(ingAmount!=null){
					List<String> tempList = new ArrayList<String>();
					tempList = Arrays.asList(ingAmount.split("[\\s]*,[\\s]*")); // (材料/分量)のStringが詰まったリスト
					for(String ingSet:tempList){
						List<String> ingSetList = new ArrayList<String>();
						ingSetList = Arrays.asList(ingSet.split("[\\s]*/[\\s]*"));
						ingAmountList.add(ingSetList);
					}
				}
				recipe.setIngAmountList(ingAmountList);
				for(int i=1;i<=10;i++){
					String p = rs.getString("proc_"+i);
					if(p!=null){
						procList.add(p);
					}
				}
				recipe.setProcList(procList);
				recipe.setCookingTime(rs.getInt("cooking_time"));
				recipe.setImageFilePath(rs.getString("image_file_path"));
				recipe.setImageFileName(rs.getString("image_file_name"));
				recipeList.add(recipe);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return recipeList;
	}

//	公開される全てのレシピを持ってくるメソッド
	public List<RecipeDTO> getPublicRecipe(){
		List<RecipeDTO> recipeList = new ArrayList<RecipeDTO>();
		String sql = "SELECT id, author, dish_name, dish_info, ing, "
				+ "proc_1, proc_2, proc_3, proc_4, proc_5, proc_6, proc_7, proc_8, proc_9, proc_10, "
				+ "cooking_time, image_file_path, image_file_name "
				+ "FROM dish_info WHERE publicity=1 ORDER BY updated_date DESC";
		Connection conn = null;
		try{
			DBConnector db = new DBConnector();
			conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				RecipeDTO recipe = new RecipeDTO();
				TagDAO tagDAO = new TagDAO();
				List<String> tagList = new ArrayList<String>();
				List<List<String>> ingAmountList = new ArrayList<List<String>>();
				List<String> procList = new ArrayList<String>();
				recipe.setDishId(String.valueOf(rs.getInt("id")));
				recipe.setAuthor(rs.getString("author"));
				recipe.setDishName(rs.getString("dish_name"));
				recipe.setDishInfo(rs.getString("dish_info"));
				tagList = tagDAO.getTagListByDB(rs.getString("id"));
				recipe.setTagList(tagList);
				String ingAmount = rs.getString("ing");
				if(ingAmount!=null){
					List<String> tempList = new ArrayList<String>();
					tempList = Arrays.asList(ingAmount.split("[\\s]*,[\\s]*")); // (材料/分量)のStringが詰まったリスト
					for(String ingSet:tempList){
						List<String> ingSetList = new ArrayList<String>();
						ingSetList = Arrays.asList(ingSet.split("[\\s]*/[\\s]*"));
						ingAmountList.add(ingSetList);
					}
				}
				recipe.setIngAmountList(ingAmountList);
				for(int i=1;i<=10;i++){
					String p = rs.getString("proc_"+i);
					if(p!=null){
						procList.add(p);
					}
				}
				recipe.setProcList(procList);
				recipe.setCookingTime(rs.getInt("cooking_time"));
				recipe.setImageFilePath(rs.getString("image_file_path"));
				recipe.setImageFileName(rs.getString("image_file_name"));
				recipeList.add(recipe);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return recipeList;
	}

//	userIdから全てのレシピを持ってくるメソッド
	public List<RecipeDTO> getUsersRecipe(String userId){
		List<RecipeDTO> recipeList = new ArrayList<RecipeDTO>();
		String sql = "SELECT id, dish_name, dish_info, ing, "
				+ "proc_1, proc_2, proc_3, proc_4, proc_5, proc_6, proc_7, proc_8, proc_9, proc_10, "
				+ "cooking_time, image_file_path, image_file_name "
				+ "FROM dish_info WHERE author = ? ORDER BY updated_date DESC";
		Connection conn = null;
		try{
			DBConnector db = new DBConnector();
			conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				RecipeDTO recipe = new RecipeDTO();
				TagDAO tagDAO = new TagDAO();
				List<String> tagList = new ArrayList<String>();
				List<List<String>> ingAmountList = new ArrayList<List<String>>();
				List<String> procList = new ArrayList<String>();
				recipe.setDishId(String.valueOf(rs.getInt("id")));
				recipe.setDishName(rs.getString("dish_name"));
				recipe.setDishInfo(rs.getString("dish_info"));
				tagList = tagDAO.getTagListByDB(rs.getString("id"));
				recipe.setTagList(tagList);
				String ingAmount = rs.getString("ing");
				if(ingAmount!=null){
					List<String> tempList = new ArrayList<String>();
					tempList = Arrays.asList(ingAmount.split("[\\s]*,[\\s]*")); // (材料/分量)のStringが詰まったリスト
					for(String ingSet:tempList){
						List<String> ingSetList = new ArrayList<String>();
						ingSetList = Arrays.asList(ingSet.split("[\\s]*/[\\s]*"));
						ingAmountList.add(ingSetList);
					}
				}
				recipe.setIngAmountList(ingAmountList);
				for(int i=1;i<=10;i++){
					String p = rs.getString("proc_"+i);
					if(p!=null){
						procList.add(p);
					}
				}
				recipe.setProcList(procList);
				recipe.setCookingTime(rs.getInt("cooking_time"));
				recipe.setImageFilePath(rs.getString("image_file_path"));
				recipe.setImageFileName(rs.getString("image_file_name"));
				recipeList.add(recipe);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return recipeList;
	}

//	RecipeDetailActionでターゲットのレシピ情報をもってくるメソッド
	public RecipeDTO getRecipeInfo(String dishId){
		RecipeDTO recipe = new RecipeDTO();
		List<String> tagList = new ArrayList<String>();
		List<List<String>> ingAmountList = new ArrayList<List<String>>();
		List<String> procList = new ArrayList<String>();
		String sql = "SELECT author, dish_name, dish_info, ing, "
				+ "proc_1, proc_2, proc_3, proc_4, proc_5, proc_6, proc_7, proc_8, proc_9, proc_10, "
				+ "cooking_time, image_file_path, image_file_name "
				+ "FROM dish_info WHERE id=?";
		Connection conn = null;
		try{
			DBConnector db = new DBConnector();
			conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, dishId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				recipe.setAuthor(rs.getString("author"));
				recipe.setDishName(rs.getString("dish_name"));
				recipe.setDishInfo(rs.getString("dish_info"));
				TagDAO tagDAO = new TagDAO();
				tagList = tagDAO.getTagListByDB(dishId);
				recipe.setTagList(tagList);
				String ingAmount = rs.getString("ing");
				if(ingAmount!=null){
					List<String> tempList = new ArrayList<String>();
					tempList = Arrays.asList(ingAmount.split("[\\s]*,[\\s]*")); // (材料/分量)のStringが詰まったリスト
					for(String ingSet:tempList){
						List<String> ingSetList = new ArrayList<String>();
						ingSetList = Arrays.asList(ingSet.split("[\\s]*/[\\s]*"));
						ingAmountList.add(ingSetList);
					}
				}
				recipe.setIngAmountList(ingAmountList);
				for(int i=1;i<=10;i++){
					String p = rs.getString("proc_"+i);
					if(p!=null){
						procList.add(p);
					}
				}
				recipe.setProcList(procList);
				recipe.setCookingTime(rs.getInt("cooking_time"));
				recipe.setImageFilePath(rs.getString("image_file_path"));
				recipe.setImageFileName(rs.getString("image_file_name"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return recipe;
	}
}
