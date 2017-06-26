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
	private LocateDao locateDao;  //�������ݿ����
	private static String tabName = JDBCUtils.getProperty("sqlTabName");//Ҫ���ӵ����ݿ������
	public LocateService(LocateDao locateDao) {
		this.locateDao = locateDao;
	}
	
	/**
	 * ��Client���͵���Ϣ��װΪApInfo
	 */
	public List<ClientAp> transferApInfo(String clientInfo){
		
		List<ClientAp> clientApList = new ArrayList<ClientAp>();
		String[] clientData = clientInfo.split(";"); // ���ͻ��˷���������ת��������
		for(int i = 0; i < clientData.length; i += 2){
			ClientAp clientAp = new ClientAp();
			clientAp.setSsid(clientData[i]);
			clientAp.setRss(Double.parseDouble(clientData[i+1]));
			clientApList.add(clientAp);
		}
		return clientApList;
	}
	/**
	 * ��Client��APList����
	 */
	 public void sortClientApList(List<ClientAp> clientApList){
	    	LocateMathUtils.sortByRss(clientApList);     //��clientApList��������
	    }
	/**
	 * locate()
	 */
    public String locate(List<ClientAp> clientApList,int k){
    	return new Locate(locateDao).knn(clientApList, k);
    }
}
