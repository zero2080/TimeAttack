package com.portfolio.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.portfolio.dto.FBReplyDto;

public class FBReplyDao {
	private static FBReplyDao instance = new FBReplyDao();
	public static FBReplyDao getInstance() {
		return instance;
	}
	private FBReplyDao() {}
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		}catch(Exception e) {
			System.out.println("getConnection() error : "+ e.getMessage());
		}
		return conn;
	}
	
	/*********************리플 가져오기************************/
	public ArrayList<FBReplyDto> getReply(int fbNumber){
		ArrayList<FBReplyDto> dtos = new ArrayList<FBReplyDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM FB_REPLY WHERE FBNUMBER=? ORDER BY FRGROUP, FRNUMBER";
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fbNumber);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int frNumber = rs.getInt("frnumber");
				String mId = rs.getString("mid");
				String mNickName = rs.getString("mnickname");
				String frContent = rs.getString("frcontent");
				int mrGroup = rs.getInt("frgroup");
				String grIp = rs.getString("frIp");
				Date frDate = rs.getDate("frdate");
				dtos.add(new FBReplyDto(frNumber,fbNumber,mId,mNickName,frContent,mrGroup,grIp,frDate));
			}
		}catch(Exception e) {
			System.out.println("getReply() error:"+e.getMessage());
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
				System.out.println("getReply().close error : "+e.getMessage());
			}
		}
		return dtos;
	}
	
	/***********************총 댓글 갯수**************************/
	public int totCnt(int fbNumber) {
		int result = 0;
		Connection conn =null;
		PreparedStatement pstmt = null;
		String sql = "SELECT COUNT(*) FROM FB_REPLY WHERE FBNUMBER=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fbNumber);
			result = pstmt.executeUpdate();
			System.out.println(result);
		}catch(Exception e) {
			System.out.println("totCnt() error : "+e.getMessage());
		}finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				System.out.println("totCnt().close error : "+e.getMessage());
			}
		}
		return result;
	}
	
	/********************************리플 달기**************************************/
	public int insertReply(FBReplyDto dto) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql ="INSERT INTO FB_REPLY (FRNUMBER, FBNUMBER, MID, MNICKNAME, FRIP,FRGROUP, FRCONTENT, FRDATE) "+ 
					"VALUES (FR_SEQ.NEXTVAL,?,?,?,?,FR_SEQ.CURRVAL,?,SYSDATE)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getFbNumber());
			pstmt.setString(2, dto.getmId());
			pstmt.setString(3, dto.getmNickName());
			pstmt.setString(4, dto.getFrIp());
			pstmt.setString(5, dto.getFrContent());
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
	
	/****************************리플 삭제***********************************/
	public int delReply(int frNumber) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FB_REPLY WHERE FRNUMBER=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,frNumber);
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
