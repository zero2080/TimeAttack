package com.portfolio.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.portfolio.dao.TimeTableDao;

public class RecordConfirm implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int tStatus = Integer.parseInt(request.getParameter("tStatus"));
		int tNumber = Integer.parseInt(request.getParameter("tNumber"));
		int result = 0;
		
		TimeTableDao dao = TimeTableDao.getInstance();
		result=dao.recordConfirm(tNumber,tStatus);
		request.setAttribute("confirmMsg", (tStatus==3?"수정요청":tStatus==2?"등록승인":"보류")+(result==1?"완료":"실패"));
	}

}
