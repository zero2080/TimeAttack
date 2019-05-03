package com.portfolio.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.portfolio.dao.TimeTableDao;

public class RecordRegist implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mId = request.getParameter("mId");
		String mNickName=request.getParameter("mNickName");
		int gNumber = Integer.parseInt(request.getParameter("gNumber"));
		String tPlatform = request.getParameter("tPlatform");
		String tDifficulty = request.getParameter("tDifficulty");
		String tMode = request.getParameter("tMode");
		String tTmst = request.getParameter("tTmst");
		String tLink = request.getParameter("tLink");
		
		TimeTableDao dao = TimeTableDao.getInstance();
		int result = dao.insertRecord(mId, mNickName, gNumber, tPlatform, tDifficulty, tMode, tTmst, tLink);
		request.setAttribute("result", result==1?"등록 신청 성공":"등록 신청 실패");
	}

}
