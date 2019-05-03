package com.portfolio.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.portfolio.dao.AdminDao;
import com.portfolio.dto.AdminDto;

public class ALoginService implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if(session.getAttribute("member")!=null) {
			session.invalidate();
		}
		String aid = request.getParameter("aid");
		String apw = request.getParameter("apw");
		AdminDao dao = AdminDao.getInstance();
		AdminDto admin = dao.aLogin(aid, apw);
		
		session.setAttribute("admin",admin);
	}

}
