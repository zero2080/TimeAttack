package com.portfolio.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.portfolio.dao.FreeBoardDao;
import com.portfolio.dto.FreeBoardDto;

public class FBReWriteView implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		FreeBoardDao dao = FreeBoardDao.getInstance();
		FreeBoardDto dto = dao.viewContent(Integer.parseInt(request.getParameter("fbNumber")));
		HttpSession session = request.getSession();
		session.setAttribute("reWrite", dto);
	}

}
