package com.portfolio.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.portfolio.dao.MemberDao;
import com.portfolio.dto.MemberDto;

public class MemberManagetMentService implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<MemberDto> members = new ArrayList<MemberDto>();
		MemberDao dao = MemberDao.getInstanse();
		members = dao.getAllMembers();
		request.setAttribute("members", members);
	}

}
