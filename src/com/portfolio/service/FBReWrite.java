package com.portfolio.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.portfolio.dao.FreeBoardDao;
import com.portfolio.dto.FreeBoardDto;

public class FBReWrite implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		String fbIp = request.getRemoteAddr();
		
		String path = request.getRealPath("fileUpload");
		int maxSize = 1024 * 1024 * 10;
		MultipartRequest mRequest = null;
		String fbFile = "";
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());

			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			fbFile = mRequest.getFilesystemName(param);

			String fbTitle = mRequest.getParameter("fbTitle");
			String fbContent = mRequest.getParameter("fbContent");
			String pageNum = mRequest.getParameter("pageNum");
			
			String fbNum = mRequest.getParameter("fbNumber");
			System.out.println("FBReWrite : "+pageNum +"/"+fbNum);
			if ("".equals(fbFile) || fbFile == null) {
				fbFile = "NOIMG.PNG";
			}
			FreeBoardDto dto = new FreeBoardDto();
			dto.setFbNumber(Integer.parseInt(fbNum));
			dto.setFbTitle(fbTitle);
			dto.setFbContent(fbContent);
			dto.setFbFile(fbFile);
			dto.setFbIp(fbIp);
			FreeBoardDao dao = FreeBoardDao.getInstance();
			session.setAttribute("pageNum", pageNum);
			session.setAttribute("fbNumber", fbNum);
			
			int result = dao.updateContent(dto);
			System.out.println(result==1?"수정성공":"수정실패");
		} catch (Exception e) {
			System.out.println("FBimage error : " + e.getMessage());
		}

		File serverFile = new File(path + "/" + fbFile);
		if (serverFile.exists()) {
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("D:/mega_it/source/8_portfolio/TimeAttack/WebContent/fileUpload/" + fbFile);
				byte[] bs = new byte[(int) serverFile.length()];
				while (true) {
					int nByteCnt = is.read(bs);
					if (nByteCnt == -1) {
						break;
					}
					os.write(bs, 0, nByteCnt);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
	}
}
