package com.portfolio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.portfolio.dto.AdminDto;

public class AdminDao {
	private static final int FAIL = 0;
	private static final int SUCCESS = 1;
	private static final int EXIST = 1;
	private static final int NONEXIST = 0;
	private static AdminDao instance = new AdminDao();
	public static AdminDao getInstance() {
		return instance;
	}
	private AdminDao() {}
	
	public Connection getConnection() {
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
	
	public int joinAdmin(AdminDto dto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO ADMIN (AID, APW, AGRADE, ANICKNAME, ANAME) "
				 	+"VALUES (?,?,1,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getAid());
			pstmt.setString(2, dto.getApw());
			pstmt.setString(3, dto.getAnickname());
			pstmt.setString(4, dto.getAname());
			result = pstmt.executeUpdate();
			System.out.println("AdminJoin : "+(result==SUCCESS?"SUCCESS":"FAIL"));
		}catch(Exception e) {
			System.out.println("AdminJoin() error : "+e.getMessage());
		}finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				System.out.println("AdminJoin().close error : "+e.getMessage());
			}
		}
		return result;
	}
	public int chkAid(String aid) {
		int result = EXIST;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM ADMIN WHERE AID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aid);
			result = pstmt.executeUpdate();
			System.out.println("Aid Check : "+(result==NONEXIST?"ID가 존재하지 않습니다.":"ID가 이미 존재합니다.."));
		}catch(Exception e) {
			System.out.println("chkAid() error : "+e.getMessage());
		}finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				System.out.println("chkAid().close error : "+e.getMessage());
			}
		}
		return result;
	}
	
	public int delAid(String aid) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE ADMIN SET AGRADE = -1 WHERE AID = ?";
		try {
			conn= getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aid);
			result = pstmt.executeUpdate();
			System.out.println("delAid : "+(result==SUCCESS?"관리자 탈퇴 성공":"관리자 탈퇴 실패"));
					
		}catch(Exception e) {
			System.out.println("delAid() error : "+e.getMessage());
		}finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				System.out.println("delAid().close error : "+e.getMessage());
			}
		}
		return result;
	}
	
	public AdminDto aLogin(String aid, String apw) {
		AdminDto admin = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM ADMIN WHERE AID = ? AND APW=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aid);
			pstmt.setString(2, apw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int agrade = rs.getInt("agrade");
				String anickname = rs.getString("anickname");
				String aname = rs.getString("aname");
				admin = new AdminDto(aid,apw,agrade,anickname,aname);
			}
			System.out.println("Admin Login : "+(admin==null?"로그인 실패":"로그인 성공"));
		}catch(Exception e) {
			System.out.println("aLogin() error : "+e.getMessage());
		}finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				System.out.println("aLogin().close error : "+e.getMessage());
			}
		}
		return admin;
	}
	
}
