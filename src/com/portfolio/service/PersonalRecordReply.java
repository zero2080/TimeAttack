package com.portfolio.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.portfolio.dao.TTRePlyDao;
import com.portfolio.dto.TTRePlyDto;

public class PersonalRecordReply implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int tNumber = Integer.parseInt(request.getParameter("tNumber"));
		String mId = request.getParameter("mId");
		String trContent = request.getParameter("trContent");
		String trIp = request.getRemoteAddr();
		TTRePlyDao dao = TTRePlyDao.getInstance();
		
		if(trContent!=null) {
			int result = dao.insertReply(tNumber, mId,trContent, trIp);
			System.out.println(result==1?"등록 성공":"등록 실패");
		}
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum="1";
		}
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE = 10;
		final int BLOCKSIZE = 10;
		int startRow = (currentPage-1)*PAGESIZE+1;
		int endRow = startRow+PAGESIZE-1;
		
		int totCnt = dao.totCnt(tNumber);
		int pageCnt = (int)Math.ceil((double)totCnt/PAGESIZE);
		int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE+1;
		int endPage = startPage + BLOCKSIZE -1;
		if(endPage>pageCnt) {
			endPage = pageCnt;
		}
		ArrayList<TTRePlyDto> reply = dao.getList(tNumber, startRow, endRow);
		
		request.setAttribute("replys", reply);
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("totCnt", totCnt);
		request.setAttribute("pageNum", pageNum);
	}
}
