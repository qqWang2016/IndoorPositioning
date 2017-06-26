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
	// 当前线程所处理的Socket对应的输入流
	BufferedReader br = null;
	// 定义当前线程处理的Socket
	private Socket s;
	
	public ServiceThread(Socket s, LocateService locateService) {
		this.s = s;
		this.locateService = locateService;
	}
	@Override
	public void run() {
		String content = null;
		// 采用循环不断的从Socket中读取客户端发送过来的数据
		while ((content=readFromClient()) != null) {
			OutputStream os = null;
			String clientLocation;
			this.recordingInfo();                     //控制台显示每次开始请求时间
			List<ClientAp> clientApList = locateService.transferApInfo(content);  //将读取的客户端String转换为clientApList
			locateService.sortClientApList(clientApList);        //得对Client端ApList的排序
			clientLocation = locateService.locate(clientApList,3);  //使用knn算法进行定位匹配
			System.out.println("客户端所在位置坐标：" + clientLocation);
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
	 * 定义读取客户端的信息
	 * @return 客户端wifi信息（bssid;rss;bssid;rss;.....）一共10组
	 */
	public String readFromClient() {
		try {

			return br.readLine();
		} 
		//如果捕获到异常，则证明该socket对应的客户端已经关闭
		catch (IOException e) {
			//删除该socket
			LocateController.socketList.remove(s);
			
		}
		return null;
	}

	/**
	 * 显示每次客户端请求的时间，供测试观察使用，去除
	 */
	public void recordingInfo(){
		// 显示每次请求的时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(df.format(new Date()));
		System.out.println("");
	}

}
