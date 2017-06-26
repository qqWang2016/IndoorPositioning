package cn.mobilelink.dao;

import org.junit.Test;

import cn.mobilelink.utils.JDBCUtils;

public class LocateDaoFactory {
	
	public static LocateDao getLocateDao(){

		String daoClassName = JDBCUtils.getProperty("cn.mobilelink.dao.LocateDaoInterface");
		Class classz;
		try {
			classz = Class.forName(daoClassName);
			return (LocateDao) classz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
