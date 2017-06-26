package cn.mobilelink.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.mobilelink.service.LocateService;
@Component
public class LocateController {

	@Resource(name="locateService")
	private LocateService locateService;
	// 定义保存所有的Socket,供广播信息使用
	public static List<Socket> socketList = new ArrayList<Socket>();

	public void locate() throws IOException {
		ServerSocket server = new ServerSocket(800);
		while(true){
			Socket s = server.accept();
System.out.println("A Client connected!");
			socketList.add(s);
			//每当客户端连接之后启动一条ServerThread线程为该客户端服务
			new Thread(new ServiceThread(s,locateService)).start();
		}
	}

}
