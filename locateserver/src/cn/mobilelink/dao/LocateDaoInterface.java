package cn.mobilelink.dao;

import java.util.List;

import cn.mobilelink.entity.AP;
/**
 * 使用面向接口编程，是业务面向接口而不是某一实现类
 * @author Cassie
 *
 */
public interface LocateDaoInterface {
	public void insert(AP ap,String tabName);
	public void delete(String bssid, double x, double y,String tabName);
	public void update(double rss,String bssid, double x, double y,String tabName);
	public List<AP> findByApInfo(String bssid, String area,String tabName);
	public int queryTotalRecords(String tabName);
	
}
