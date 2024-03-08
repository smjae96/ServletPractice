package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestPostServlet
 */
@WebServlet("/test2.do")
public class RequestPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet 메소드 실행");
		
		// 요청 시 전달된 값들은 request의 parameter 영역에 있음
		
		// POST 방식 요청의 경우 데이터를 처리(확인)하기 "전"에 인코딩 설정이 필요!
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");		// "홍길동" | ""
		String gender = request.getParameter("gender"); // "남"/ "여" | null
		int age = Integer.parseInt(request.getParameter("age")); // "20" -> 20 | ""
		String address = request.getParameter("address");	// "서울" | "경기" ..
		double height = Double.parseDouble(request.getParameter("height"));	// "160" -> 160.0 ..
		String[] foods = request.getParameterValues("food");	// ["한식", "중식", ...] | null
		
		System.out.println("name : " +name);
		System.out.println("gender : " +gender);
		System.out.println("age : " + age);
		System.out.println("address : " +address);
		System.out.println("height : " +height);
		if(foods== null) {
			System.out.println("foods : 선택항목 없음" );
		} else {
			System.out.println("foods : " + String.join("-", foods));
		}
		
		// -----------------------------------------------------------------
		
		// 요청 처리 : > Service > Dao > DB 처리
		
		// 요청에 대한 처리가 완료되었다고 가정하고 응답페이지 작성
		
		// 순수 servlet 방식 : Java 코드 내에 html 작성
		// JSP (Java Server Page) 방식 : html 내에 Java 코드 작성
		
		// 응답 페이지를 만드는 과정을 jsp에게 맡겨보자.
		
		// 응답 페이지에서 필요한 데이터를 정리하여 전달 (forward 전에 전달!)
		// -> 전달하는 공간 : request 객체의 attribute라는 공간을 사용 --> attribute는 JSP만의 특별한 공간이라고 생각하자
		//					request.setAttribute("키값", "밸류값"); // 두번째 매개변수로 들어가는 "밸류값"은 Object 타입이므로 어떤 자료형이 오더라도 상관없음.(다형성)
		request.setAttribute("name", name);
		request.setAttribute("gender", gender);
		request.setAttribute("age", age);
		request.setAttribute("address", address);
		request.setAttribute("height", height);
		request.setAttribute("foods", foods);
		
		// jsp 페이지로 응답페이지 설정 : request.getRequestDispatcher(응답하고자하는_페이지_경로);
		RequestDispatcher view = request.getRequestDispatcher("views/responsePage.jsp");
		view.forward(request, response);
//		response.setContentType("text/html;charset=UTF-8");

		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
		 * 서블릿은 REST API 구현 전 버젼으로 단순히 서블릿 구현인 경우 GET, POST를 내부적으로 동일하게 처리
		 * GET, POST를 구분해서 처리하는 경우도 별도로 코드 작성은 가능
		 */
		System.out.println("doPost 메소드 실행!");

		doGet(request, response);
	}

}
