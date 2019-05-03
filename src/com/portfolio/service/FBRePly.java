package com.portfolio.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.portfolio.dao.FBReplyDao;
import com.portfolio.dto.FBReplyDto;
import com.portfolio.dto.MemberDto;

public class FBRePly implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto)session.getAttribute("member");
		
		int fbNumber = Integer.parseInt(request.getParameter("fbNumber"));
		String frContent = request.getParameter("frContent");
		
		FBReplyDto dto = new FBReplyDto();
		dto.setFbNumber(fbNumber);
		dto.setFrContent(frContent);
		dto.setmId(member.getMid());
		dto.setGrIp(request.getRemoteAddr());
		dto.setmNickName(member.getMnickName());
		
		FBReplyDao dao = FBReplyDao.getInstance();
		int result = dao.insertReply(dto);
		
	}
}
