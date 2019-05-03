package com.portfolio.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.portfolio.dao.TimeTableDao;
import com.portfolio.dto.TimeTableDto;

public class SelectedStatus implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int tStatus = Integer.parseInt(request.getParameter("tStatus"));
		TimeTableDao dao = TimeTableDao.getInstance();
		ArrayList<TimeTableDto> tts = new ArrayList<TimeTableDto>();
		tts = dao.getStatus(tStatus);
		request.setAttribute("tStatus", tts);
		System.out.println(tts.toString());
	}

}
