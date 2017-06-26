package cn.mobilelink.entity;

import org.junit.Test;

public class AP {
	
	private String bssid; //Ap��MAC
	private String ssid;  //Ap������
	private double rss;      //AP��RSS
	private String area;  //AP���������
	private double x;        //AP����x
	private double y;        //AP����y
	private String time;  //AP��Ϣ������ʱ��

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getBssid() {
		return bssid;
	}

	public void setBssid(String bssid) {
		this.bssid = bssid;
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public double getRss() {
		return rss;
	}

	public void setRss(double rss) {
		this.rss = rss;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "AP [bssid=" + bssid + ", ssid=" + ssid + ", rss=" + rss
				+ ", area=" + area + ", x=" + x + ", y=" + y + ", time=" + time
				+ "]";
	}


}
