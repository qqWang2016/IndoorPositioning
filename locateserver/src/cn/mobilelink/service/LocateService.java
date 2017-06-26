package cn.mobilelink.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.mobilelink.dao.LocateDao;
import cn.mobilelink.entity.ClientAp;
import cn.mobilelink.utils.JDBCUtils;
import cn.mobilelink.utils.LocateMathUtils;

@Service
public class LocateService {
	@Resource(name="locateDao")
	private LocateDao locateDao;  //操作数据库对象
	private static String tabName = JDBCUtils.getProperty("sqlTabName");//要连接的数据库表名称
	public LocateService(LocateDao locateDao) {
		this.locateDao = locateDao;
	}
	
	/**
	 * 将Client发送的信息封装为ApInfo
	 */
	public List<ClientAp> transferApInfo(String clientInfo){
		
		List<ClientAp> clientApList = new ArrayList<ClientAp>();
		String[] clientData = clientInfo.split(";"); // 将客户端发来的数据转换成数组
		for(int i = 0; i < clientData.length; i += 2){
			ClientAp clientAp = new ClientAp();
			clientAp.setSsid(clientData[i]);
			clientAp.setRss(Double.parseDouble(clientData[i+1]));
			clientApList.add(clientAp);
		}
		return clientApList;
	}
	/**
	 * 将Client端APList排序
	 */
	 public void sortClientApList(List<ClientAp> clientApList){
	    	LocateMathUtils.sortByRss(clientApList);     //对clientApList进行排序
	    }
	/**
	 * locate()
	 */
    public String locate(List<ClientAp> clientApList,int k){
    	return new Locate(locateDao).knn(clientApList, k);
    }
}
