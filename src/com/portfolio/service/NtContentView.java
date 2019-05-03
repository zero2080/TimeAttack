package com.portfolio.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.portfolio.dao.NoticeDao;
import com.portfolio.dto.NoticeDto;

public class NtContentView implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int nNumber = Integer.parseInt(request.getParameter("nNumber"));
		String pageNum = request.getParameter("pageNum");
		NoticeDao dao = NoticeDao.getInstance();
		dao.readCountup(nNumber);
		NoticeDto dto = dao.viewContent(nNumber);
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum",pageNum);
	}
}
