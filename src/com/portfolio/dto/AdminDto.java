package com.portfolio.dto;

public class AdminDto {
	private String aid;
	private String apw;
	private int agrade;
	private String anickname;
	private String aname;
	
	public AdminDto() {}
	
	public AdminDto(String aid, String apw, int agrade, String anickname, String aname) {
		this.aid = aid;
		this.apw = apw;
		this.agrade = agrade;
		this.anickname = anickname;
		this.aname = aname;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getApw() {
		return apw;
	}
	public void setApw(String apw) {
		this.apw = apw;
	}
	public int getAgrade() {
		return agrade;
	}
	public void setAgrade(int agrade) {
		this.agrade = agrade;
	}
	public String getAnickname() {
		return anickname;
	}
	public void setAnickname(String anickname) {
		this.anickname = anickname;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	@Override
	public String toString() {
		return "AdminDto [aid=" + aid + ", apw=" + apw + ", agrade=" + agrade + ", anickname=" + anickname + ", aname="
				+ aname + "]";
	}
	
}
