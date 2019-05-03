package com.portfolio.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.portfolio.dao.TimeTableDao;
import com.portfolio.dto.MemberDto;
import com.portfolio.dto.TimeTableDto;

public class PersonalRecordManager implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto)session.getAttribute("member");
		String mId = member.getMid();
		
		TimeTableDao dao = TimeTableDao.getInstance();
		ArrayList<TimeTableDto> records = dao.personalRecord(mId);
		
		
		
		request.setAttribute("records", records);
	}

}
