package cn.mobilelink.controller;

import java.io.IOException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class LocateMain{

	public static void main(String[] args) throws IOException {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"/applicationContext.xml");
		((LocateController) ac.getBean("locateController")).locate();
	}
}