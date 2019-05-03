package com.portfolio.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.portfolio.dao.MemberDao;
import com.portfolio.dto.MemberDto;

public class QuitMember implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto)session.getAttribute("member");
		String mId = member.getMid();
		System.out.println(mId);
		MemberDao dao = MemberDao.getInstanse();
		int result = dao.selfquitMemeber(mId);
		System.out.println(result==1?"탈퇴성공":"탈퇴실패");
		request.setAttribute("memberQuit", result==1?"탈퇴성공":"탈퇴실패");
	}

}
