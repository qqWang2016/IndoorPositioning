package cn.mobilelink.utils;

public class NumberUtils {
	public static final int LISTSIZE = 10;   //ɨ���APList����
	public static final int SCANNUM = 30;    //ɨ��Ĵ���
	public static final int LEN = 7;         //AP����Ϣ������
	public static final int APNUM = 3;       //ѡȡ���ڶ�λ��AP����
	public static final double LOCATEDELTA = 4.0;  //KNNƥ���ֵ
	
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
