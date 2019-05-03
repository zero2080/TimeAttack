package com.portfolio.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.portfolio.dao.MemberDao;

public class IdConfirm implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mid = request.getParameter("mid");
		MemberDao dao = MemberDao.getInstanse();
		int result = dao.idChk(mid);
		if(result==MemberDao.EXIST) {
			request.setAttribute("idConfirmMsg", "중복된 ID입니다.");
			request.setAttribute("IdMsg", 0);
		}else {
			request.setAttribute("idConfirmMsg", "사용가능한 ID입니다.");
			request.setAttribute("IdMsg", 1);
		}
	}

}
