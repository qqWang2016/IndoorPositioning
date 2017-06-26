package cn.mobilelink.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import cn.mobilelink.entity.AP;
import cn.mobilelink.entity.ClientAp;

public class LocateMathUtils {

	// ��ֵ������
	public static double avg(List<AP> apList) {
		return sum(apList) / apList.size();
	}

	// ��ͺ���
	public static double sum(List<AP> apList) {
		double sumRss = 0.0;
		for (int i = 0; i < apList.size(); i++) {
			AP ap = apList.get(i);
			sumRss += ap.getRss();
		}
		return sumRss;
	}
	//����
	public static double var(List<AP> apList){
		if(apList.size() <= 1)  return 0.0;
		double var = 0.0;
		double avgRss = avg(apList);
		for(int i = 0; i < apList.size(); i++){
			AP ap = apList.get(i);
			var += Math.pow(ap.getRss() - avgRss , 2);
		}
		return var/apList.size();
	}
	//��׼��
	public static double stdDeviation(List<AP> apList){
		return Math.sqrt(var(apList));
	}
	//��rss���ֵƽ��
	public static double getDistance(double rss1, double rss2){
		return Math.pow((rss1-rss2), 2);
	}
	
	//��������
	public static void sort(List<Map<String, Double>> List)  {
    	Collections.sort(List, new Comparator<Map>() {
			public int compare(Map o1, Map o2) {
				Double a = (Double) o1.get("delta");
				Double b = (Double) o2.get("delta");
				return a.compareTo(b); 
			}
		});
    }
	//�����㷨����������rss��������
		public static void sortByRss(List<ClientAp> List)  {

	    	Collections.sort(List, new Comparator<ClientAp>() {
				public int compare(ClientAp clientAp1, ClientAp clientAp2) {
					Double a = clientAp1.getRss();
					Double b = clientAp2.getRss();
					return b.compareTo(a); 
				}
			});
	    }
}
