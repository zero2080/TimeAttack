package com.portfolio.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.portfolio.dao.GameListDao;
import com.portfolio.dto.GameListDto;

public class GameList implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		String gTitle = request.getParameter("gTitle");
		if(pageNum == null) {
			pageNum="1";
		}
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE = 10;
		final int BLOCKSIZE = 10;
		int startRow = (currentPage-1)*PAGESIZE+1;
		int endRow = startRow+PAGESIZE-1;
		
		if(gTitle ==null) {
			GameListDao dao = GameListDao.getInstance();
			ArrayList<GameListDto> games = dao.allList(startRow, endRow);
			int totCnt = dao.totCnt();
			int pageCnt = (int)Math.ceil((double)totCnt/PAGESIZE);
			int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE+1;
			int endPage = startPage + BLOCKSIZE -1;
			if(endPage>pageCnt) {
				endPage = pageCnt;
			}
			request.setAttribute("games", games);
			request.setAttribute("BLOCKSIZE", BLOCKSIZE);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("totCnt", totCnt);
			request.setAttribute("pageNum", pageNum);
		}else {
			GameListDao dao = GameListDao.getInstance();
			ArrayList<GameListDto> games = dao.someList(startRow, endRow, gTitle);
			int totCnt = dao.totCnt();
			int pageCnt = (int)Math.ceil((double)totCnt/PAGESIZE);
			int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE+1;
			int endPage = startPage + BLOCKSIZE -1;
			if(endPage>pageCnt) {
				endPage = pageCnt;
			}
			request.setAttribute("games", games);
			request.setAttribute("BLOCKSIZE", BLOCKSIZE);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("totCnt", totCnt);
			request.setAttribute("pageNum", pageNum);
		}
	}
}
