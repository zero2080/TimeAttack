package com.portfolio.dto;

import java.sql.Date;

public class FreeBoardDto {
	private int fbNumber;
	private String mId;
	private String mNickName;
	private String fbTitle;
	private String fbContent;
	private String fbFile;
	private Date fbRdate;
	private int fbReadCount;
	private String fbIp;
	private int fbGroup;
	private int fbStep;
	private int fbLv;
	
	public FreeBoardDto() {}
	
	public FreeBoardDto(int fbNumber, String mId, String mNickName, String fbTitle, String fbContent, String fbFile,
			Date fbRdate, int fbReadCount, String fbIp, int fbGroup, int fbStep, int fbLv) {
		super();
		this.fbNumber = fbNumber;
		this.mId = mId;
		this.mNickName = mNickName;
		this.fbTitle = fbTitle;
		this.fbContent = fbContent;
		this.fbFile = fbFile;
		this.fbRdate = fbRdate;
		this.fbReadCount = fbReadCount;
		this.fbIp = fbIp;
		this.fbGroup = fbGroup;
		this.fbStep = fbStep;
		this.fbLv = fbLv;
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

	public String getFbTitle() {
		return fbTitle;
	}

	public void setFbTitle(String fbTitle) {
		this.fbTitle = fbTitle;
	}

	public String getFbContent() {
		return fbContent;
	}

	public void setFbContent(String fbContent) {
		this.fbContent = fbContent;
	}

	public String getFbFile() {
		return fbFile;
	}

	public void setFbFile(String fbFile) {
		this.fbFile = fbFile;
	}

	public Date getFbRdate() {
		return fbRdate;
	}

	public void setFbRdate(Date fbRdate) {
		this.fbRdate = fbRdate;
	}

	public int getFbReadCount() {
		return fbReadCount;
	}

	public void setFbReadCount(int fbReadCount) {
		this.fbReadCount = fbReadCount;
	}

	public String getFbIp() {
		return fbIp;
	}

	public void setFbIp(String fbIp) {
		this.fbIp = fbIp;
	}

	public int getFbGroup() {
		return fbGroup;
	}

	public void setFbGroup(int fbGroup) {
		this.fbGroup = fbGroup;
	}

	public int getFbStep() {
		return fbStep;
	}

	public void setFbStep(int fbStep) {
		this.fbStep = fbStep;
	}

	public int getFbLv() {
		return fbLv;
	}

	public void setFbLv(int fbLv) {
		this.fbLv = fbLv;
	}

	@Override
	public String toString() {
		return "FreeBoardDto [fbNumber=" + fbNumber + ", mId=" + mId + ", mNickName=" + mNickName + ", fbTitle="
				+ fbTitle + ", fbContent=" + fbContent + ", fbFile=" + fbFile + ", fbRdate=" + fbRdate
				+ ", fbReadCount=" + fbReadCount + ", fbIp=" + fbIp + ", fbGroup=" + fbGroup + ", fbStep=" + fbStep
				+ ", fbLv=" + fbLv + "]";
	}
	
	
}
