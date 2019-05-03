package com.portfolio.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class TimeTableDto {
	private int tNumber;
	private String mId;
	private String mNickName;
	private int gNumber;
	private String tPlatform;
	private Timestamp tTmst;
	private String tDifficulty;
	private String tMode;
	private int tStatus;
	private Date tRdate;
	private String tLink;
	
	public TimeTableDto() {}

	public TimeTableDto(int tNumber, String mId, String mNickName, int gNumber, String tPlatform,Timestamp tTmst, String tDifficulty,
			String tMode, int tStatus, Date tRdate, String tLink) {
		this.tNumber = tNumber;
		this.mId = mId;
		this.mNickName = mNickName;
		this.gNumber = gNumber;
		this.tPlatform = tPlatform;
		this.tTmst = tTmst;
		this.tDifficulty = tDifficulty;
		this.tMode = tMode;
		this.tStatus = tStatus;
		this.tRdate = tRdate;
		this.tLink = tLink;
	}

	public int gettNumber() {
		return tNumber;
	}

	public void settNumber(int tNumber) {
		this.tNumber = tNumber;
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

	public int getgNumber() {
		return gNumber;
	}

	public void setgNumber(int gNumber) {
		this.gNumber = gNumber;
	}

	public String gettPlatform() {
		return tPlatform;
	}
	
	public void settPlatform(String tPlatform) {
		this.tPlatform = tPlatform;
	}
	
	public Timestamp gettTmst() {
		return tTmst;
	}

	public void settTmst(Timestamp tTmst) {
		this.tTmst = tTmst;
	}

	public String gettDifficulty() {
		return tDifficulty;
	}

	public void settDifficulty(String tDifficulty) {
		this.tDifficulty = tDifficulty;
	}

	public String gettMode() {
		return tMode;
	}

	public void settMode(String tMode) {
		this.tMode = tMode;
	}

	public int gettStatus() {
		return tStatus;
	}

	public void settStatus(int tStatus) {
		this.tStatus = tStatus;
	}

	public Date gettRdate() {
		return tRdate;
	}

	public void settRdate(Date tRdate) {
		this.tRdate = tRdate;
	}

	public String gettLink() {
		return tLink;
	}

	public void settLink(String tLink) {
		this.tLink = tLink;
	}

	@Override
	public String toString() {
		return "TimeTableDto [tNumber=" + tNumber + ", mId=" + mId + ", mNickName=" + mNickName + ", gNumber=" + gNumber
				+ ", tPlatform=" + tPlatform + ", tTmst=" + tTmst + ", tDifficulty=" + tDifficulty + ", tMode=" + tMode + ", tStatus=" + tStatus
				+ ", tRdate=" + tRdate + ", tLink=" + tLink + "]";
	}
}
