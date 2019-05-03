package com.portfolio.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.portfolio.dao.FBReplyDao;
import com.portfolio.dao.FreeBoardDao;
import com.portfolio.dto.FBReplyDto;
import com.portfolio.dto.FreeBoardDto;

public class FBContentView implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int fbNumber = Integer.parseInt(request.getParameter("fbNumber"));
		String pageNum = request.getParameter("pageNum");
		FreeBoardDao dao = FreeBoardDao.getInstance();
		dao.readCountup(fbNumber);
		FreeBoardDto dto = dao.viewContent(fbNumber);
		FBReplyDao reDao = FBReplyDao.getInstance();
		ArrayList<FBReplyDto> reDto = reDao.getReply(fbNumber);
		int totCnt = reDao.totCnt(fbNumber);
		request.setAttribute("fbDto", dto);
		request.setAttribute("pageNum",pageNum);
		request.setAttribute("fbreply", reDto);
		request.setAttribute("totCnt", totCnt);
	}
}
