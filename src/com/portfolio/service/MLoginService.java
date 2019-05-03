package com.portfolio.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.portfolio.dao.MemberDao;
import com.portfolio.dto.MemberDto;

public class MLoginService implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		MemberDto member = null;
		MemberDao dao = MemberDao.getInstanse();
		member = dao.memberLogin(mid, mpw);
		HttpSession session = request.getSession();
		
		session.setAttribute("member", member);
	}

}
