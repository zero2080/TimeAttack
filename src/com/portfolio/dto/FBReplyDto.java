package com.portfolio.dto;

import java.sql.Date;

public class FBReplyDto {
	private int frNumber;
	private int fbNumber;
	private String mId;
	private String mNickName;
	private String frContent;
	private int mrGroup;
	private String frIp;
	private Date frDate;
	
	public FBReplyDto() {}

	public FBReplyDto(int frNumber, int fbNumber, String mId, String mNickName, String frContent, int mrGroup,
			String frIp, Date frDate) {
		super();
		this.frNumber = frNumber;
		this.fbNumber = fbNumber;
		this.mId = mId;
		this.mNickName = mNickName;
		this.frContent = frContent;
		this.mrGroup = mrGroup;
		this.frIp = frIp;
		this.frDate = frDate;
	}

	public int getFrNumber() {
		return frNumber;
	}

	public void setFrNumber(int frNumber) {
		this.frNumber = frNumber;
	}

	public int getFbNumber() {
		return fbNumber;
	}

	public void setFbNumber(int fbNumber) {
		this.fbNumber = fbNumber;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmNickName() {
		return mNickName;
	}

	public void setmNickName(String mNickName) {
		this.mNickName = mNickName;
	}

	public String getFrContent() {
		return frContent;
	}

	public void setFrContent(String frContent) {
		this.frContent = frContent;
	}

	public int getMrGroup() {
		return mrGroup;
	}

	public void setMrGroup(int mrGroup) {
		this.mrGroup = mrGroup;
	}

	public String getFrIp() {
		return frIp;
	}

	public void setGrIp(String frIp) {
		this.frIp = frIp;
	}

	public Date getFrDate() {
		return frDate;
	}

	public void setFrDate(Date frDate) {
		this.frDate = frDate;
	}

	@Override
	public String toString() {
		return "FBReplyDto [frNumber=" + frNumber + ", fbNumber=" + fbNumber + ", mId=" + mId + ", mNickName="
				+ mNickName + ", frContent=" + frContent + ", mrGroup=" + mrGroup + ", grIp=" + frIp + ", frDate="
				+ frDate + "]";
	}
	
	
}
