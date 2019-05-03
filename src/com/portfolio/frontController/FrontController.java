package com.portfolio.frontController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.portfolio.service.ALoginService;
import com.portfolio.service.DetailGameView;
import com.portfolio.service.FBContentView;
import com.portfolio.service.FBRePly;
import com.portfolio.service.FBReWrite;
import com.portfolio.service.FBReWriteView;
import com.portfolio.service.FBWrite;
import com.portfolio.service.FBWriteView;
import com.portfolio.service.FBdelete;
import com.portfolio.service.FreeBoardList;
import com.portfolio.service.GameList;
import com.portfolio.service.GameManagerView;
import com.portfolio.service.GameRecordConfirm;
import com.portfolio.service.GameRecordList;
import com.portfolio.service.GameRecordView;
import com.portfolio.service.GameRegist;
import com.portfolio.service.IdConfirm;
import com.portfolio.service.MJoinService;
import com.portfolio.service.MLoginService;
import com.portfolio.service.MLogoutService;
import com.portfolio.service.MModify;
import com.portfolio.service.MemberManagetMentService;
import com.portfolio.service.NTWriteView;
import com.portfolio.service.NoticeListView;
import com.portfolio.service.NtContentView;
import com.portfolio.service.NtReWrite;
import com.portfolio.service.NtWrite;
import com.portfolio.service.Ntdelete;
import com.portfolio.service.PersonalRecordManager;
import com.portfolio.service.PersonalRecordReply;
import com.portfolio.service.PersonalRecordView;
import com.portfolio.service.QuitMember;
import com.portfolio.service.RecordConfirm;
import com.portfolio.service.RecordModify;
import com.portfolio.service.RecordModifyPro;
import com.portfolio.service.RecordRegist;
import com.portfolio.service.SelectedStatus;
import com.portfolio.service.TAservice;
import com.portfolio.service.mnicknameConfirm;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public FrontController() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		String viewPage = null;
		TAservice service = null;
		
		if(com.equals("/main.do")) {
			viewPage="main/main.jsp";
		}
		if(com.equals("/loginForm.do")) {
			if(session.getAttribute("member")==null) {
				viewPage="join/loginForm.jsp";
			}else {
				viewPage="main.do";
			}
		}
		if(com.equals("/loginPro.do")){
			service = new MLoginService();
			service.execute(request, response);
			viewPage="main.do";
		}
		if(com.equals("/loginOut.do")){
			service = new MLogoutService();
			service.execute(request, response);
			viewPage="main.do";
		}
		if(com.equals("/myInfo.do")) {
			viewPage = "join/modify.jsp";
		}
		if(com.equals("/memberModify.do")) {
			viewPage = "join/member_modify.jsp";
		}
		if(com.equals("/modifyPro.do")) {
			service = new MModify();
			service.execute(request, response);
			viewPage = "main.do";
		}
		if(com.equals("/quitMember.do")) {
			service = new QuitMember();
			service.execute(request, response);
			viewPage = "main.do";
		}
		
		if(com.equals("/adminLogin.do")) {
			viewPage="join/adminLoginForm.jsp";
		}
		if(com.equals("/adminLoginPro.do")) {
			service = new ALoginService();
			service.execute(request, response);
			viewPage="main.do";
		}
		if(com.equals("/adminLogout.do")) {
			session.invalidate();
			viewPage="main.do";
		}
		if(com.equals("/memberManagement.do")) {
			service = new MemberManagetMentService();
			service.execute(request, response);
			viewPage = "admin/memberManager.jsp";
		}
		if(com.contentEquals("/gameRecodManagement.do")){
			service = new GameManagerView();
			service.execute(request, response);
			viewPage = "gameList/gameList.jsp";
		}
		
		if(com.equals("/gameManagemnet.do")) {
			viewPage = "gameList/gameMain.jsp";
		}
		if(com.equals("/newGameView.do")) {
			viewPage="gameList/newGame.jsp";
		}
		if(com.equals("/gameRegi.do")) {
			service = new GameRegist();
			service.execute(request, response);
			viewPage = "gameList/answer.jsp";
		}
		if(com.equals("/recordList.do")) {
			service = new GameRecordList();
			service.execute(request, response);
			viewPage="gameList/recordList.jsp";
		}
		if(com.equals("/recordConfirmView.do")) {
			service = new GameRecordConfirm();
			service.execute(request, response);
			viewPage="gameList/recordConfirmView.jsp";
		}
		if(com.equals("/confirm.do")) {
			service = new RecordConfirm();
			service.execute(request, response);
			viewPage="gameList/recordConfirm.jsp";
		}
		if(com.equals("/selectedStatus.do")) {
			service = new SelectedStatus();
			service.execute(request, response);
			viewPage="gameList/selectedStatus.jsp";
		}
		if(com.equals("/gameList.do")) {
			service = new GameList();
			service.execute(request, response);
			viewPage="gameService/gameList.jsp";
		}
		if(com.equals("/detailGame.do")) {
			service = new DetailGameView();
			service.execute(request, response);
			viewPage="gameService/detailView.jsp";
		}
		if(com.equals("/gameRecordView.do")) {
			service = new GameRecordView();
			service.execute(request, response);
			viewPage = "gameService/gamerecordView.jsp";
		}
		if(com.equals("/regiRecordView.do")) {
			viewPage = "gameService/regiRecordView.jsp";
		}
		if(com.equals("/recordRegistration.do")) {
			service = new RecordRegist();
			service.execute(request, response);
			viewPage = "/regiRecordView.do";
		}
		if(com.equals("/personRecordView.do")) {
			service = new PersonalRecordView();
			service.execute(request, response);
			viewPage = "gameService/personRecordView.jsp";
		}
		if(com.equals("/ttrePly.do")) {
			service = new PersonalRecordReply();
			service.execute(request, response);
			viewPage = "gameService/personalRecordReply.jsp";
		}
		if(com.equals("/recordManager.do")) {
			service = new PersonalRecordManager();
			service.execute(request, response);
			viewPage = "gameService/personalRecord.jsp";
		}
		if(com.equals("/recordModify.do")) {
			service = new RecordModify();
			service.execute(request, response);
			viewPage = "gameService/recordModify.jsp";
			}
		if(com.equals("/recordModifyPro.do")) {
			service = new RecordModifyPro();
			service.execute(request, response);
			viewPage = "gameService/recordModifyPro.jsp";
		}
		
		if(com.equals("/joinForm.do")) {
			viewPage=session.getAttribute("member")==null?"join/joinForm.jsp":"main.do";
		}
		if(com.equals("/idConfirm.do")){
			service = new IdConfirm();
			service.execute(request, response);
			viewPage="join/idConfirm.jsp";
		}
		if(com.equals("/mnicknameConfirm.do")){
			service = new mnicknameConfirm();
			service.execute(request, response);
			viewPage="join/mnicknameConfirm.jsp";
		}
		if(com.equals("/joinPro.do")) {
			service = new MJoinService();
			service.execute(request, response);
			viewPage="join/welcome.jsp";
		}
		if(com.equals("/freeBoard.do")) {
			service = new FreeBoardList();
			service.execute(request, response);
			viewPage="freeBoard/freeBoardList.jsp";
		}
		if(com.equals("/content_view.do")) {
			service = new FBContentView();
			service.execute(request, response);
			viewPage="freeBoard/contentView.jsp";
		}
		if(com.equals("/fbWriteView.do")) {
			service = new FBWriteView();
			service.execute(request, response);
			viewPage = "freeBoard/fbWriteView.jsp";
		}
		if(com.equals("/fb_write.do")) {
			service = new FBWrite();
			service.execute(request, response);
			viewPage = "main.do";
		}
		if(com.equals("/rePly.do")) {
			service = new FBRePly();
			service.execute(request, response);
			String pageNum = request.getParameter("pageNum");
			String fbNumber = request.getParameter("fbNumber");
			viewPage="content_view.do?fbNumber="+fbNumber+"&pageNum="+pageNum;
		}
		if(com.equals("/fbReWriteView.do")) {
			service = new FBReWriteView();
			service.execute(request, response);
			viewPage="freeBoard/fbReWriteView.jsp";
		}
		if(com.equals("/fb_reWrite.do")) {
			service = new FBReWrite();
			service.execute(request, response);
			String pageNum = (String)session.getAttribute("pageNum");
			String fbNumber = (String)session.getAttribute("fbNumber");
			
			viewPage="content_view.do?fbNumber="+fbNumber+"&pageNum="+pageNum;
		}
		if(com.equals("/fb_delete.do")) {
			service = new FBdelete();
			service.execute(request, response);
			viewPage="freeBoard.do";
		}
		
		if(com.equals("/notice.do")) {
			service = new NoticeListView();
			service.execute(request, response);
			viewPage ="notice/noticeList.jsp";
		}
		if(com.equals("/nContent_view.do")) {
			service = new NtContentView();
			service.execute(request, response);
			viewPage = "notice/ntContentView.jsp";
		}
		if(com.equals("/n_delete.do")) {
			service = new Ntdelete();
			service.execute(request, response);
			viewPage="notice.do";
		}
		if(com.equals("/nWriteView.do")) {
			service = new NTWriteView();
			service.execute(request, response);
			viewPage = "notice/ntWriteView.jsp";
		}
		if(com.equals("/n_reWrite.do")) {
			service = new NtReWrite();
			service.execute(request, response);
			String pageNum = (String)session.getAttribute("pageNum");
			String fbNumber = (String)session.getAttribute("fbNumber");
			
			viewPage="ntContent_view.do?fbNumber="+fbNumber+"&pageNum="+pageNum;
		}
		if(com.equals("/n_write.do")) {
			service = new NtWrite();
			service.execute(request, response);
			viewPage = "main.do";
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
