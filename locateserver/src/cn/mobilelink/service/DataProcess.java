package cn.mobilelink.service;

import java.util.List;

import cn.mobilelink.entity.AP;
import cn.mobilelink.utils.LocateMathUtils;

/**
 * 数据预处理算法，目前是3倍标准差剔除之后用均值处理
 * @author Cassie
 *
 */
public class DataProcess {
	
	/**
	 * 3*sigma剔除异常扫描结果
	 * @param apList
	 * @return
	 */
	public List<AP> stdDevRemove(List<AP> apList){
		if(apList.size()<=1) return apList;
		double rssDtdDev = 3*LocateMathUtils.stdDeviation(apList);	//3倍标准差
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
