package com.portfolio.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.portfolio.dao.NoticeDao;
import com.portfolio.dto.NoticeDto;

public class NoticeListView implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) {
			pageNum="1";
		}
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE = 20;
		final int BLOCKSIZE= 5;
		int startRow = (currentPage-1)*PAGESIZE+1;
		int endRow = startRow+PAGESIZE-1;
		NoticeDao dao = NoticeDao.getInstance();
		ArrayList<NoticeDto> list = dao.getList(startRow, endRow);
		request.setAttribute("list", list);
		int totCnt = dao.getTotCnt();
		int pageCnt = (int)Math.ceil((double)totCnt/PAGESIZE);
		int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE+1;
		int endPage = startPage + BLOCKSIZE -1;
		if(endPage>pageCnt) {
			endPage = pageCnt;
		}
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("totCnt", totCnt);
		request.setAttribute("pageNum", pageNum);	

	}

}
