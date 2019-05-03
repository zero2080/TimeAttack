package com.portfolio.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.portfolio.dao.TimeTableDao;

public class RecordModifyPro implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String mId = request.getParameter("mId");
		System.out.println(mId);
		int tNumber = Integer.parseInt(request.getParameter("tNumber"));
		System.out.println(tNumber);
		String tPlatform = request.getParameter("tPlatform");
		String tDifficulty = request.getParameter("tDifficulty");
		String tMode = request.getParameter("tMode");
		String tTmst = request.getParameter("tTmst");
		String tLink = request.getParameter("tLink");
		
		System.out.println("�������?");
		System.out.println(mId+tNumber+tPlatform+tDifficulty+tMode+tTmst);
		TimeTableDao dao = TimeTableDao.getInstance();
		
		
		int result = dao.modifyRecord(mId, tNumber, tPlatform, tDifficulty, tMode, tTmst, tLink);
		System.out.println(result==1?"수정성공":"수정실패");
	}

}
