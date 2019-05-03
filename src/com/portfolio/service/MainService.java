package com.portfolio.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.portfolio.dto.MemberDto;

public class MainService implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		MemberDto dto = (MemberDto)session.getAttribute("member");
		if(dto!=null) {
			System.out.println("MainService : "+dto.toString());
		}
	}

}
