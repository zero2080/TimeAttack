package com.portfolio.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.portfolio.dao.TimeTableDao;
import com.portfolio.dto.TimeTableDto;

public class GameRecordConfirm implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		TimeTableDao dao = TimeTableDao.getInstance();
		ArrayList<TimeTableDto> list = dao.recordGame();
		request.setAttribute("list", list);
		
	}

}
