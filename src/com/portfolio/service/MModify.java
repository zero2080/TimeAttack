package com.portfolio.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.portfolio.dao.MemberDao;
import com.portfolio.dto.MemberDto;

public class MModify implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto member = new MemberDto();
		
		member.setMid(request.getParameter("mid"));
		member.setMpw(request.getParameter("mpw"));
		member.setMname(request.getParameter("mname"));
		member.setMnickName(request.getParameter("mnickname"));
		member.setMemail(request.getParameter("memail"));
		MemberDao dao = MemberDao.getInstanse();
		
		System.out.println("MModify : "+member.toString());
		
		int result = dao.updateInfo(member);
		session.setAttribute("modifyMsg", "Modify : "+(result==1?"SUCCESS":"FAIL"));

	}

}
