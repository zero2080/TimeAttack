package com.portfolio.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.portfolio.dao.GameListDao;
import com.portfolio.dto.GameListDto;

public class GameRegist implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("fileUpload");
		int maxSize = 1024*1024*10;
		MultipartRequest mRequest = null;
		String gImg= "";
		try {
			mRequest = new MultipartRequest(request,path,maxSize,"utf-8",new DefaultFileRenamePolicy());
			
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			gImg = mRequest.getFilesystemName(param);
			
			String aId = mRequest.getParameter("aid");
			String gTitle = mRequest.getParameter("gTitle");
			String gPf[]= mRequest.getParameterValues("gPlatform");
			String gPlatform = "";
			for(int i =0 ; i<gPf.length;i++) {
				if(i==0) {
					gPlatform=gPf[i];
				}else {
					gPlatform+=","+gPf[i];
				}
			}
			
			String gGenre = mRequest.getParameter("gGenre");
			String gGenre2 = mRequest.getParameter("gGenre2");
			String gDifficulty = mRequest.getParameter("gDifficulty");
			String gCorp = mRequest.getParameter("gCorp");
			String gMode = mRequest.getParameter("gMode");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String gD = mRequest.getParameter("gDate");
			Date gDate = Date.valueOf(gD);
			
			int gNumber = mRequest.getParameter("gNumber")==null?0:Integer.parseInt(mRequest.getParameter("gNumber"));
			if("".equals(gImg) || gImg==null) {
				gImg="NOIMG.PNG";
			}
			Date gRdate = null;
			GameListDto dto = new GameListDto(gNumber, gPlatform, aId, gTitle, gGenre, gGenre2,	gImg, gDifficulty, gCorp, gMode, gRdate, gDate);
			GameListDao dao = GameListDao.getInstance();
			
			int result = dao.regGame(dto);
			request.setAttribute("resultMSG", result);
			System.out.println(result==GameListDao.SUCCESS?"등록성공":"등록실패");
		}catch(Exception e) {
			System.out.println("GameRegist error : "+e.getMessage());
		}
		
		File serverFile = new File(path+"/"+gImg);
		if(serverFile.exists()) {
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("D:/mega_it/source/8_portfolio/TimeAttack/WebContent/gameImg/"+gImg);
				byte[] bs = new byte[(int)serverFile.length()];
				while(true) {
					int nByteCnt = is.read(bs);
					if(nByteCnt == -1) {
						break;
					}
					os.write(bs,0,nByteCnt);
				}
			}catch(Exception e) {
				System.out.println("GameRegist file copy error : "+e.getMessage());
			}
		}
	}
}
