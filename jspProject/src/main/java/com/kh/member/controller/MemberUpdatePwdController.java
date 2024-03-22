package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.vo.Member;
import com.kh.member.service.MemberService;

/**
 * Servlet implementation class MemberUpdatePwdController
 */
@WebServlet("/updatePwd.me")
public class MemberUpdatePwdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdatePwdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) POST 요청인 경우 인코딩 설정
		request.setCharacterEncoding("utf-8");
		
		// 2) 전달되는 데이터를 뽑아오기
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String updatePwd = request.getParameter("updatePwd");
		
		// 3) 서비스에게 뽑아온 데이터를 전달하여 유저의 비밀번호를 변경
		Member updateMem = new MemberService().updatePassword(userId, userPwd, updatePwd);
		
		// 4) 결과에 따라 성공한 경우 변경된 유저 정보와 성공 메시지를 포함하여 마이페이지를 응답
		//				실패한 경우 실패 메시지를 포함하여 에러페이지를 응답
		if(updateMem != null) {
			// 비밀번호 변경 성공 시
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", updateMem);
			session.setAttribute("alertMsg", "비밀번호 변경 성공!");
			response.sendRedirect(request.getContextPath()+"/myPage.me");
		} else {
			// 비밀번호 변경 실패 시
			request.setAttribute("errorMsg", "비밀번호 변경에 실패했습니다.");
			
			// 에러페이지로 응답
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request, response);
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
