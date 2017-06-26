package cn.mobilelink.entity;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ClientAp {
	private String ssid;
	private double rss;
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
	@Override
	public String toString() {
		return "ClientApInfo [ssid=" + ssid + ", rss=" + rss + "]";
	}

}
