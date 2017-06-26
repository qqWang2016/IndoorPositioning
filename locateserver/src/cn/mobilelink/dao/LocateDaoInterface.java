package cn.mobilelink.dao;

import java.util.List;

import cn.mobilelink.entity.AP;
/**
 * ʹ������ӿڱ�̣���ҵ������ӿڶ�����ĳһʵ����
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
