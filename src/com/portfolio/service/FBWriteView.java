package com.portfolio.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.portfolio.dao.FreeBoardDao;
import com.portfolio.dto.FreeBoardDto;

public class FBWriteView implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if(request.getParameter("fbNumber")!=null) {
			int fbNumber = 0;
			fbNumber = Integer.parseInt(request.getParameter("fbNumber"));
			FreeBoardDao dao = FreeBoardDao.getInstance();
			FreeBoardDto dto = dao.viewContent(fbNumber);
			session.setAttribute("reply", dto);
			System.out.println(fbNumber+"번글 답변 예정 / group : "+dto.getFbGroup()+" / step : "+dto.getFbStep()+" / level : "+dto.getFbLv());
		}
	}
}
