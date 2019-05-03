package com.portfolio.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.portfolio.dto.FreeBoardDto;

public class FreeBoardDao {
	private static FreeBoardDao instance = new FreeBoardDao();
	public static FreeBoardDao getInstance() {
		return instance;
	}
	private FreeBoardDao() {}
	public Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		}catch(Exception e) {
			System.out.println("Connection error : "+e.getMessage());
		}
		return conn;
	}
	
	/*****************************게시판 리스트**********************************/
	public ArrayList<FreeBoardDto> getList(int startRow, int endRow){
		ArrayList<FreeBoardDto> dtos = new ArrayList<FreeBoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT C.*,B.RCOUNT FROM FREE_BOARD C,(SELECT FBNUMBER, COUNT(*) RCOUNT FROM FB_REPLY GROUP BY FBNUMBER) B WHERE C.FBNUMBER=B.FBNUMBER(+) ORDER BY FBGROUP DESC, FBSTEP) A) WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int fbNumber = rs.getInt("fbnumber");
				String mId = rs.getString("mid");
				String mNickName = rs.getString("mnickname");
				String fbTitle = rs.getString("fbtitle");
				String fbContent = rs.getString("fbcontent");
				String fbFile = rs.getString("fbfile");
				Date fbRdate = rs.getDate("fbrdate");
				int fbReadCount = rs.getInt("fbreadcount");
				String fbIp = rs.getString("fbip");
				int fbGroup = rs.getInt("fbgroup");
				int fbStep = rs.getInt("fbstep");
				int fbLv = rs.getInt("fblv");
				dtos.add(new FreeBoardDto(fbNumber, mId, mNickName, fbTitle, fbContent, fbFile,	fbRdate, fbReadCount, fbIp, fbGroup, fbStep, fbLv));
			}
		}catch(Exception e) {
			System.out.println("getList() error : "+e.getMessage());
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
				System.out.println("getList().close error : "+e.getMessage());
			}
		}
		return dtos;
	}
	
	/**************************총 글 갯수*******************************/
	public int getTotCnt() {
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM FREE_BOARD";
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			result = rs.getInt(1);
		}catch(Exception e) {
			System.out.println("getTotCnt() error : "+e.getMessage());
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
				System.out.println("getTotCnt().close error : "+e.getMessage());
			}
		}
		return result;
	}
	
	/****************************글보기**********************************/
	public FreeBoardDto viewContent(int fbNumber) {
		FreeBoardDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM FREE_BOARD WHERE FBNUMBER=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fbNumber);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String mId = rs.getString("mid");
				String mNickName = rs.getString("mnickname");
				String fbTitle = rs.getString("fbtitle");
				String fbContent = rs.getString("fbcontent");
				String fbFile = rs.getString("fbfile");
				Date fbRdate = rs.getDate("fbrdate");
				int fbReadCount = rs.getInt("fbreadcount");
				String fbIp = rs.getString("fbip");
				int fbGroup = rs.getInt("fbgroup");
				int fbStep = rs.getInt("fbstep");
				int fbLv = rs.getInt("fblv");
				dto = new FreeBoardDto(fbNumber, mId, mNickName, fbTitle, fbContent, fbFile,	fbRdate, fbReadCount, fbIp, fbGroup, fbStep, fbLv);
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
		return dto;
	}
	
	/*************************글입력**********************************/
	public int writeBoard(FreeBoardDto dto) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			conn = getConnection();
			if(dto.getFbGroup()!=0) {
				sql = "UPDATE FREE_BOARD SET FBSTEP = FBSTEP+1 WHERE FBGROUP=? AND FBSTEP>?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getFbGroup());
				pstmt.setInt(2, dto.getFbStep());
				pstmt.executeUpdate();
				pstmt.close();
				dto.setFbStep(dto.getFbStep()+1);
				dto.setFbLv(dto.getFbLv()+1);
			}
			if(dto.getFbGroup()>=1) {
				sql = "INSERT INTO FREE_BOARD (FBNUMBER, MID, MNICKNAME, FBTITLE, FBCONTENT, FBFILE, FBRDATE, FBREADCOUNT, FBIP,FBGROUP, FBSTEP, FBLV) "
									+ "VALUES (FB_SEQ.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE, 0, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getmId());
				pstmt.setString(2, dto.getmNickName());
				pstmt.setString(3, dto.getFbTitle());
				pstmt.setString(4, dto.getFbContent());
				pstmt.setString(5, dto.getFbFile());
				pstmt.setString(6, dto.getFbIp());
				pstmt.setInt(7, dto.getFbGroup());
				pstmt.setInt(8, dto.getFbStep());
				pstmt.setInt(9, dto.getFbLv());
				result = pstmt.executeUpdate();
				System.out.println(result==1?"답글 작성 성공":"답글 작성 실패");
			}else {
				System.out.println(dto.toString());
				sql = "INSERT INTO FREE_BOARD (FBNUMBER, MID, MNICKNAME, FBTITLE, FBCONTENT, FBFILE, FBRDATE, FBREADCOUNT, FBIP, FBGROUP, FBSTEP, FBLV) " + 
								   	 " VALUES (FB_SEQ.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE, 0, ?,FB_SEQ.CURRVAL, 0, 0)";
				pstmt= conn.prepareStatement(sql);
				pstmt.setString(1, dto.getmId());
				pstmt.setString(2, dto.getmNickName());
				pstmt.setString(3, dto.getFbTitle());
				pstmt.setString(4, dto.getFbContent());
				pstmt.setString(5, dto.getFbFile());
				pstmt.setString(6, dto.getFbIp());
				result = pstmt.executeUpdate();
				System.out.println(result==1?"글 작성 성공":"글 작성 실패");
			}
			
		}catch(Exception e) {
			System.out.println("writeBoard() error : "+e.getMessage());
		}finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				System.out.println("writeBoard().close error : "+e.getMessage());
			}
		}
		return result;
	}
	
	/***********************글 수정****************************/
	public int updateContent(FreeBoardDto dto) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE FREE_BOARD SET FBTITLE=?, FBCONTENT = ?, FBFILE=?, FBIP=? WHERE FBNUMBER=?";
		try {
			conn=getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getFbTitle());
			pstmt.setString(2, dto.getFbContent());
			pstmt.setString(3, dto.getFbFile());
			pstmt.setString(4, dto.getFbIp());
			pstmt.setInt(5, dto.getFbNumber());
			result = pstmt.executeUpdate();
			System.out.println(result==1?"글 수정 성공":"글 수정 실패");
		}catch(Exception e) {
			System.out.println("updateContent() error : "+e.getMessage());
		}finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				System.out.println("updateContent().close error : "+e.getMessage());
			}
		}
		return result;
	}
	
	/***************************글 삭제******************************/
	public int delContent(int fbNumber) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FREE_BOARD WHERE FBNUMBER=?";
		String sql2 = "DELETE FB_REPLY WHERE FBNUMBER=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, fbNumber);
			pstmt.execute();
			pstmt.close();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fbNumber);
			result = pstmt.executeUpdate();
			System.out.println(result==1?"글 삭제 성공":"글 삭제 실패");
		}catch(Exception e) {
			System.out.println("delContent() error : "+e.getMessage());
		}finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				System.out.println("delContent().close error : "+e.getMessage());
			}
		}
		return result;
	}
	
	/************************조회수 올리기*********************************/
	public void readCountup(int fbNumber) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE FREE_BOARD SET FBREADCOUNT=FBREADCOUNT+1 WHERE FBNUMBER=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fbNumber);
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("readCountup() error : "+e.getMessage());
		}finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				System.out.println("readCountUp().close error : "+e.getMessage());
			}
		}
	}
}
