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

import com.portfolio.dto.GameListDto;

public class GameListDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private static GameListDao instance = new GameListDao();
	public static GameListDao getInstance() {
		return instance;
	}
	private GameListDao() {}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		}catch(Exception e ) {
			System.out.println("getConnection() error : "+e.getMessage());
		}
		return conn;
	}
	
	/************************게임등록***************************/
	public int regGame(GameListDto dto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO GAMELIST (GNUMBER, GPLATFORM ,AID ,GTITLE ,GGENRE ,GGENRE2 ,GIMG ,GDIFFICULTY ,GCORP ,GMODE ,GRDATE ,GDATE) " + 
				"     VALUES (GL_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,SYSDATE,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getgPlatform());
			pstmt.setString(2, dto.getaId());
			pstmt.setString(3, dto.getgTitle());
			pstmt.setString(4, dto.getgGenre());
			pstmt.setString(5, dto.getgGenre2());
			pstmt.setString(6, dto.getgImg());
			pstmt.setString(7, dto.getgDifficulty());
			pstmt.setString(8, dto.getgCorp());
			pstmt.setString(9, dto.getgMode());
			pstmt.setDate(10, dto.getgDate());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("regGame() error : " + e.getMessage());
		}finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				System.out.println("regGame().close error : "+e.getMessage());
			}
		}
		return result;
	}
	
	/*****************************게임 정보 수정***********************************/
	public int updateGame(GameListDto dto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE GAMELIST SET GPLATFORM = ?, AID=?, GTITLE=?, GGENRE=?, GGENRE2=?, GIMG=?, GDIFFICULTY=?, GCORP=?, GMODE=?, GDATE=? WHERE GNUMBER=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getgPlatform());
			pstmt.setString(2, dto.getaId());
			pstmt.setString(3, dto.getgTitle());
			pstmt.setString(4, dto.getgGenre());
			pstmt.setString(5, dto.getgGenre2());
			pstmt.setString(6, dto.getgImg());
			pstmt.setString(7, dto.getgDifficulty());
			pstmt.setString(8, dto.getgCorp());	
			pstmt.setString(9, dto.getgMode());
			pstmt.setDate(10, dto.getgDate());
			pstmt.setInt(11, dto.getgNumber());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("updateGame() error : "+e.getMessage());
		}finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				System.out.println("updateGame().close error : "+e.getMessage());
			}
		}
		return result;
	}
	
	/************************게임정보 가져오기*****************************/
	public GameListDto searchGame(int gnumber) {
		GameListDto game = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM GAMELIST WHERE GNUMBER=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gnumber);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int gNumber =rs.getInt("gnumber");
				String gPlatform=rs.getString("gplatform");
				String aId = rs.getString("aid");
				String gTitle = rs.getString("gtitle");
				String gGenre = rs.getString("ggenre");
				String gGenre2 = rs.getString("ggenre2");
				String gImg = rs.getString("gimg");
				String gDifficulty = rs.getString("gdifficulty");
				String gCorp = rs.getString("gcorp");
				String gMode = rs.getString("gmode");
				Date gRdate = rs.getDate("grdate");
				Date gDate = rs.getDate("gdate");
				game = new GameListDto(gNumber, gPlatform, aId, gTitle, gGenre, gGenre2, gImg, gDifficulty, gCorp, gMode, gRdate, gDate);
			}
		}catch(Exception e) {
			System.out.println("serchGame() error : "+ e.getMessage());
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
				System.out.println("searchGame().close error : "+e.getMessage());
			}
		}
		return game;
	}
	
	/*******************************게임삭제*******************************/
	public int delGame(int gnumber) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM GAME_LIST WHERE GNUMBER=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gnumber);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("delGame() error : "+e.getMessage());
		}finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e ) {
				System.out.println("delGame().close error : "+e.getMessage());
			}
		}
		return result;
	}
	
	/***************************전체 게임갯수*******************************/
	public int totCnt() {
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		String sql = "SELECT COUNT(*) FROM GAMELIST";
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
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
	
	/***************************전체 목록*******************************/
	public ArrayList<GameListDto> allList(int startRow, int endRow){
		ArrayList<GameListDto> games = new ArrayList<GameListDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM GAMELIST ORDER BY GNUMBER) A) WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int gNumber = rs.getInt("gnumber");
				String gPlatform = rs.getString("gplatform");
				String aId = rs.getString("aid");
				String gTitle = rs.getString("gtitle");
				String gGenre = rs.getString("ggenre");
				String gGenre2 = rs.getString("ggenre2");
				String gImg = rs.getString("gimg");
				String gDifficulty = rs.getString("gdifficulty");
				String gCorp = rs.getString("gcorp");
				String gMode = rs.getString("gmode");
				Date gRdate = rs.getDate("grdate");
				Date gDate = rs.getDate("gdate");
				games.add(new GameListDto(gNumber, gPlatform, aId, gTitle, gGenre, gGenre2,gImg, gDifficulty, gCorp, gMode, gRdate, gDate));
			}
		}catch(Exception e) {
			System.out.println("allList() error : "+e.getMessage());
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
		return games;
	}
	
	/*************************게임검색*****************************/
	public ArrayList<GameListDto> someList(int startRow, int endRow,String word){
		ArrayList<GameListDto> games = new ArrayList<GameListDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM GAMELIST ORDER BY GNUMBER) A) WHERE RN BETWEEN ? AND ? AND GTITLE LIKE '%'||UPPER(?)||'%'";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			pstmt.setString(3, word);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int gNumber = rs.getInt("gnumber");
				String gPlatform = rs.getString("gplatform");
				String aId = rs.getString("aid");
				String gTitle = rs.getString("gtitle");
				String gGenre = rs.getString("ggenre");
				String gGenre2 = rs.getString("ggenre2");
				String gImg = rs.getString("gimg");
				String gDifficulty = rs.getString("gdifficulty");
				String gCorp = rs.getString("gcorp");
				String gMode = rs.getString("gmode");
				Date gRdate = rs.getDate("grdate");
				Date gDate = rs.getDate("gdate");
				games.add(new GameListDto(gNumber, gPlatform, aId, gTitle, gGenre, gGenre2,gImg, gDifficulty, gCorp, gMode, gRdate, gDate));
			}
		}catch(Exception e) {
			System.out.println("allList() error : "+e.getMessage());
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
		return games;
	}
}
