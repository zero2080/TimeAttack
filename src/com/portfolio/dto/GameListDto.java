package com.portfolio.dto;

import java.sql.Date;

public class GameListDto {
	private int gNumber;
	private String gPlatform;
	private String aId;
	private String gTitle;
	private String gGenre;
	private String gGenre2;
	private String gImg;
	private String gDifficulty;
	private String gCorp;
	private String gMode;
	private Date gRdate;
	private Date gDate;
	
	public GameListDto() {}
	
	public GameListDto(int gNumber, String gPlatform, String aId, String gTitle, String gGenre, String gGenre2,
			String gImg, String gDifficulty, String gCorp, String gMode, Date gRdate, Date gDate) {
		this.gNumber = gNumber;
		this.gPlatform = gPlatform;
		this.aId = aId;
		this.gTitle = gTitle;
		this.gGenre = gGenre;
		this.gGenre2 = gGenre2;
		this.gImg = gImg;
		this.gDifficulty = gDifficulty;
		this.gCorp = gCorp;
		this.gMode = gMode;
		this.gRdate = gRdate;
		this.gDate = gDate;
	}

	public int getgNumber() {
		return gNumber;
	}

	public void setgNumber(int gNumber) {
		this.gNumber = gNumber;
	}

	public String getgPlatform() {
		return gPlatform;
	}

	public void setgPlatform(String gPlatform) {
		this.gPlatform = gPlatform;
	}

	public String getaId() {
		return aId;
	}

	public void setaId(String aId) {
		this.aId = aId;
	}

	public String getgTitle() {
		return gTitle;
	}

	public void setgTitle(String gTitle) {
		this.gTitle = gTitle;
	}

	public String getgGenre() {
		return gGenre;
	}

	public void setgGenre(String gGenre) {
		this.gGenre = gGenre;
	}

	public String getgGenre2() {
		return gGenre2;
	}

	public void setgGenre2(String gGenre2) {
		this.gGenre2 = gGenre2;
	}

	public String getgImg() {
		return gImg;
	}

	public void setgImg(String gImg) {
		this.gImg = gImg;
	}

	public String getgDifficulty() {
		return gDifficulty;
	}

	public void setgDifficulty(String gDifficulty) {
		this.gDifficulty = gDifficulty;
	}

	public String getgCorp() {
		return gCorp;
	}

	public void setgCorp(String gCorp) {
		this.gCorp = gCorp;
	}

	public String getgMode() {
		return gMode;
	}

	public void setgMode(String gMode) {
		this.gMode = gMode;
	}

	public Date getgRdate() {
		return gRdate;
	}

	public void setgRdate(Date gRdate) {
		this.gRdate = gRdate;
	}

	public Date getgDate() {
		return gDate;
	}

	public void setgDate(Date gDate) {
		this.gDate = gDate;
	}

	@Override
	public String toString() {
		return "GameList [gNumber=" + gNumber + ", gPlatform=" + gPlatform + ", aId=" + aId + ", gTitle=" + gTitle
				+ ", gGenre=" + gGenre + ", gGenre2=" + gGenre2 + ", gImg=" + gImg + ", gDifficulty=" + gDifficulty
				+ ", gCorp=" + gCorp + ", gMode=" + gMode + ", gRdate=" + gRdate + ", gDate=" + gDate + "]";
	}
	
	
}
