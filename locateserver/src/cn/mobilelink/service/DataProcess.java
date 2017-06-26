package cn.mobilelink.service;

import java.util.List;

import cn.mobilelink.entity.AP;
import cn.mobilelink.utils.LocateMathUtils;

/**
 * ����Ԥ�����㷨��Ŀǰ��3����׼���޳�֮���þ�ֵ����
 * @author Cassie
 *
 */
public class DataProcess {
	
	/**
	 * 3*sigma�޳��쳣ɨ����
	 * @param apList
	 * @return
	 */
	public List<AP> stdDevRemove(List<AP> apList){
		if(apList.size()<=1) return apList;
		double rssDtdDev = 3*LocateMathUtils.stdDeviation(apList);	//3����׼��
		double rssAvg = LocateMathUtils.avg(apList);
		for(int i = 0; i < apList.size(); i++){
			double delta = Math.abs(apList.get(i).getRss() - rssAvg);
			if(delta > rssDtdDev){
				apList.remove(i);
			}
		}
		return apList;
	}
}
