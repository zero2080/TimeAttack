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

import com.portfolio.dto.NoticeDto;

public class NoticeDao {
	private static NoticeDao instance = new NoticeDao();
	public static NoticeDao getInstance() {
		return instance;
	}
	private NoticeDao() {}
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
	public ArrayList<NoticeDto> getList(int startRow, int endRow){
		ArrayList<NoticeDto> dtos = new ArrayList<NoticeDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM NOTICE ORDER BY NGROUP DESC, NSTEP) A) WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int nNumber = rs.getInt("nnumber");
				String aId = rs.getString("aid");
				String nTitle = rs.getString("ntitle");
				String nContent = rs.getString("ncontent");
				String nFile = rs.getString("nfile");
				int nReadcount = rs.getInt("nReadcount");
				Date nRdate = rs.getDate("nrdate");
				int nGroup = rs.getInt("ngroup");
				int nStep = rs.getInt("nstep");
				int nLv = rs.getInt("nlv");
				dtos.add(new NoticeDto(nNumber, aId, nTitle, nContent, nFile, nReadcount, nRdate, nGroup, nStep, nLv));
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
		String sql = "SELECT COUNT(*) FROM NOTICE";
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
	public NoticeDto viewContent(int ntNumber) {
		NoticeDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM NOTICE WHERE NNUMBER=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ntNumber);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String aId = rs.getString("aid");
				String nTitle = rs.getString("ntitle");
				String nContent = rs.getString("ncontent");
				String nFile = rs.getString("nfile");
				Date nRdate = rs.getDate("nrdate");
				int nGroup = rs.getInt("ngroup");
				int nStep = rs.getInt("nstep");
				int nLv = rs.getInt("nlv");
				int nReadcount = rs.getInt("nReadcount");
				dto = new NoticeDto(ntNumber, aId, nTitle, nContent, nFile, nReadcount, nRdate, nGroup, nStep, nLv);
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
	
	/*************************글 입력**********************************/
	public int writeBoard(NoticeDto dto) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			conn = getConnection();
			if(dto.getnGroup()!=0) {
				sql = "UPDATE NOTICE SET NSTEP = NSTEP+1 WHERE NGROUP=? AND NSTEP>?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getnGroup());
				pstmt.setInt(2, dto.getnStep());
				pstmt.executeUpdate();
				pstmt.close();
				dto.setnStep(dto.getnStep()+1);
				dto.setnLv(dto.getnLv()+1);
			}
			if(dto.getnGroup()>=1) {
				sql = "INSERT INTO NOTICE (NNUMBER, AID, NTITLE, NCONTENT, NFILE, nReadcount, NRDATE, NGROUP, NSTEP, NLV) "
									+ "VALUES (NTC_SEQ.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE, 0, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getaId());
				pstmt.setString(2, dto.getnTitle());
				pstmt.setString(3, dto.getnContent());
				pstmt.setString(4, dto.getnFile());
				pstmt.setInt(5, dto.getnReadcount());
				pstmt.setInt(6, dto.getnGroup());
				pstmt.setInt(7, dto.getnStep());
				pstmt.setInt(8, dto.getnLv());
				result = pstmt.executeUpdate();
				System.out.println(result==1?"답글 작성 성공":"답글 작성 실패");
			}else {
				System.out.println(dto.toString());
				sql = "INSERT INTO NOTICE (NNUMBER, AID, NTITLE, NCONTENT, NFILE, nReadcount, NRDATE, NGROUP, NSTEP, NLV) " + 
								   	 " VALUES (NTC_SEQ.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE, NTC_SEQ.CURRVAL, 0, 0)";
				pstmt= conn.prepareStatement(sql);
				pstmt.setString(1, dto.getaId());
				pstmt.setString(2, dto.getnTitle());
				pstmt.setString(3, dto.getnContent());
				pstmt.setString(4, dto.getnFile());
				pstmt.setInt(5, dto.getnReadcount());
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
	public int updateContent(NoticeDto dto) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE NOTICE SET NTITLE=?, NCONTENT = ?, NFILE=? WHERE NNUMBER=?";
		try {
			conn=getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getnTitle());
			pstmt.setString(2, dto.getnContent());
			pstmt.setString(3, dto.getnFile());
			pstmt.setInt(4, dto.getnNumber());
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
	public int delContent(int nNumber) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE NOTICE WHERE NNUMBER=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nNumber);
			result = pstmt.executeUpdate();
			System.out.println(result==1?"삭제성공":"삭제실패");
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
	public void readCountup(int nNumber) { Connection conn = null;
	
	PreparedStatement pstmt = null; 
	
	String sql = "UPDATE NOTICE SET NREADCOUNT=NREADCOUNT+1 WHERE NNUMBER=?"; 
	
		try { 
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,nNumber);
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
