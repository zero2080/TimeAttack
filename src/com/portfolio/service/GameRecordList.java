package com.portfolio.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.portfolio.dao.TimeTableDao;
import com.portfolio.dto.TimeTableDto;

public class GameRecordList implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int gNumber = Integer.parseInt(request.getParameter("gnumber"));
		TimeTableDao dao = TimeTableDao.getInstance();
		ArrayList<TimeTableDto> tts = dao.oneGamett(gNumber);
		
		request.setAttribute("tts", tts);
	}
}
