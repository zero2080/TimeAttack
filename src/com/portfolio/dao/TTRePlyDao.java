package com.portfolio.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.portfolio.dto.TTRePlyDto;

public class TTRePlyDao {
	private static TTRePlyDao instance = new TTRePlyDao();
	public static TTRePlyDao getInstance() {
		return instance;
	}
	private TTRePlyDao() {}
	
	private static Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	/***********************댓글입력**************************/
	public int insertReply(int tNumber,String mId,String trContent,String trIp) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO TT_REPLY (TRNUMBER,MID, TNUMBER,TRCONTENT, TRGROUP,TRIP,TRDATE) " + 
				    " VALUES (TR_SEQ.NEXTVAL,?,?,?,TR_SEQ.CURRVAL,?,SYSDATE)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setInt(2,tNumber);
			pstmt.setString(3, trContent);
			pstmt.setString(4, trIp);
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
	
	/*************************댓글 목록****************************/
	public ArrayList<TTRePlyDto> getList(int tNumber,int startRow, int endRow){
		ArrayList<TTRePlyDto> repl = new ArrayList<TTRePlyDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM TT_REPLY WHERE TNUMBER=? ORDER BY TRDATE) A ) WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tNumber);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int trNumber =rs.getInt("trnumber");
				String mId = rs.getString("mid");
				String trContent = rs.getString("trcontent");
				int trGroup = rs.getInt("trgroup");
				String trIp = rs.getString("trip");
				Date trDate = rs.getDate("trdate");
				repl.add(new TTRePlyDto(trNumber, mId, tNumber, trContent, trGroup, trIp, trDate));
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
		return repl;
	}
	
	/**********************댓글삭제**************************/
	public int delReply(int trNumber) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE TT_REPLY WHERE TRNUMBER=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, trNumber);
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
	
	/***************************총 리플 갯수***************************/
	public int totCnt(int tNumber) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM TT_REPLY WHERE TNUMBER=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tNumber);
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt(1);
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

