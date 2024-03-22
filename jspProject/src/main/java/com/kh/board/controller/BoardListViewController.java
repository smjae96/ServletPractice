package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.vo.Board;
import com.kh.board.service.BoardService;
import com.kh.common.model.PageInfo;

/**
 * Servlet implementation class BoardListViewController
 */
@WebServlet("/list.bo")
public class BoardListViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListViewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 일반 게시글 목록 조회
		
		// -- 페이징 처리 --
		int listCount;			// 전체 게시글 개수
		int currentPage;		// 현재 페이지 (사용자로부터 요청 시 전달될 값)
		int pageLimit;			// 페이지 하단에 표시될 페이징바의 최대 개수
		int boardLimit; 			// 한 페이지에 표시할 게시글의 최대 개수
		
		// 위의 4개의 데이터를 통해서 아래의 3개의 데이터를 추출
		int maxPage;		// 가장 마지막 페이지 (총 페이지 수)
		int startPage;		// 페이징 바의 시작번호
		int endPage;		// 페이징 바의 마지막번호
		
		// listCount ( 전체 게시글 개수 ) : DB에 저장된 데이터의 개수를 조회
		listCount = new BoardService().selectListCount();
		// currentPage (현재 페이지) : 요청 시 전달된 값에서 조회(사용자 요청)
		currentPage = Integer.parseInt(request.getParameter("cpage"));
		// pageLimt, boardLimt : 10
		pageLimit = 10;		// 페이징바의 최대 개수 (단위)
		boardLimit = 10;	// 한 페이지에 표시할 게시글 개수 (단위)
		
		/**
		 * * maxPage : 마지막 페이지 번호
		 * 
		 * listCount, boardLimit 데이터의 영향을 받음
		 * 
		 * ex) 게시글이 10개씩 표시되는 경우
		 * 
		 * listCount			boardLimit		maxPage
		 * 	 100					10			  10
		 * 	 101					10			  11
		 *   105					10			  11
		 *   110				    10			  11
		 *   
		 * ex) 게시글이 5개씩 표시되는 경우
		 * listCount			boardLimit				maxPage
		 * 	 10.0  		/		    5       =>  2.0		  2
		 * 	 11.0		/			5		=>  2.2		  3
		 * 	 14.0		/			5		=>  2.8		  3
		 * 
		 * 	총 게시글 개수 (실수) /	boardLimit => 올림처리		
		 */
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
		
		/**
		 * * startPage : 페이징 바의 시작번호
		 * 
		 * pageLimit, currentPage 데이터의 영향을 받음
		 * 
		 * ex) 페이징 바에 목록이 10개씩 표시된다면, 
		 * 		statrtPage => 1, 11, 21, 31, ...
		 *				   n * 10 + 1
		 *					=> n * pageLimit + 1
		 *
		 * currentPage		startPage
		 * 		1				 1		=> 0 * 10 + 1
		 * 		5				 1		=> 0 * 10 + 1
		 * 		10				 1		=> 0 * 10 + 1
		 * 
		 * 		11				11		=> 1 * 10 + 1
		 * 		15				11		=> 1 * 10 + 1
		 * 		20				11		=> 1 * 10 + 1
		 * 
		 * 	1 ~ 10 => n = 0
		 * 11 ~ 20 => n = 1
		 * 21 ~ 30 => n = 2
		 * ...
		 * currentPage - 1 => n* pageLimit
		 * (currentPage - 1) / pageLimit => n
		 * 
		 * startPage = n * pageLimit + 1
		 * 			= (currentPage - 1) / pageLimit * pageLimit + 1							
		 */
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		
		/**
		 * * endPage : 페이징바의 마지막번호
		 * 
		 * statrtPage, pageLimit 값에 영향을 받음 (단, maxPage의 영향을 받을 수도 있다.)
		 * 
		 * ex) pageLimit 10인 경우
		 * 
		 * startPage : 1 ---> endPage : 10
		 * 			   11 ---> endPage : 20
		 * 			   21 ---> endPage : 30
		 */
		endPage = startPage + pageLimit - 1;
		if (maxPage < endPage) {
			endPage = maxPage;
		}
		
		// 페이징바 관련 객체를 com.kh.common.PageInfo 추가
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		// 현재 요청한 페이지(currentPage)의 게시글 목록 조회(boardLimit 개수만큼)
		ArrayList<Board> list = new BoardService().selectList(pi);
		
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		
		
		request.getRequestDispatcher("views/board/boardListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
