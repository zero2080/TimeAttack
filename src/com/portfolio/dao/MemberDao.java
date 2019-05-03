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

import com.portfolio.dto.MemberDto;

public class MemberDao {
	public static int FAIL = 0;
	public static int SUCCESS = 1;
	public static int EXIST = 1;
	public static int NONEXIST =0;
	private static MemberDao instanse = new MemberDao();
	public static MemberDao getInstanse() {
		return instanse;
	}
	private MemberDao() {}
	
	/***************************DB커넥터***********************************/
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
	
	/****↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓기능↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*****/
		/********************ID 중복체크***********************/
	public int idChk(String id) {
		int result = EXIST;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM MEMBER WHERE MID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("idChk() error : "+e.getMessage());
		}finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				System.out.println("idChk().close error : "+e.getMessage());
			}
		}
		return result;
	}
		/*****************닉네임 중복체크**********************/
	public int nickNameChk(String mNickName) {
		int result = EXIST;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM MEMBER WHERE MNICKNAME=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mNickName);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("nickNameChk() error : "+e.getMessage());
		}finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				System.out.println("nickNameChk().close error : "+e.getMessage());
			}
		}
		return result;
	}
	
		/************************가입**************************/
	public int joinMember(MemberDto dto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO MEMBER (MID, MPW, MNAME, MNICKNAME, MEMAIL, MGRADE, MPOINT, MRDATE, MSTATUS) "
				   + "VALUES (?,?,?,?,?,7,1000,SYSDATE,0)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMid());
			pstmt.setString(2, dto.getMpw());
			pstmt.setString(3, dto.getMname());
			pstmt.setString(4, dto.getMnickName());
			pstmt.setString(5, dto.getMemail());
			System.out.println(dto.toString());
			if(pstmt.executeUpdate()==SUCCESS) {
				result = SUCCESS; 
			}
		}catch(Exception e) {
			System.out.println("joinMember() error : "+e.getMessage());
		}finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				System.out.println("joinMember().close error : "+e.getMessage());
			}
		}
		return result;
	}
	
		/**********************정보수정************************/
	public int updateInfo(MemberDto dto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE MEMBER SET MPW=?, MNAME=?, MNICKNAME=?, MEMAIL=? WHERE MID = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMpw());
			pstmt.setString(2, dto.getMname());
			pstmt.setString(3, dto.getMnickName());
			pstmt.setString(4, dto.getMemail());
			pstmt.setString(5, dto.getMid());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("updateInfo() error : "+e.getMessage());
		}finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				System.out.println("updateInfo().close error : "+e.getMessage());
			}
		}
		return result;
	}
		/************************탈퇴**************************/
	public int quitMember(String id) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE MEMBER SET MSTATUS=1 WHERE MID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			if(pstmt.executeUpdate()==SUCCESS) {
				result = SUCCESS;
			};
		}catch(Exception e) {
			System.out.println("quitMember() error : "+e.getMessage());
		}finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				System.out.println("quitMember().close error : "+e.getMessage());
			}
		}
		return result;
	}
		/************************강퇴**************************/
	public int kickOutMember(String id) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE MEMBER SET MSTATUS=2 WHERE MID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			if(pstmt.executeUpdate()==SUCCESS) {
				result = SUCCESS;
			};
		}catch(Exception e) {
			System.out.println("kickOutMember() error : "+e.getMessage());
		}finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				System.out.println("kickOutMember().close error : "+e.getMessage());
			}
		}
		return result;
	}
		/***********************로그인*************************/
	public MemberDto memberLogin(String id, String pw) {
		MemberDto dto =null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM MEMBER WHERE MID=? AND MPW=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getInt("mstatus")==0) {
					dto = new MemberDto();
					dto.setMid(id);
					dto.setMpw(pw);
					dto.setMemail(rs.getString("memail"));
					dto.setMgrade(rs.getInt("mgrade"));
					dto.setMname(rs.getString("mname"));
					dto.setMnickName(rs.getString("mnickname"));
					dto.setMpoint(rs.getInt("mpoint"));
					dto.setMrdate(rs.getDate("mrdate"));
					dto.setMstatus(rs.getInt("mstatus"));
				}
			}
		}catch(Exception e) {
			System.out.println("memberLogin() error : "+e.getMessage());
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
				System.out.println("memberLogin().close error : "+e.getMessage());
			}
		}
		return dto;
	}
	
		/***********************ID로 정보조회************************/
	public MemberDto getMemInfo(String id) {
		MemberDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM MEMBER WHERE MID = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt("mstatus")==0) {
					dto = new MemberDto();
					dto.setMid(id);
					dto.setMemail(rs.getString("memail"));
					dto.setMgrade(rs.getInt("mgrade"));
					dto.setMname(rs.getString("mname"));
					dto.setMnickName(rs.getString("mnickname"));
					dto.setMpoint(rs.getInt("mpoint"));
					dto.setMrdate(rs.getDate("mrdate"));
					dto.setMstatus(rs.getInt("mstatus"));
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
		return dto;
	}
	
	/*****************************모든 회원 불러오기**********************************/
		public ArrayList<MemberDto> getAllMembers() {
			ArrayList<MemberDto> members = new ArrayList<MemberDto>();
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			String sql = "SELECT * FROM MEMBER";
			try {
				conn = getConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					String mid = rs.getString("mid");
					String mpw = rs.getString("mpw");
					String mname = rs.getString("mname");
					String mnickName = rs.getString("mnickname");
					String memail = rs.getString("memail");
					int mgrade = rs.getInt("mgrade");
					int mpoint = rs.getInt("mpoint");
					Date mrdate = rs.getDate("mrdate");
					int mstatus = rs.getInt("mstatus");
					members.add(new MemberDto(mid, mpw, mname, mnickName, memail, mgrade, mpoint, mrdate, mstatus));
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
			return members;
		}
		
		/*************************자진탈퇴******************************/
		public int selfquitMemeber(String mId) {
			int result = 0;
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "UPDATE MEMBER SET MSTATUS=1 WHERE MID = ?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mId);
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
		
		/*************************강제탈퇴******************************/
		public int quitMemeber(String mId) {
			int result = 0;
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "UPDATE MEMBER SET MSTATUS=2 WHERE MID = ?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mId);
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
