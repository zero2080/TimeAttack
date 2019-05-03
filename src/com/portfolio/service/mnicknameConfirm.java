package com.portfolio.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.portfolio.dao.MemberDao;

public class mnicknameConfirm implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mNickName = request.getParameter("mnickname");
		MemberDao dao = MemberDao.getInstanse();
		int result = dao.nickNameChk(mNickName);
		if(result==MemberDao.EXIST) {
			request.setAttribute("mnicknameConfirmMsg", "중복된 닉네임입니다..");
			request.setAttribute("NickMsg", 0);
		}else {
			request.setAttribute("mnicknameConfirmMsg", "사용가능한 닉네임입니다.");
			request.setAttribute("NickMsg", 2);
		}
	}

}
