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
import com.portfolio.dao.NoticeDao;
import com.portfolio.dto.NoticeDto;

public class NtReWrite implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		String path = request.getRealPath("fileUpload");
		int maxSize = 1024 * 1024 * 10;
		MultipartRequest mRequest = null;
		String nFile = "";
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());

			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			nFile = mRequest.getFilesystemName(param);

			String nTitle = mRequest.getParameter("nTitle");
			String nContent = mRequest.getParameter("nContent");
			String pageNum = mRequest.getParameter("pageNum");

			String nNum = mRequest.getParameter("nNumber");
			System.out.println("FBReWrite : " + pageNum + "/" + nNum);
			if ("".equals(nFile) || nFile == null) {
				nFile = "NOIMG.PNG";
			}
			NoticeDto dto = new NoticeDto();
			dto.setnNumber(Integer.parseInt(nNum));
			dto.setnTitle(nTitle);
			dto.setnContent(nContent);
			dto.setnFile(nFile);
			NoticeDao dao = NoticeDao.getInstance();
			session.setAttribute("pageNum", pageNum);
			session.setAttribute("ntNumber", nNum);

			int result = dao.updateContent(dto);
			System.out.println(result == 1 ? "수정성공" : "수정실패");
		} catch (Exception e) {
			System.out.println("FBimage error : " + e.getMessage());
		}

		File serverFile = new File(path + "/" + nFile);
		if (serverFile.exists()) {
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("D:/mega_it/source/8_portfolio/TimeAttack/WebContent/fileUpload/" + nFile);
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
