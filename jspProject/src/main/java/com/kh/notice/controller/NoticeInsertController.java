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
 * Servlet implementation class NoticeInsertController
 */
@WebServlet("/insert.no")
public class NoticeInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1) POST 요청 시 인코딩 설정(request 객체)
		request.setCharacterEncoding("utf-8");
		// 2) 요청 시 전달된 데이터를 뽑아내기. 로그인한 유저 정보 뽑아내기.
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		// * 로그인한 정보 추출하는 방법
		//  1) input:hidden 요소에 값을 저장해서 요청할 때 포함하여 전달
		//  2) session 객체로 부터 값을 꺼내오기
		HttpSession session = request.getSession();
		Member m = (Member)session.getAttribute("loginUser");
		int userNo = m.getUserNo();	// DB에 저장할 유저정보(Member테이블의 pk)
		
		// 3) 요청 처리 (Notice 객체를 서비스에 전달해서 공지사항을 DB에 저장하도록 처리)
		Notice notice = new Notice(title, content, String.valueOf(userNo));
		// 추가 (insert) -> DML -> int
		int result = new NoticeService().insertNotice(notice);
		// 4) 결과에 따라 응답
		//		- 성공한 경우, "성공적으로 공지사항이 등록되었습니다." 메시지창을 띄워주고, 공지사항 목록페이지를 응답
		//		- 실패한 경우, "공지사항 등록에 실패했습니다." 라는 내용으로 에러페이지를 응답
		
		if(result != 0) {
			session.setAttribute("alertMsg", "성공적으로 공지사항이 등록되었습니다.");
			// "/jsp/list.no" 재요청
			response.sendRedirect(request.getContextPath()+"/list.no");
//			request.getRequestDispatcher("views/notice/noticeListView.jsp").forward(request, response);
			// request.getRequestDispatcher를 써서 포워딩 해줄 경우 list를 조회하지 않은 상태로 페이지로 이동하기 때문에 목록이 화면에 나타나지 않는다. list == null이기 때문!
			// 따라서 따로 NoticeInsertController에서 list를 조회해서 전달해주지 않을 거라면 sendRedirect를 통해 NoticeListViewController를 경유해서 가야 함!
		} else {
			request.setAttribute("errorMsg", "공지사항 등록에 실패했습니다.");
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
