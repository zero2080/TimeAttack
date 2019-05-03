package com.portfolio.service;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.portfolio.dao.GameListDao;
import com.portfolio.dao.TimeTableDao;
import com.portfolio.dto.GameListDto;
import com.portfolio.dto.TimeTableDto;

public class DetailGameView implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int gNumber = Integer.parseInt(request.getParameter("gNumber"));
		GameListDao dao = GameListDao.getInstance();
		GameListDto dto = dao.searchGame(gNumber);
		/***************************플랫폼*********************************/
		StringTokenizer pf = new StringTokenizer(dto.getgPlatform(), ",");
		String platform[] = new String[pf.countTokens()];
		int i = 0;
		while(pf.hasMoreElements()) {
			platform[i] = pf.nextToken();
			i++;
		}
		
		/****************************난이도********************************/
		StringTokenizer df = new StringTokenizer(dto.getgDifficulty(),",");
		String difficulty[] = new String[df.countTokens()]; 
		i = 0;
		while(df.hasMoreElements()) {
			difficulty[i] = df.nextToken();
			i++;
		}
		/******************************모드********************************/
		StringTokenizer md = new StringTokenizer(dto.getgMode(),",");
		String mode[] = new String[md.countTokens()];
		i = 0;
		while(md.hasMoreElements()) {
			mode[i] = md.nextToken();
			i++;
		}
		
		
		
		/******************게임기록 로딩******************/
		TimeTableDao tdao = TimeTableDao.getInstance();
		ArrayList<TimeTableDto> records = tdao.oneGamett(gNumber);
		
		request.setAttribute("records", records);
		request.setAttribute("platform", platform);
		request.setAttribute("diffs", difficulty);
		request.setAttribute("modes", mode);
		request.setAttribute("game", dto);
	}
}
