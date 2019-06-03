package com.amazonaws.compute.apnortheast1.ec21311515583.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.amazonaws.compute.apnortheast1.ec21311515583.dto.TempUserDTO;
import com.amazonaws.compute.apnortheast1.ec21311515583.util.DBConnector;

public class UserInfoDAO {

//	ログイン時にmailとpassを照合するメソッド
	public boolean UserLogin(String mail, String pass){
		String sql = "SELECT mail, pass FROM user_info WHERE mail = ? AND pass = ?";
		boolean ret = false;
		Connection conn = null;
		try{
			DBConnector db = new DBConnector();
			conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, mail);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				ret = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return ret;
	}

//	mailIdからuserIdを持ってくるメソッド
	public String getUserIdFromDB(String mailId){
		String sql = "SELECT user_id FROM user_info WHERE mail=?";
		String userId = null;
		Connection conn = null;
		try{
			DBConnector db = new DBConnector();
			conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, mailId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				userId = rs.getString("user_id");
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
		return userId;
	}

//	ユーザーIDが既に登録されていないか調べるメソッド
	public boolean TempUserIdCheck(TempUserDTO tempUser){
		String sql = "SELECT user_id FROM user_info WHERE user_id = ?";
		Connection conn = null;
		boolean result = true;
		try{
			DBConnector db = new DBConnector();
			conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, tempUser.getUserId());
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				result = false;
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return result;
	}

//	メールアドレスが既に登録されていないか調べるメソッド
	public boolean TempUserMailCheck(TempUserDTO tempUser){
		String sql = "SELECT mail FROM user_info WHERE mail = ?";
		Connection conn = null;
		boolean result = true;
		try{
			DBConnector db = new DBConnector();
			conn = db.getConnection();

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, tempUser.getMail());
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				result = false;
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return result;
	}

//	adminアカウントかどうか調べるメソッド
	public boolean adminCheck(String mail, String pass){
		boolean result = false;
		String sql = "SELECT adminFlg FROM user_info WHERE mail=? AND pass=?";
		Connection conn = null;
		try{
			DBConnector db = new DBConnector();
			conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, mail);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();

			if(rs.next() && rs.getBoolean("adminFlg")){
				result = true;
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return result;
	}

//	user_infoテーブルにユーザーを追加するメソッド
	public boolean CreateUser(TempUserDTO tempUser){
		String sql= "INSERT INTO user_info (user_id,mail,pass) VALUES(?,?,?)";
		Connection conn = null;
		int result = 0;
		boolean ret = true;

		try{
			DBConnector db = new DBConnector();
			conn = db.getConnection();

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, tempUser.getUserId());
			ps.setString(2, tempUser.getMail());
			ps.setString(3, tempUser.getPass());
			result = ps.executeUpdate();

			if(result != 1){
				ret = false;
			}
		}catch(Exception e){
			ret = false;
			e.printStackTrace();
		}finally{
			try{
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return ret;
	}
}
