package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.vo.Member;
import com.kh.notice.model.vo.Notice;
import com.kh.notice.service.NoticeService;

/**
 * Servlet implementation class NoticeUpdateController
 */
@WebServlet("/update.no")
public class NoticeUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 인코딩 설정 (Post 요청)
		request.setCharacterEncoding("utf-8");
		// 2) 전달된 데이터를 뽑아오기. 로그인한 유저 정보 뽑아오기. --> 유저 정보 필요없음.
		int noticeNo = Integer.parseInt(request.getParameter("num"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		HttpSession session = request.getSession();
//		Member m = (Member)session.getAttribute("loginUser");
//		int userNo = m.getUserNo();
		
		Notice notice = new Notice();
		notice.setNoticeNo(noticeNo);
		notice.setNoticeTitle(title);
		notice.setNoticeContent(content);
		
		// 3) 요청 처리 (수정 => UPDATE. DML)
		int result = new NoticeService().updateNotice(notice);
		// 4) 결과에 따라, 성공인 경우 해당 공지사항의 상세보기 페이지로 이동 ("성공적으로 공지사항이 변경되었습니다.")
		//				실패인 경우 에러페이지로 이동 ("공지사항 변경을 실패했습니다.")
		if(result != 0) {
			session.setAttribute("alertMsg", "성공적으로 공지사항이 변경되었습니다.");
//			request.getSession().setAttribute("alertMsg", "성공적으로 공지사항이 변경되었습니다.");
			response.sendRedirect(request.getContextPath()+"/detail.no?num=" +noticeNo);
		} else {
			request.setAttribute("errorMsg", "공지사항 변경을 실패했습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
