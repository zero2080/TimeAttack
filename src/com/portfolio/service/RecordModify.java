package com.portfolio.service;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.portfolio.dao.GameListDao;
import com.portfolio.dao.TimeTableDao;
import com.portfolio.dto.GameListDto;
import com.portfolio.dto.TimeTableDto;

public class RecordModify implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int tNumber = Integer.parseInt(request.getParameter("tNumber"));
		
		TimeTableDao dao = TimeTableDao.getInstance();
		TimeTableDto record = dao.getOneRecord(tNumber);
		GameListDao gDao = GameListDao.getInstance();
		GameListDto game = gDao.searchGame(record.getgNumber());
		
		StringTokenizer pf = new StringTokenizer(game.getgPlatform(), ",");
		String platform[] = new String[pf.countTokens()];
		int i = 0;
		while(pf.hasMoreElements()) {
			platform[i] = pf.nextToken();
			i++;
		}
		
		/****************************난이도********************************/
		StringTokenizer df = new StringTokenizer(game.getgDifficulty(),",");
		String difficulty[] = new String[df.countTokens()]; 
		i = 0;
		while(df.hasMoreElements()) {
			difficulty[i] = df.nextToken();
			i++;
		}
		/******************************모드********************************/
		StringTokenizer md = new StringTokenizer(game.getgMode(),",");
		String mode[] = new String[md.countTokens()];
		i = 0;
		while(md.hasMoreElements()) {
			mode[i] = md.nextToken();
			i++;
		}
		
		request.setAttribute("records", record);
		request.setAttribute("platform", platform);
		request.setAttribute("diffs", difficulty);
		request.setAttribute("modes", mode);
		request.setAttribute("game", game);
	}

}
