package com.amazonaws.compute.apnortheast1.ec21311515583.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

	private final String driverName = "com.mysql.jdbc.Driver";
	private final String url = "jdbc:mysql://localhost/myrecipestruts";
	private final String user = "root";
	private final String password = "mysql";

	public Connection getConnection(){
		Connection conn = null;

		try{
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, user, password);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}

		return conn;
	}
}
