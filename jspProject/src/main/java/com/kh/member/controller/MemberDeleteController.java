package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.service.MemberService;

/**
 * Servlet implementation class MemberDeleteController
 */
@WebServlet("/delete.me")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		int result = new MemberService().deleteMember(userId, userPwd);
		HttpSession session = request.getSession();
		// 처리 결과에 따라 응답
		if(result != 0) {
			// 회원 탈퇴 성공했을 경우
			// 성공 메시지를 띄우기 위한 데이터
			session.setAttribute("alertMsg", "성공적으로 회원 탈퇴되었습니다. 그동안 이용해주셔서 감사합니다!");
			// 회원 정보를 세션에서 제거
			session.removeAttribute("loginUser");	
			// 메인페이지로 응답
			response.sendRedirect(request.getContextPath());	// "/jsp" url 재요청		
		} else {
			// 회원 탈퇴 실패했을 경우
			
			session.setAttribute("alertMsg", "회원 탈퇴에 실패했습니다.");
			response.sendRedirect(request.getContextPath()+ "/myPage.me");
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
