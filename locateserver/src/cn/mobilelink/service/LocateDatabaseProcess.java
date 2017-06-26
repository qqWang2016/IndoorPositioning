package cn.mobilelink.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.mobilelink.dao.LocateDao;
import cn.mobilelink.entity.AP;
import cn.mobilelink.utils.C3P0Utils;
import cn.mobilelink.utils.JDBCUtils;
import cn.mobilelink.utils.LocateMathUtils;

public class LocateDatabaseProcess {

	private LocateDao ld = new LocateDao();
	
	@Test
	public void testDao(){
		System.out.println(ld.toString());
	}
	@Test
	/**
	 * 经过3*sigma剔除并使用均值滤波的数据库
	 */
	public void TablocateBuild(){
		Connection sqlConn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AP ap = new AP();
		List<AP> apList = new ArrayList<AP>();
		try {
			sqlConn = C3P0Utils.getConnection();
			rs = sqlConn.prepareStatement("SELECT count(*) AS tableCount FROM ap_info").
					executeQuery();
			rs.next();
			int tableCount = rs.getInt("tableCount");
			System.out.println(tableCount);
			String sql = "INSERT INTO ap_info_sm(bssid,ssid,rss,area,x,y,time) VALUES(?,?,?,?,?,?,?)";
			pstmt = sqlConn.prepareStatement(sql);
			for(int i = 0; i < tableCount; i += 300){
				for(int j = 1; j < 11; j++){
					ap = ld.findByApInfo(i+j,JDBCUtils.getProperty("originalTab"));
					apList = ld.findByApInfo(ap.getSsid(), ap.getArea(),JDBCUtils.getProperty("originalTab"));    //选出ssid和area相同的ap
					new DataProcess().stdDevRemove(apList);   //3倍sigma剔除异常值
					pstmt.setString(1, ap.getBssid());
					pstmt.setString(2, ap.getSsid());
					pstmt.setDouble(3, LocateMathUtils.avg(apList));      //将剔除异常值后rss均值存入数据库
					pstmt.setString(4, ap.getArea());
					pstmt.setDouble(5, ap.getX());
					pstmt.setDouble(6, ap.getY());
					pstmt.setString(7, ap.getTime());
					pstmt.addBatch();
					pstmt.executeBatch();// 执行批处理！
			
				}
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	@Test
	public void TabLocateWithout3Sigma(){
		
		Connection sqlConn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AP ap = new AP();
		List<AP> apList = new ArrayList<AP>();
		
		try {
			sqlConn = C3P0Utils.getConnection();
			rs = sqlConn.prepareStatement("SELECT count(*) AS tableCount FROM ap_info").
					executeQuery();
			rs.next();
			int tableCount = rs.getInt("tableCount");
			System.out.println(tableCount);
			
			String sql = "INSERT INTO ap_info_m(bssid,ssid,rss,area,x,y,time) VALUES(?,?,?,?,?,?,?)";
			pstmt = sqlConn.prepareStatement(sql);
			for(int i = 0; i < tableCount; i += 300){
				for(int j = 1; j < 11; j++){
					ap = ld.findByApInfo(i+j,JDBCUtils.getProperty("originalTab"));
					apList = ld.findByApInfo(ap.getSsid(), ap.getArea(),JDBCUtils.getProperty("originalTab"));    //选出ssid和area相同的ap
					pstmt.setString(1, ap.getBssid());
					pstmt.setString(2, ap.getSsid());
					pstmt.setDouble(3, LocateMathUtils.avg(apList));      //将剔除异常值后rss均值存入数据库
					pstmt.setString(4, ap.getArea());
					pstmt.setDouble(5, ap.getX());
					pstmt.setDouble(6, ap.getY());
					pstmt.setString(7, ap.getTime());
					pstmt.addBatch();
					pstmt.executeBatch();// 执行批处理！
				}
			}
			pstmt.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	@Test
	/**
	 * 从Locate_data中选出每个区域强度最强的三个值存储进ToBeLocate数据库
	 */
	public void TabForLocate(){
		
		Connection sqlConn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtTmp = null;
		ResultSet rs = null;
		
		try {		
			sqlConn = C3P0Utils.getConnection();
			rs = sqlConn.prepareStatement("SELECT count(*) AS tableLen FROM ap_info_3sigma_mean").executeQuery();
			rs.next();
			int tableLen = rs.getInt("tableLen");

			String sqlTmp = "SELECT * FROM ap_info_3sigma_mean ORDER BY area, rss LIMIT ?,1";
			pstmtTmp = sqlConn.prepareStatement(sqlTmp);
			
			String sql = "INSERT INTO test(bssid,ssid,rss,area,x,y,time) VALUES(?,?,?,?,?,?,?)";
			pstmt = sqlConn.prepareStatement(sql);
			
			for(int i = 7; i < tableLen; i += 10){
				for(int j = 0; j < 3; j++){
					pstmtTmp.setInt(1, i+j);
					rs =pstmtTmp.executeQuery();
					pstmtTmp.addBatch();
					rs.next();
					pstmt.setString(1, rs.getString("bssid"));
					pstmt.setString(2, rs.getString("ssid"));
					pstmt.setDouble(3, rs.getDouble("rss"));      //将剔除异常值后rss均值存入数据库
					pstmt.setString(4, rs.getString("area"));
					pstmt.setDouble(5, rs.getInt("x"));
					pstmt.setDouble(6, rs.getInt("y"));
					pstmt.setString(7, rs.getString("time"));
					pstmt.addBatch();
					pstmt.executeBatch();// 执行批处理！
				}
			}
			rs.close();
			pstmtTmp.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
