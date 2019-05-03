package com.portfolio.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.portfolio.dao.NoticeDao;

public class Ntdelete implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int nNumber = Integer.parseInt(request.getParameter("nNumber"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		NoticeDao dao = NoticeDao.getInstance();
		int result = dao.delContent(nNumber);
		request.setAttribute("result", result);
	}

}
