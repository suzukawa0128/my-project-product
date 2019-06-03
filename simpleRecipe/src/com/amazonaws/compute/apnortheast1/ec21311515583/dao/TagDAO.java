package com.amazonaws.compute.apnortheast1.ec21311515583.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.compute.apnortheast1.ec21311515583.dto.RecipeDTO;
import com.amazonaws.compute.apnortheast1.ec21311515583.util.DBConnector;


public class TagDAO {

//	recipeを引数としてtag_infoとtag_mapにまとめてinsertするメソッド
	public boolean insertRecipesTagInfo(RecipeDTO recipe){
		boolean ret = false;
		List<String> tagList = recipe.getTagList();
		RecipeDAO recipeDAO = new RecipeDAO();
		String dishId = recipeDAO.getDishId(recipe);
		int r = 0;
		if(tagList!=null){
			for(String tagName:tagList){
				boolean res = false;
				res = insertTagInfo(tagName, dishId);
				if(res){
					r += 1;
				}
			}
			if(r==tagList.size()){
				ret = true;
			}
		}else{
			ret = true;
		}
		return ret;
	}

//	tag_infoとtag_mapをupdateするメソッド
	public boolean updateTagInfo(RecipeDTO recipe, String dishId){
		boolean ret = false;
		boolean res = false;
		List<String> newTagList = recipe.getTagList();
		List<String> tagListByDB = getTagListByDB(dishId);
//			タグを全て削除した場合
			if(newTagList==null && tagListByDB!=null){
				int r = 0;
				for(String tagNameByDB:tagListByDB){
					res = deleteTagMap(tagNameByDB, dishId);
					if(res){
						r += 1;
					}
				}
				if(r==tagListByDB.size()){
					ret = true;
				}
//			タグを登録していないレシピに新たにタグを登録した場合
			}else if(newTagList!=null && tagListByDB==null){
				res = insertRecipesTagInfo(recipe);
				if(res){
					ret = true;
				}
//			タグを登録してあるレシピのタグを編集した場合
			}else if(newTagList!=null && tagListByDB!=null){
				for(String newTagName:newTagList){
					if(!(tagListByDB.contains(newTagName))){
						insertTagInfo(newTagName, dishId);
					}
				}
				for(String tagNameByDB:tagListByDB){
					if(!(newTagList.contains(tagNameByDB))){
						deleteTagMap(tagNameByDB, dishId);
					}
				}
				ret = true;
			}
		return ret;
	}

//	tagNameとdishIdを引数としてtag_mapに一つだけレコードをinsertするメソッド
	public boolean insertTagInfo(String tagName, String dishId){
		boolean ret = false;
		String tagInfoSql = "INSERT INTO tag_info(tag_name) VALUES(?)";
		String tagMapSql = "INSERT INTO tag_map(dish_id, tag_id) VALUES(?,?)";
		Connection conn = null;
		try{
			DBConnector db = new DBConnector();
			conn = db.getConnection();
			PreparedStatement tagInfoPs = conn.prepareStatement(tagInfoSql);
			String tagId;
//			tagListの要素に対し、タグが存在しないならばinsert文を実行
			boolean tagExists = existsTag(tagName);
			if(!tagExists){
				tagInfoPs.setString(1, tagName);
				tagInfoPs.execute();
			}
			tagId = getTagId(tagName);
			PreparedStatement tagMapPs = conn.prepareStatement(tagMapSql);
			tagMapPs.setInt(1, Integer.parseInt(dishId));
			tagMapPs.setInt(2, Integer.parseInt(tagId));
			int r = tagMapPs.executeUpdate();
			if(r==1){
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

//	tag_mapのレコードを一つ削除するメソッド
	public boolean deleteTagMap(String tagName, String dishId){
		boolean ret = false;
		String sql = "DELETE FROM tag_map WHERE dish_id=? AND tag_id=?";
		Connection conn = null;
		try{
			DBConnector db = new DBConnector();
			conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			String tagId = getTagId(tagName);
			ps.setInt(1, Integer.parseInt(dishId));
			ps.setInt(2, Integer.parseInt(tagId));
			ret = ps.execute();
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

//	dishIdからtag_mapのレコードを削除するメソッド
	public boolean deleteRecipesTagMap(String dishId){
		boolean ret = false;
		String sql = "DELETE FROM tag_map WHERE dish_id=?";
		Connection conn = null;
		try{
			DBConnector db = new DBConnector();
			conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(dishId));
			ret = ps.execute();
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

//	tag_infoに既にタグがあるか調べるメソッド
	public boolean existsTag(String tagName){
		boolean ret = false;
		String sql = "SELECT tag_name FROM tag_info WHERE tag_name = ?";
		Connection conn = null;
		try{
			DBConnector db = new DBConnector();
			conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, tagName);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
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

//	tag_mapに既に登録されているか調べるメソッド
	public boolean existsTagMap(String dishId){
		boolean ret = false;
		String sql = "SELECT * FROM tag_map	WHERE dish_id=?";
		Connection conn = null;
		try{
			DBConnector db = new DBConnector();
			conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, dishId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
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

//	tag_infoからtag_idをもってくるメソッド
	public String getTagId(String tagName){
		String ret = null;
		String sql = "SELECT id FROM tag_info WHERE tag_name = ?";
		Connection conn = null;
		try{
			DBConnector db = new DBConnector();
			conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, tagName);
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

//	tagListを作るメソッド
	public List<String> getTagListByDB(String dishId){
		List<String> tagList = new ArrayList<String>();
		String sql = "SELECT ti.tag_name FROM tag_info ti INNER JOIN tag_map tm ON ti.id = tm.tag_id WHERE tm.dish_id = ?";
		Connection conn = null;
		try{
			DBConnector db = new DBConnector();
			conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, dishId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				tagList.add(rs.getString("ti.tag_name"));
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
		return tagList;
	}
}
