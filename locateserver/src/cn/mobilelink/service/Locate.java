package cn.mobilelink.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.mobilelink.dao.LocateDao;
import cn.mobilelink.dao.LocateDaoFactory;
import cn.mobilelink.entity.AP;
import cn.mobilelink.entity.ClientAp;
import cn.mobilelink.entity.Location;
import cn.mobilelink.utils.C3P0Utils;
import cn.mobilelink.utils.JDBCUtils;
import cn.mobilelink.utils.LocateMathUtils;

/**
 * ��Ŀ�Ķ�λ�㷨��Ŀǰ����KNN
 * @author Cassie
 *
 */
public class Locate {
	
	private LocateDao ld ;
	private static final String tabName = JDBCUtils.getProperty("sqlTabName");//Ҫ���ӵ����ݿ������
	public Locate(LocateDao ld){
		this.ld = ld;
	}
	/**
	 * knn�㷨
	 * @param clientApList
	 * @param k
	 */
	public String knn(List<ClientAp> clientApList, int k){
	
	} 
	
	

}
