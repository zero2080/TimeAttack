package com.portfolio.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.portfolio.dao.TTRePlyDao;
import com.portfolio.dao.TimeTableDao;
import com.portfolio.dto.TTRePlyDto;
import com.portfolio.dto.TimeTableDto;

public class PersonalRecordView implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int tNumber = Integer.parseInt(request.getParameter("tNumber"));
		TTRePlyDao reDao = TTRePlyDao.getInstance();
		TimeTableDao dao = TimeTableDao.getInstance();
		TimeTableDto record = dao.getOneRecord(tNumber);
		
		String youtubeLink = record.gettLink();
		
		record.settLink(youtubeLink.substring(youtubeLink.length()-11,youtubeLink.length()));
		
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum="1";
		}
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE = 10;
		final int BLOCKSIZE = 10;
		int startRow = (currentPage-1)*PAGESIZE+1;
		int endRow = startRow+PAGESIZE-1;
		
		int totCnt = reDao.totCnt(tNumber);
		int pageCnt = (int)Math.ceil((double)totCnt/PAGESIZE);
		int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE+1;
		int endPage = startPage + BLOCKSIZE -1;
		if(endPage>pageCnt) {
			endPage = pageCnt;
		}
		ArrayList<TTRePlyDto> reply = reDao.getList(tNumber, startRow, endRow);
		
		request.setAttribute("record", record);
		request.setAttribute("replys", reply);
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("totCnt", totCnt);
		request.setAttribute("pageNum", pageNum);
		
	}
}
