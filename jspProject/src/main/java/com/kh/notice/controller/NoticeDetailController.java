package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.vo.Notice;
import com.kh.notice.service.NoticeService;

/**
 * Servlet implementation class NoticeDetailController
 */
@WebServlet("/detail.no")
public class NoticeDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 인코딩 설정 (POST)
		// 2) 데이터 추출
		int noticeNo = Integer.parseInt(request.getParameter("num"));
		
		// 3) 요청 처리 => 조회수를 먼저 증가, 공지사항 내용을 조회
		Notice n = new NoticeService().selectDetailNotice(noticeNo);
		// 조회된 결과가 있을 경우 => 공지사항 내용을 담은 객체(Notice)
		//			  없을 경우 => null
		
		// 4) 응답 - 공지사항 상세페이지를 응답, 상세 내용 포함
		if (n != null) {	// 조회수 증가 및 공지사항 내용 조회 성공
			
			request.setAttribute("notice", n);
			request.getRequestDispatcher("views/notice/noticeDetailView.jsp").forward(request, response);
		} else {		// 실패
			request.setAttribute("errorMsg", "공지사항 조회에 실패했습니다.");
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
