package cn.mobilelink.entity;

import org.junit.Test;

public class AP {
	
	private String bssid; //Ap的MAC
	private String ssid;  //Ap的名称
	private double rss;      //AP的RSS
	private String area;  //AP的区域号码
	private double x;        //AP坐标x
	private double y;        //AP坐标y
	private String time;  //AP信息产生的时间

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
