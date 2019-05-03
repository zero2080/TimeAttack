package com.portfolio.dto;

import java.sql.Date;

public class TTRePlyDto {
	private int trNumber;
	private String mId;
	private int tNumber;
	private String trContent;
	private int trGroup;
	private String trIp;
	private Date trDate;
	
	public TTRePlyDto() {}

	public TTRePlyDto(int trNumber, String mId, int tNumber, String trContent, int trGroup, String trIp, Date trDate) {
		this.trNumber = trNumber;
		this.mId = mId;
		this.tNumber = tNumber;
		this.trContent = trContent;
		this.trGroup = trGroup;
		this.trIp = trIp;
		this.trDate = trDate;
	}

	public int getTrNumber() {
		return trNumber;
	}

	public void setTrNumber(int trNumber) {
		this.trNumber = trNumber;
	}

	public String getmId() {
		return mId;
	}
	
	public void setmId(String mId) {
		this.mId = mId;
	}
	public int gettNumber() {
		return tNumber;
	}

	public void settNumber(int tNumber) {
		this.tNumber = tNumber;
	}

	public String getTrContent() {
		return trContent;
	}

	public void setTrContent(String trContent) {
		this.trContent = trContent;
	}

	public int getTrGroup() {
		return trGroup;
	}

	public void setTrGroup(int trGroup) {
		this.trGroup = trGroup;
	}

	public String getTrIp() {
		return trIp;
	}

	public void setTrIp(String trIp) {
		this.trIp = trIp;
	}

	public Date getTrDate() {
		return trDate;
	}

	public void setTrDate(Date trDate) {
		this.trDate = trDate;
	}

	@Override
	public String toString() {
		return "TTRePlyDto [trNumber=" + trNumber + ", mId=" + mId + ", tNumber=" + tNumber + ", trContent=" + trContent + ", trGroup="
				+ trGroup + ", trIp=" + trIp + ", trDate=" + trDate + "]";
	}
	
	
	
}
