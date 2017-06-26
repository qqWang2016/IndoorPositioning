package cn.mobilelink.utils;

public class NumberUtils {
	public static final int LISTSIZE = 10;   //扫描的APList长度
	public static final int SCANNUM = 30;    //扫描的次数
	public static final int LEN = 7;         //AP中信息量长度
	public static final int APNUM = 3;       //选取用于定位的AP个数
	public static final double LOCATEDELTA = 4.0;  //KNN匹配差值
	
	public static int getListsize() {
		return LISTSIZE;
	}
	
	public static int getScannum() {
		return SCANNUM;
	}
	
	public static int getLen() {
		return LEN;
	}
	
	public static int getApnum() {
		return APNUM;
	}
	
	public static double getLocatedelta() {
		return LOCATEDELTA;
	}
	
}
