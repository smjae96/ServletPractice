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
 * Servlet implementation class UpdateController
 */
@WebServlet("/update.me")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// 2) 전달되는 데이터를 뽑아내기 (회원 정보를 회원의 Id를 기준으로 변경함)
		String userId = request.getParameter("userId");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String[] interestList = request.getParameterValues("interest");
		
		String interest = null;
		
		if(interestList != null) {
			interest = String.join(",", interestList);
		}
		
		// 3) Member 객체를 생성하여 서비스에게 전달
		Member m = new Member(userId, phone, email, address, interest);
		
		//--- 요청 처리
		Member member = new MemberService().updateMember(m);
//		int result = new MemberService().updateMember(m);
		
		if(member != null) {
			// 회원 정보 변경 성공 => member의 값이 변경된 정보 => session에 변경된 정보로 업데이트
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", member);
			session.setAttribute("alertMsg", "회원정보가 수정되었습니다!");
			
			// 마이 페이지로 url 재요청
			response.sendRedirect(request.getContextPath()+"/myPage.me"); // "/jsp/myPage.me" url로 재요청
		} else {
			
			request.setAttribute("errorMsg", "회원정보 수정에 실패했습니다.");
			
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
