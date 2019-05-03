package com.portfolio.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.portfolio.dao.TimeTableDao;
import com.portfolio.dto.TimeTableDto;

public class GameRecordView implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int gNumber = Integer.parseInt(request.getParameter("gNumber"));
		String tPlatform = request.getParameter("tPlatform");
		String tDifficulty = request.getParameter("tDifficulty");
		String tMode = request.getParameter("tMode");
		
		ArrayList<TimeTableDto> tts = new ArrayList<TimeTableDto>();
		TimeTableDao dao = TimeTableDao.getInstance();
		tts=dao.getSelRecord(tPlatform, tMode, tDifficulty, gNumber);
		
		request.setAttribute("records", tts);
	}
}
