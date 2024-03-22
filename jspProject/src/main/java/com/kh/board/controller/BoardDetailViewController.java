package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.board.service.BoardService;
import com.kh.common.model.PageInfo;

/**
 * Servlet implementation class BoardDetailController
 */
@WebServlet("/detail.bo")
public class BoardDetailViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailViewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ex) /jsp/detail.bo?bno=115
		
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		
		// 서비스 객체에게 해당 게시글 번호를 전달해서,
		//		조회수 업데이트, 해당 게시글 정보를 조회해서 결과 반환
		Board b = new BoardService().selectBoardDetail(boardNo);
		
		Attachment at = new BoardService().selectAttachment(boardNo);
		
		
		// request 객체에 조회된 정보를 담아서 응답
		if(b != null) {
			if(at != null) {
				request.setAttribute("board", b);
				request.setAttribute("attachment", at);
			} else {
				request.setAttribute("board", b);
			}
			request.getRequestDispatcher("views/board/boardDetailView.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMsg", "게시글 정보 조회 실패!");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		
		// boardDetailView.jsp 응답
		
		
		// 게시글 정보 조회 실패 시 에러페이지 응답
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
