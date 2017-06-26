package cn.mobilelink.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Utils {

	private static ComboPooledDataSource cpds = new ComboPooledDataSource();
	private Connection conn;
	{
		try {
			conn = cpds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * 返回mysql-Connection
	 */
	public static Connection getConnection() throws SQLException{
		Connection conn = cpds.getConnection();
		return conn;
	}

	/*
	 * 返回连接池
	 */
	public static DataSource getDataSource(){
		return cpds;
	}

}
