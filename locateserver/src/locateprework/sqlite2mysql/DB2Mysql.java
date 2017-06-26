package locateprework.sqlite2mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import cn.mobilelink.utils.C3P0Utils;
import cn.mobilelink.utils.JDBCUtils;
/**
 * ʵ�����ݴ�sqlite���ļ����ݿ�ת����mysql
 * @author Cassie
 *
 */
public class DB2Mysql {

	@Test
	/**
	 * �������������ʹ�ã������ݴ�sqlite���ļ����ݿ�ת����mysql
	 */
	public void DB2Mysql() {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection sqlConn = null;
		PreparedStatement pstmt = null;
		long start = System.currentTimeMillis();
		try {
			// sqlite��������properties�ļ��У��Լ���װ��JDBCUtils���ȡ
			Class.forName(JDBCUtils.getProperty("driverClassName")); // ��̬���������������������,ȷ�����౻���ز�������
			conn = DriverManager.getConnection(JDBCUtils
					.getProperty("connectionName"));
			stmt = conn.prepareStatement("select * from wifidatabase");
			rs = stmt.executeQuery();
			// mysql,ͨ��c3p0���ӳ�����
			sqlConn = C3P0Utils.getConnection();
			sqlConn.setAutoCommit(false);// ���Զ��ύ�ر�
			String sql = "INSERT INTO ap_info(bssid,ssid,rss,area,x,y,time) VALUES(?,?,?,?,?,?,?)";
		    pstmt = sqlConn.prepareStatement(sql);

			while (rs.next()) {
				pstmt.setString(1, rs.getString("BSSID"));
				pstmt.setString(2, rs.getString("SSID"));
				pstmt.setInt(3, Integer.parseInt(rs.getString("level")));//��ʵ���Զ�ת��,�����ٶȲ�ֵ�ɺ��Բ���
				pstmt.setString(4, rs.getString("area"));
				pstmt.setString(5, rs.getString("x"));   //���ݿ�洢����int���͵�Ϊ���쳣��
				pstmt.setString(6, rs.getString("y"));
				pstmt.setString(7, rs.getString("time"));
				pstmt.addBatch();
			}
			
			pstmt.executeBatch();// ִ��������
			sqlConn.commit();// ִ������ֶ��ύ����
			sqlConn.setAutoCommit(true);// �ڰ��Զ��ύ��

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
