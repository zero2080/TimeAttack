package com.portfolio.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.portfolio.dao.FreeBoardDao;

public class FBdelete implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int fbNumber = Integer.parseInt(request.getParameter("fbNumber"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		FreeBoardDao dao = FreeBoardDao.getInstance();
		int result = dao.delContent(fbNumber);
		request.setAttribute("result", result);
	}

}
