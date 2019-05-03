package com.portfolio.dto;

import java.sql.Date;

public class NoticeDto {
	private int nNumber;
	private String aId;
	private String nTitle;
	private String nContent;
	private String nFile;
	private int nReadcount;
	private Date nRdate;
	private int nGroup;
	private int nStep;
	private int nLv;
	
	public NoticeDto() {}

	public NoticeDto(int nNumber, String aId, String nTitle, String nContent, String nFile, int nReadcount, Date nRdate,
			int nGroup, int nStep, int nLv) {
		this.nNumber = nNumber;
		this.aId = aId;
		this.nTitle = nTitle;
		this.nContent = nContent;
		this.nFile = nFile;
		this.nReadcount = nReadcount;
		this.nRdate = nRdate;
		this.nGroup = nGroup;
		this.nStep = nStep;
		this.nLv = nLv;
	}

	public int getnNumber() {
		return nNumber;
	}

	public void setnNumber(int nNumber) {
		this.nNumber = nNumber;
	}

	public String getaId() {
		return aId;
	}

	public void setaId(String aId) {
		this.aId = aId;
	}

	public String getnTitle() {
		return nTitle;
	}

	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
	}

	public String getnContent() {
		return nContent;
	}

	public void setnContent(String nContent) {
		this.nContent = nContent;
	}

	public String getnFile() {
		return nFile;
	}

	public void setnFile(String nFile) {
		this.nFile = nFile;
	}

	public int getnReadcount() {
		return nReadcount;
	}

	public void setnReadcount(int nReadcount) {
		this.nReadcount = nReadcount;
	}

	public Date getnRdate() {
		return nRdate;
	}

	public void setnRdate(Date nRdate) {
		this.nRdate = nRdate;
	}

	public int getnGroup() {
		return nGroup;
	}

	public void setnGroup(int nGroup) {
		this.nGroup = nGroup;
	}

	public int getnStep() {
		return nStep;
	}

	public void setnStep(int nStep) {
		this.nStep = nStep;
	}

	public int getnLv() {
		return nLv;
	}

	public void setnLv(int nLv) {
		this.nLv = nLv;
	}

	@Override
	public String toString() {
		return "NoticeDto [nNumber=" + nNumber + ", aId=" + aId + ", nTitle=" + nTitle + ", nContent=" + nContent
				+ ", nFile=" + nFile + ", nReadcount=" + nReadcount + ", nRdate=" + nRdate + ", nGroup=" + nGroup + ", nStep="
				+ nStep + ", nLv=" + nLv + "]";
	}

		
}
