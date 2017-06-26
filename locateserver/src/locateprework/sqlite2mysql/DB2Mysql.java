package locateprework.sqlite2mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import cn.mobilelink.utils.C3P0Utils;
import cn.mobilelink.utils.JDBCUtils;
/**
 * 实现数据从sqlite单文件数据库转存入mysql
 * @author Cassie
 *
 */
public class DB2Mysql {

	@Test
	/**
	 * 事务和批处理混合使用，将数据从sqlite单文件数据库转存入mysql
	 */
	public void DB2Mysql() {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection sqlConn = null;
		PreparedStatement pstmt = null;
		long start = System.currentTimeMillis();
		try {
			// sqlite，配置在properties文件中，自己封装了JDBCUtils类获取
			Class.forName(JDBCUtils.getProperty("driverClassName")); // 静态方法调用了启动类加载器,确保该类被加载并已连接
			conn = DriverManager.getConnection(JDBCUtils
					.getProperty("connectionName"));
			stmt = conn.prepareStatement("select * from wifidatabase");
			rs = stmt.executeQuery();
			// mysql,通过c3p0连接池配置
			sqlConn = C3P0Utils.getConnection();
			sqlConn.setAutoCommit(false);// 将自动提交关闭
			String sql = "INSERT INTO ap_info(bssid,ssid,rss,area,x,y,time) VALUES(?,?,?,?,?,?,?)";
		    pstmt = sqlConn.prepareStatement(sql);

			while (rs.next()) {
				pstmt.setString(1, rs.getString("BSSID"));
				pstmt.setString(2, rs.getString("SSID"));
				pstmt.setInt(3, Integer.parseInt(rs.getString("level")));//其实会自动转换,两者速度差值可忽略不计
				pstmt.setString(4, rs.getString("area"));
				pstmt.setString(5, rs.getString("x"));   //数据库存储的是int类型但为抛异常，
				pstmt.setString(6, rs.getString("y"));
				pstmt.setString(7, rs.getString("time"));
				pstmt.addBatch();
			}
			
			pstmt.executeBatch();// 执行批处理！
			sqlConn.commit();// 执行完后，手动提交事务
			sqlConn.setAutoCommit(true);// 在把自动提交打开

			sqlConn.close();
			conn.close();
			long end = System.currentTimeMillis();
			
			System.out.println(end - start);//412764, 301
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.toString());
		}
	}
}
