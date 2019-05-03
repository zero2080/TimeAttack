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
import com.portfolio.dao.NoticeDao;
import com.portfolio.dto.NoticeDto;

public class NtWrite implements TAservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("fileUpload");
		int maxSize = 1024 * 1024 * 10;
		MultipartRequest mRequest = null;
		String nFile = "";
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());

			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			nFile = mRequest.getFilesystemName(param);

			String aId = mRequest.getParameter("aId");
			String nTitle = mRequest.getParameter("nTitle");
			String nContent = mRequest.getParameter("nContent");

			String nNum = mRequest.getParameter("nNumber");
			String nGro = mRequest.getParameter("nGroup");
			String nSt = mRequest.getParameter("nStep");
			String nL = mRequest.getParameter("nLv");

			int nNumber = nNum == null ? 0 : "".equals(nNum) ? 0 : Integer.parseInt(nNum);
			int nGroup = nGro == null ? 0 : "".equals(nGro) ? 0 : Integer.parseInt(nGro);
			int nStep = nSt == null ? 0 : "".equals(nSt) ? 0 : Integer.parseInt(nSt);
			int nLv = nL == null ? 0 : "".equals(nL) ? 0 : Integer.parseInt(nL);

			Date nRdate = null;

			if ("".equals(nFile) || nFile == null) {
				nFile = "NOIMG.PNG";
			}
			System.out.println(nNumber + " / " + aId + " / " + nTitle + " / " + nContent + " / "
					+ nFile + " / " + nRdate + " / " + nGroup + " / " + nStep + " / " + nLv);
			NoticeDto dto = new NoticeDto(nNumber, aId, nTitle, nContent, nFile, 0, nRdate, nGroup, nStep, nLv);
			NoticeDao dao = NoticeDao.getInstance();

			int result = dao.writeBoard(dto);
			request.setAttribute("writeResult", result);
		} catch (Exception e) {
			System.out.println("Ntimage error : " + e.getMessage());
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
