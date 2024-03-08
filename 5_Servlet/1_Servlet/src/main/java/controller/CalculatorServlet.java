package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalculatorServlet
 */
@WebServlet("/calc_Servlet")
public class CalculatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculatorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET 방식 요청 들어옴!");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		String fv = request.getParameter("first_value");
		String sv = request.getParameter("second_value");
		String calc = request.getParameter("calc");
		double fvv = Double.parseDouble(fv);
		double svv = Double.parseDouble(sv);
		double result = 0;
		
		if(calc.equals("+")) {
			result = fvv+svv;
		} else if(calc.equals("-")) {
			result = fvv-svv;
		} else if(calc.equals("*")) {
			result = fvv*svv;
		} else if(calc.equals("/")) {
			if(svv == 0) {
				pw.println("0으로 나눌 수 있겠냐? 멍청아");
			} else {
				result = fvv/svv;
			}
		}
		pw.println("<h1>계산기 서블릿<h1>"
				+ "<hr>" +"계산 결과 :" +result);
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
