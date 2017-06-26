package cn.mobilelink.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JDBCUtils {
	
	private static Properties props = null;
	// 给props进行初始化，即加载locate_constant_config.properties文件到props对象中
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
	
	//获取配置文件中某一key的值
	public static String getProperty(String str){
		if(str == null){ 
			System.out.println("尝试获取空值！");
			return null;
			}
		
		return props.getProperty(str);
	}
	
}
