package cn.mobilelink.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JDBCUtils {
	
	private static Properties props = null;
	// ��props���г�ʼ����������locate_constant_config.properties�ļ���props������
	static{
		InputStream in= JDBCUtils.class.getClassLoader()
				.getResourceAsStream("locate_constant_config.properties");
		props = new Properties();
		try {
			props.load(in);
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
	
	//��ȡ�����ļ���ĳһkey��ֵ
	public static String getProperty(String str){
		if(str == null){ 
			System.out.println("���Ի�ȡ��ֵ��");
			return null;
			}
		
		return props.getProperty(str);
	}
	
}
