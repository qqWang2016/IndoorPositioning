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
	// ���屣�����е�Socket,���㲥��Ϣʹ��
	public static List<Socket> socketList = new ArrayList<Socket>();

	public void locate() throws IOException {
		ServerSocket server = new ServerSocket(800);
		while(true){
			Socket s = server.accept();
System.out.println("A Client connected!");
			socketList.add(s);
			//ÿ���ͻ�������֮������һ��ServerThread�߳�Ϊ�ÿͻ��˷���
			new Thread(new ServiceThread(s,locateService)).start();
		}
	}

}
