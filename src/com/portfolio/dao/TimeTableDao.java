package com.portfolio.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.portfolio.dto.TimeTableDto;

public class TimeTableDao {
	private static TimeTableDao instance = new TimeTableDao();
	public static TimeTableDao getInstance() {
		return instance;
	}
	private TimeTableDao() {}
	
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		}catch(Exception e) {
			System.out.println("getConnection() error : "+e.getMessage());
		}
		return conn;
	}
	
	/***********************gNumber로 기록불러오기**************************/
	public ArrayList<TimeTableDto> oneGamett(int gNumber){
		ArrayList<TimeTableDto> tts = new ArrayList<TimeTableDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM TIME_TABLE WHERE GNUMBER=? AND TSTATUS=2 ORDER BY TTMST";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gNumber);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int tNumber =rs.getInt("tnumber");
				String mId = rs.getString("mid");
				String mNickName = rs.getString("mnickname");
				String tPlatform = rs.getString("tplatform");
				Timestamp tTmst = rs.getTimestamp("ttmst");
				String tDifficulty = rs.getString("tdifficulty");
				String tMode = rs.getString("tmode");
				int tStatus = rs.getInt("tstatus");
				Date tRdate = rs.getDate("trdate");
				String tLink = rs.getString("tlink");
				tts.add(new TimeTableDto(tNumber, mId, mNickName, gNumber, tPlatform, tTmst, tDifficulty, tMode, tStatus, tRdate, tLink));
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return tts;
	}
	
	/**********************기록승인대기리스트****************************/
	public ArrayList<TimeTableDto> recordGame(){
		ArrayList<TimeTableDto> tts = new ArrayList<TimeTableDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM TIME_TABLE WHERE TSTATUS=0 ORDER BY TRDATE";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int tNumber =rs.getInt("tnumber");
				String mId = rs.getString("mid");
				String mNickName = rs.getString("mnickname");
				int gNumber = rs.getInt("gNumber");
				String tPlatform = rs.getString("tplatform");
				Timestamp tTmst = rs.getTimestamp("ttmst");
				String tDifficulty = rs.getString("tdifficulty");
				String tMode = rs.getString("tmode");
				Date tRdate = rs.getDate("trdate");
				String tLink = rs.getString("tlink");
				int tStatus = rs.getInt("tstatus");
				tts.add(new TimeTableDto(tNumber, mId, mNickName, gNumber, tPlatform, tTmst, tDifficulty, tMode, tStatus, tRdate, tLink));
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return tts;
	}
	
	/***********************모든 등록대기 기록 갯수***************************/
	public int waitTotCnt() {
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql ="SELECT COUNT(*) FROM TIME_TABLE WHERE TSTATUS=0";
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			result = rs.getInt(1);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(stmt!=null) {
					stmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	
	/***************************tNumber로 tStatus변경******************************/
	public int recordConfirm(int tNumber, int tStatus) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE TIME_TABLE SET TSTATUS=? WHERE TNUMBER=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,tStatus);
			pstmt.setInt(2,tNumber);
			result = pstmt.executeUpdate();
			System.out.println(result==1?"상태변경완료":"상태변경실패");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	
	/*************************상태값(tstatus)으로 조회**************************/
	public ArrayList<TimeTableDto> getStatus(int tStatus){
		ArrayList<TimeTableDto> tts = new ArrayList<TimeTableDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM TIME_TABLE WHERE TSTATUS=? ORDER BY TRDATE";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tStatus);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int tNumber =rs.getInt("tnumber");
				String mId = rs.getString("mid");
				String mNickName = rs.getString("mnickname");
				int gNumber = rs.getInt("gnumber");
				String tPlatform = rs.getString("tplatform");
				Timestamp tTmst = rs.getTimestamp("ttmst");
				String tDifficulty = rs.getString("tdifficulty");
				String tMode = rs.getString("tmode");
				Date tRdate = rs.getDate("trdate");
				String tLink = rs.getString("tlink");
				tts.add(new TimeTableDto(tNumber, mId, mNickName, gNumber, tPlatform, tTmst, tDifficulty, tMode, tStatus, tRdate, tLink));
			}
		}catch(Exception e) {
			System.out.println("getStatus() error : "+e.getMessage());
		}finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return tts;
	}
	
	/***************************플랫폼으로 검색****************************/
	public ArrayList<TimeTableDto> getSelRecord(String tP, String tM, String tD, int gNumber){
		ArrayList<TimeTableDto> tts = new ArrayList<TimeTableDto>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM TIME_TABLE WHERE TPLATFORM IN ("+tP+") AND TMODE IN ("+tM+") AND TDIFFICULTY IN ("+tD+") AND TSTATUS=2 AND GNUMBER="+gNumber+" ORDER BY TTMST";
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int tNumber =rs.getInt("tnumber");
				int tStatus =rs.getInt("tstatus");
				String mId = rs.getString("mid");
				String mNickName = rs.getString("mnickname");
				String tPlatform = rs.getString("tplatform");
				Timestamp tTmst = rs.getTimestamp("ttmst");
				String tDifficulty = rs.getString("tdifficulty");
				String tMode = rs.getString("tmode");
				Date tRdate = rs.getDate("trdate");
				String tLink = rs.getString("tlink");
				tts.add(new TimeTableDto(tNumber, mId, mNickName, gNumber, tPlatform, tTmst, tDifficulty, tMode, tStatus, tRdate, tLink));
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(stmt!=null) {
					stmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return tts;
	}
	
	/***************************기록 입력******************************/
	public int insertRecord(String mId, String mNickName,int gNumber, String tPlatform, String tDifficulty, String tMode, String tTmst, String tLink) {
		int result =0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO TIME_TABLE (TNUMBER,MID,MNICKNAME,GNUMBER,TPLATFORM,TTMST,TDIFFICULTY,TMODE,TSTATUS,TRDATE,TLINK) " + 
					" VALUES (TT_SEQ.NEXTVAL,?,?,?,?,TO_TIMESTAMP(?,'HH24:MI:SS'),?,?,0,SYSDATE,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, mNickName);
			pstmt.setInt(3, gNumber);
			pstmt.setString(4, tPlatform);
			pstmt.setString(5, tTmst);
			pstmt.setString(6, tDifficulty);
			pstmt.setString(7, tMode);
			pstmt.setString(8, tLink);
			result = pstmt.executeUpdate();
			System.out.println(result==1?"입력성공":"입력실패");
		}catch(Exception e) {
			System.out.println("insertRecord() error : "+e.getMessage());
		}finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	
	/**************************1개 기록보기******************************/
	public TimeTableDto getOneRecord(int tNumber) {
		TimeTableDto record = null;
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM TIME_TABLE WHERE TNUMBER=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tNumber);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				record = new TimeTableDto();
				record.settNumber(tNumber);
				record.setgNumber(rs.getInt("gNumber"));
				record.setmId(rs.getString("mid"));
				record.setmNickName(rs.getString("mnickname"));
				record.settDifficulty(rs.getString("tdifficulty"));
				record.settLink(rs.getString("tlink"));
				record.settMode(rs.getString("tmode"));
				record.settPlatform(rs.getString("tplatform"));
				record.settRdate(rs.getDate("trdate"));
				record.settStatus(rs.getInt("tstatus"));
				record.settTmst(rs.getTimestamp("ttmst"));
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return record;
	}
	
	/**************************mId로 개인기록 조회*************************/
	public ArrayList<TimeTableDto> personalRecord(String mId){
		ArrayList<TimeTableDto> records = new ArrayList<TimeTableDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM TIME_TABLE WHERE MID = ? ORDER BY TRDATE";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				while(rs.next()) {
					int tNumber =rs.getInt("tnumber");
					String mNickName = rs.getString("mnickname");
					int gNumber = rs.getInt("gnumber");
					String tPlatform = rs.getString("tplatform");
					Timestamp tTmst = rs.getTimestamp("ttmst");
					String tDifficulty = rs.getString("tdifficulty");
					String tMode = rs.getString("tmode");
					int tStatus = rs.getInt("tstatus");
					Date tRdate = rs.getDate("trdate");
					String tLink = rs.getString("tlink");
					records.add(new TimeTableDto(tNumber, mId, mNickName, gNumber, tPlatform, tTmst, tDifficulty, tMode, tStatus, tRdate, tLink));
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return records;
	}
	
	/*****************************기록 수정*******************************/
	public int modifyRecord(String mId, int tNumber, String tPlatform, String tDifficulty, String tMode, String tTmst, String tLink) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE TIME_TABLE SET TPLATFORM = ?, TTMST = TO_TIMESTAMP(?,'HH24:MI:SS'), TDIFFICULTY=?, TMODE=?,TSTATUS=0,TLINK=? WHERE TNUMBER=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tPlatform);
			pstmt.setString(2, tTmst);
			pstmt.setString(3, tDifficulty);
			pstmt.setString(4, tMode);
			pstmt.setString(5, tLink);
			pstmt.setInt(6, tNumber);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
}
