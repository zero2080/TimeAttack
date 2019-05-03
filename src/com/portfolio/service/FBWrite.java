package com.portfolio.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.portfolio.dao.FreeBoardDao;
import com.portfolio.dto.FreeBoardDto;

public class FBWrite implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String fbIp = request.getRemoteAddr();
		
		String path = request.getRealPath("fileUpload");
		int maxSize = 1024*1024*10;
		MultipartRequest mRequest = null;
		String fbFile= "";
		try {
			mRequest = new MultipartRequest(request,path,maxSize,"utf-8",new DefaultFileRenamePolicy());
			
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			fbFile = mRequest.getFilesystemName(param);
			
			String mId = mRequest.getParameter("mId");
			String mNickName = mRequest.getParameter("mNickName");
			String fbTitle = mRequest.getParameter("fbTitle");
			String fbContent = mRequest.getParameter("fbContent");
			
			String fbNum = mRequest.getParameter("fbNumber");
			String fbGro = mRequest.getParameter("fbGroup");
			String fbSt = mRequest.getParameter("fbStep");
			String fbL = mRequest.getParameter("fbLv");
			
			int fbNumber = fbNum==null?0:"".equals(fbNum)?0:Integer.parseInt(fbNum);
			int fbGroup = fbGro==null?0:"".equals(fbGro)?0:Integer.parseInt(fbGro);
			int fbStep = fbSt==null?0:"".equals(fbSt)?0:Integer.parseInt(fbSt);
			int fbLv = fbL==null?0:"".equals(fbL)?0:Integer.parseInt(fbL);
			
			Date fbRdate = null;
			
			if("".equals(fbFile) || fbFile==null) {
				fbFile="NOIMG.PNG";
			}
			System.out.println(fbNumber+" / "+ mId+" / "+ mNickName+" / "+ fbTitle+" / "+ fbContent+" / "+ fbFile+" / "+ fbRdate+" / " + fbIp+" / "+ fbGroup+" / "+ fbStep+" / "+ fbLv);
			FreeBoardDto dto = new FreeBoardDto(fbNumber, mId, mNickName, fbTitle, fbContent, fbFile, fbRdate, 0, fbIp, fbGroup, fbStep, fbLv);
			FreeBoardDao dao = FreeBoardDao.getInstance();
			
			int result = dao.writeBoard(dto);
			request.setAttribute("writeResult", result);
		}catch(Exception e) {
			System.out.println("FBimage error : "+e.getMessage());
		}
		
		File serverFile = new File(path+"/"+fbFile);
		if(serverFile.exists()) {
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("D:/mega_it/source/8_portfolio/TimeAttack/WebContent/fileUpload/"+fbFile);
				byte[] bs = new byte[(int)serverFile.length()];
				while(true) {
					int nByteCnt = is.read(bs);
					if(nByteCnt == -1) {
						break;
					}
					os.write(bs,0,nByteCnt);
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
	}

}
