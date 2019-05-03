package com.portfolio.dto;

import java.sql.Date;

public class MemberDto {
	private String mid;
	private String mpw;
	private String mname;
	private String mnickName;
	private String memail;
	private int mgrade;
	private int mpoint;
	private Date mrdate;
	private int mstatus;
	
	public MemberDto() {}

	public MemberDto(String mid, String mpw, String mname, String mnickName, String memail, int mgrade, int mpoint, Date mrdate, int mstatus) {
		this.mid = mid;
		this.mpw = mpw;
		this.mname = mname;
		this.mnickName = mnickName;
		this.memail = memail;
		this.mgrade = mgrade;
		this.mpoint = mpoint;
		this.mrdate = mrdate;
		this.mstatus = mstatus;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMpw() {
		return mpw;
	}

	public void setMpw(String mpw) {
		this.mpw = mpw;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMnickName() {
		return mnickName;
	}

	public void setMnickName(String mnickName) {
		this.mnickName = mnickName;
	}

	public String getMemail() {
		return memail;
	}

	public void setMemail(String memail) {
		this.memail = memail;
	}

	public int getMgrade() {
		return mgrade;
	}

	public void setMgrade(int mgrade) {
		this.mgrade = mgrade;
	}

	public int getMpoint() {
		return mpoint;
	}

	public void setMpoint(int mpoint) {
		this.mpoint = mpoint;
	}

	public Date getMrdate() {
		return mrdate;
	}

	public void setMrdate(Date mrdate) {
		this.mrdate = mrdate;
	}

	public int getMstatus() {
		return mstatus;
	}

	public void setMstatus(int mstatus) {
		this.mstatus = mstatus;
	}

	@Override
	public String toString() {
		return "MemberDto [mid=" + mid + ", mpw=" + mpw + ", mname=" + mname + ", mnickName=" + mnickName + ", memail="
				+ memail + ", mgrade=" + mgrade + ", mpoint=" + mpoint + ", mrdate=" + mrdate + ", mstatus=" + mstatus
				+ "]";
	}

	
}
