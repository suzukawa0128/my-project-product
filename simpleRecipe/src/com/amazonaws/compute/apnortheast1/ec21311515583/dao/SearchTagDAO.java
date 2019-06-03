package com.amazonaws.compute.apnortheast1.ec21311515583.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.compute.apnortheast1.ec21311515583.dto.RecipeDTO;
import com.amazonaws.compute.apnortheast1.ec21311515583.util.DBConnector;

public class SearchTagDAO {

//	publicRecipeからタグ検索した時にrecipeListを返すメソッド
	public List<RecipeDTO> searchTagFromPublic(String tagId){
		List<RecipeDTO> recipeList = new ArrayList<RecipeDTO>();
		String sql = "SELECT di.id, di.author, di.dish_name, di.dish_info, di.image_file_path, di.image_file_name "
				+ "FROM dish_info di INNER JOIN tag_map tm ON di.id = tm.dish_id WHERE di.publicity=true AND tm.tag_id=? "
				+ "ORDER BY updated_date DESC";
		Connection conn = null;
		try{
			DBConnector db = new DBConnector();
			conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, tagId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				RecipeDTO recipe = new RecipeDTO();
				recipe.setDishId(rs.getString("di.id"));
				recipe.setAuthor(rs.getString("di.author"));
				recipe.setDishName(rs.getString("di.dish_name"));
				recipe.setDishInfo(rs.getString("di.dish_info"));
				recipe.setImageFilePath(rs.getString("di.image_file_path"));
				recipe.setImageFileName(rs.getString("di.image_file_name"));
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

//	myPageからタグ検索した時にrecipeListを返すメソッド
	public List<RecipeDTO> searchTagFromMyRecipe(String tagId, String userId){
		List<RecipeDTO> recipeList = new ArrayList<RecipeDTO>();
		String sql = "SELECT di.id, di.author, di.dish_name, di.dish_info, di.image_file_path, di.image_file_name "
				+ "FROM dish_info di INNER JOIN tag_map tm ON di.id = tm.dish_id WHERE di.author=? AND tm.tag_id=? "
				+ "ORDER BY updated_date DESC";
		Connection conn = null;
		try{
			DBConnector db = new DBConnector();
			conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, tagId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				RecipeDTO recipe = new RecipeDTO();
				recipe.setDishId(rs.getString("di.id"));
				recipe.setAuthor(rs.getString("di.author"));
				recipe.setDishName(rs.getString("di.dish_name"));
				recipe.setDishInfo(rs.getString("di.dish_info"));
				recipe.setImageFilePath(rs.getString("di.image_file_path"));
				recipe.setImageFileName(rs.getString("di.image_file_name"));
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
}
