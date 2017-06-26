package cn.mobilelink.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.mobilelink.entity.ClientAp;
import cn.mobilelink.service.LocateService;

class ServiceThread implements Runnable{
	private LocateService locateService = null;
	// ��ǰ�߳��������Socket��Ӧ��������
	BufferedReader br = null;
	// ���嵱ǰ�̴߳����Socket
	private Socket s;
	
	public ServiceThread(Socket s, LocateService locateService) {
		this.s = s;
		this.locateService = locateService;
	}
	@Override
	public void run() {
		String content = null;
		// ����ѭ�����ϵĴ�Socket�ж�ȡ�ͻ��˷��͹���������
		while ((content=readFromClient()) != null) {
			OutputStream os = null;
			String clientLocation;
			this.recordingInfo();                     //����̨��ʾÿ�ο�ʼ����ʱ��
			List<ClientAp> clientApList = locateService.transferApInfo(content);  //����ȡ�Ŀͻ���Stringת��ΪclientApList
			locateService.sortClientApList(clientApList);        //�ö�Client��ApList������
			clientLocation = locateService.locate(clientApList,3);  //ʹ��knn�㷨���ж�λƥ��
			System.out.println("�ͻ�������λ�����꣺" + clientLocation);
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			try {
				os.write((clientLocation + "\n").getBytes("gbk"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			clientLocation = null;
		}
	}
	
	/**
	 * �����ȡ�ͻ��˵���Ϣ
	 * @return �ͻ���wifi��Ϣ��bssid;rss;bssid;rss;.....��һ��10��
	 */
	public String readFromClient() {
		try {

			return br.readLine();
		} 
		//��������쳣����֤����socket��Ӧ�Ŀͻ����Ѿ��ر�
		catch (IOException e) {
			//ɾ����socket
			LocateController.socketList.remove(s);
			
		}
		return null;
	}

	/**
	 * ��ʾÿ�οͻ��������ʱ�䣬�����Թ۲�ʹ�ã�ȥ��
	 */
	public void recordingInfo(){
		// ��ʾÿ�������ʱ��
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(df.format(new Date()));
		System.out.println("");
	}

}
