package com.portfolio.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.portfolio.dao.NoticeDao;
import com.portfolio.dto.NoticeDto;

public class NTWriteView implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if(request.getParameter("nNumber")!=null) {
			int nNumber = 0;
			nNumber = Integer.parseInt(request.getParameter("nNumber"));
			NoticeDao dao = NoticeDao.getInstance();
			NoticeDto dto = dao.viewContent(nNumber);
			session.setAttribute("dto", dto);
			System.out.println(nNumber+"번글 작성예정 / group : "+dto.getnGroup()+" / step : "+dto.getnStep()+" / level : "+dto.getnLv());
		}
	}
}
