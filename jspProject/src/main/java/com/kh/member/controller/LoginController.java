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
 * Servlet implementation class LoginController
 */
@WebServlet("/login.me")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1) 전달 값이 있을 경우 인코딩 처리 (POST 방식)
		request.setCharacterEncoding("UTF-8");
		// 2) 요청 시 전달된 데이터를 변수 또는 객체에 저장
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		// 3) 서비스로 요청 (db에 sql문을 사용하여 데이터를 조회)
		//		=> 해당 서비스 메소드를 호출
		Member loginUser = new MemberService().loginMember(userId, userPwd);
		
		/**
		 * 응답 페이지에 전달할 데이터를 어딘가에 담아서 보냄. (담을 수 있는 공간 => JSP 내장 객체 4가지)
		 * 
		 *  1) application : 웹 어플리케이션 전역에서 다 꺼내어 사용할 수 있음.
		 *  2) session	   : 세션에 저장하면 직접 지우기 전까지/세션이 만료될 때까지 (서버가 멈추거나, 브라우저 종료)
		 *  				 어떤 jsp든, servlet이든 꺼내서 사용 가능
		 *  3) request	   : 해당 request 객체를 "포워딩한 응답 jsp에서만" 사용 가능
		 *  4) page		   : 해당 jsp에서만 사용 가능
		 *  
		 *  공통적으로 데이터를 담을 때는 객체명.setAttribute("키값", 데이터)
		 *  		데이터를 꺼낼 때는 객체명.getAttribute("키값") : Object 타입의 데이터 반환
		 *  		 데이터를 지울 때는 객체명.removeAttribute("키값")
		 *  
		 */
		
		// 4) 처리 결과에 따라 사용자에게 응답페이지를 포워딩 또는 url 전달(재요청)
		// 조회 결과가 없는 경우 => 로그인 실패 => 에러페이지로 응답
		if (loginUser == null) {
			request.setAttribute("errorMsg", "로그인에 실패했습니다.");
			
			// 응답페이지를 jsp를 통해 전달할 때 필요한 객체 (RequestDispatcher)
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			// request, response 객체 포워딩
			view.forward(request, response);
		} else {
			// 조회 결과가 있는 경우 => 로그인 성공
			
			// 로그인 정보를 session 객체에 담기. (필요한 곳에서 바로 꺼내서 사용하기 위해)
			// 서블릿에서 세션 객체에 접근하기 위해 request객체를 통해 세션을 얻어오기
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
			
			// 1) 포워딩 방식 => 요청한 url 그대로 노출
//			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
//			view.forward(request, response);
			
			// 2) url 재요청 방식 (해당 url에 대한 응답이 존재해야 함)
			// 	  응답 페이지 : index.jsp (/jsp 요청)
			response.sendRedirect(request.getContextPath());
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
