package cn.mobilelink.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.mobilelink.entity.AP;
import cn.mobilelink.utils.C3P0Utils;

@Repository
public class LocateDao implements LocateDaoInterface{
	
	public LocateDao(){
	}
	
	@Override
	public void insert(AP ap, String tabName) {
		Connection conn = null;
		String sql = "INSERT INTO" +tabName+"(bssid,ssid,rss,area,x,y,time) VALUES(?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			conn = C3P0Utils.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ap.getBssid());
			pstmt.setString(2, ap.getSsid());
			pstmt.setDouble(3, ap.getRss());
			pstmt.setString(4, ap.getArea());
			pstmt.setDouble(5, ap.getX());
			pstmt.setDouble(6, ap.getY());
			pstmt.setString(7, ap.getTime());
			pstmt.addBatch();
			pstmt.executeBatch();// 执行批处理！
			pstmt.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	@Override
	public void delete(String bssid, double x, double y,String tabName) {
		Connection conn = null;
		String sql = "DELETE FROM"+ tabName +"WHERE bssid=? AND x = ? AND y = ?";
		PreparedStatement pstmt = null;
		try {
			conn = C3P0Utils.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bssid);
			pstmt.setDouble(2, x);
			pstmt.setDouble(3, y);
			pstmt.addBatch();
			pstmt.executeBatch();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	@Override
	public void update(double rss, String bssid, double x, double y, String tabName) {
		Connection conn = null;
		String sql = "UPDATE"+ tabName +"SET rss=? WHERE bssid=? AND x = ? AND y = ?";
		PreparedStatement pstmt = null;
		try {
			conn = C3P0Utils.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, rss);
			pstmt.setString(2, bssid);
			pstmt.setDouble(3, x);
			pstmt.setDouble(4, y);
			pstmt.addBatch();
			pstmt.executeBatch();
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}
	@Override
	public List<AP> findByApInfo(String bssid, String area, String tabName) {
		Connection conn = null;
		String sql = "SELECT * FROM "+ tabName +" WHERE ssid=? AND area = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AP> apList = new ArrayList<AP>();
		try {
			conn = C3P0Utils.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bssid);
			pstmt.setString(2, area);
			pstmt.addBatch();
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				AP ap = new AP();
				ap.setBssid(rs.getString("bssid"));
				ap.setArea(rs.getString("area"));
				ap.setSsid(rs.getString("ssid"));
				ap.setTime(rs.getString("time"));
				ap.setRss(Integer.parseInt(rs.getString("rss")));
				ap.setX(rs.getInt("x"));
				ap.setY(rs.getInt("y"));
				apList.add(ap);
			}
			return apList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int queryTotalRecords(String tabName) {
		Connection conn = null;
		String sql = "SELECT COUNT(*) AS totalRecords FROM "+ tabName ;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = C3P0Utils.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt("totalRecords");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}

	public AP findByApInfo(int recordsNO, String tabName) {
		Connection conn = null;
		String sql = "SELECT * FROM " + tabName + " WHERE recordsNO=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = C3P0Utils.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recordsNO);
			pstmt.addBatch();
			rs = pstmt.executeQuery();
			rs.next();
			AP ap = new AP();
			ap.setBssid(rs.getString("bssid"));
			ap.setArea(rs.getString("area"));
			ap.setSsid(rs.getString("ssid"));
			ap.setTime(rs.getString("time"));
			ap.setRss(Integer.parseInt(rs.getString("rss")));
			ap.setX(rs.getInt("x"));
			ap.setY(rs.getInt("y"));
			return ap;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

}
