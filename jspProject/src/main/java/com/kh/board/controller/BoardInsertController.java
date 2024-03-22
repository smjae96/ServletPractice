package com.kh.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.board.service.BoardService;
import com.kh.common.MyFileRenamePolicy;
import com.kh.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class BoardInsertController
 */
@WebServlet("/insert.bo")
public class BoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// * 일반적인 방식이 아닌, multipart/form-data 요청으로 들어오는 경우 request 객체에서 데이터를 추출 불가.
		// 	enctype이 multipart/form-data인 경우 처리하는 방식
		
		if(ServletFileUpload.isMultipartContent(request)) {		// enctype이 multipart/form-data인 경우 true 반환
			
			//System.out.println("enctype이 multipart/form-data로 들어옴!!");
			// 파일 업로드를 위한 라이브러리 : cos.jar (com.oreilly.servlet의 약자)
			//			www.servlets.com/cos 다운로드 받아서 lib/cos.jar 파일을
			// 				src/main/webapp/WEB-INF/lib/ 폴더로 복사
			
			// 1. 전달되는 파일을 처리할 작업 내용
			//		- 파일의 용량 제한
			//		- 파일을 저장시킬 폴더 경로
			
			// 	파일의 용량 제한 ( int maxSize => byte단위) =? 10Mbyte로 제한을 하고자 한다면 
			/*	byte -> kbyte -> mbyte -> gbyte -> tbyte (각 단위는 1024배 차이)
			 * 
			 * 1kbyte == 1024byte
			 * 1mbyte == 1024kbyte == (1024*1024)byte
			 * 10mbyte == 10*(1024*1024)byte
			 * 
			 */
			int maxSize = 10 * 1024 * 1024; // 10mbyte
			
			//	파일을 저장시킬 폴더 경로 (String savePath)
			String savePath = request.getSession().getServletContext().getRealPath("/resources/b_upfiles/");
//			System.out.println(savePath);
			
			// 2. 전달된 파일이름을 변경 및 서버에 업로드
			// => HttpServletRequest -> MultipartRequest 변환
			// 	  MultipartRequest 변수명 = new MultipartRequest(request, 저장시킬 폴더의 경로, 제한한용량크기, 인코딩값, 파일명을수정해주는객체);
			//	  --> 위의 한 줄 실행 시 전달된 첨부파일이 해당 경로에 무조건 업로드됨!
			
			//	* 파일명이 같을 경우, 덮어씌워질 수 있으며
			//	  파일명에 한글/특수문자/띄어쓰기가 포함된 경우 서버에 따라 문제가 발생할 수 있음 => 파일명을 수정하는게 일반적임.
			//	  => 기본적으로 파일명이 중복되지 않게 변경해주는 객체 : DefaultFileRenamePolicy 객체 (cos.jar 에서 제공)
			//		 동일한 파일명이 있을 경우 파일명 뒤에 카운트를 해서 숫자를 붙여서 변경
			
			//  * 원하는 방식으로 파일명을 바꾸고자 할 경우 : FileRenamePolicy 인터페이스를 구현해서 새로운 객체를 만들어 사용
			//	  - com.kh.common.MyFileRenamePolicy 클래스 작성
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize
																					, "utf-8", new MyFileRenamePolicy());
			// 3. DB에 기록할 데이터를 추출해서 vo에 저장
			//		- 카테고리 번호, 게시글 제목, 게시글 내용, 로그인한 회원번호 => BOARD 테이블에 추가
			//		- 첨부파일이 있는 경우) 원본 파일명, 수정된 파일명, 저장 경로 => ATTACHMENT 테이블에 추가
			
			String category = multiRequest.getParameter("category");	// 카테고리 번호
			String title = multiRequest.getParameter("title");			// 게시글 제목
			String content = multiRequest.getParameter("content");		// 게시글 내용
			Member m = (Member)(request.getSession().getAttribute("loginUser"));
			int userNo = m.getUserNo();									// 로그인한 회원번호
			
			Board board = new Board();
			board.setCategoryNo(category);
			board.setBoardTitle(title);
			board.setBoardContent(content);
			board.setBoardWriter(String.valueOf(userNo));
			// ---------------------- BOARD 테이블에 추가할 데이터 정리
			Attachment attachment = null;		// 첨부파일이 있는 경우 객체를 생성
			
			// multiRequest.getOriginalFileName("키값") : 첨부파일이 있는 경우 "원본파일명" | 없는 경우 null 반환
			if(multiRequest.getOriginalFileName("upfile") != null) {
				// 첨부파일이 있는 경우
				attachment = new Attachment();
				
				attachment.setOriginName(multiRequest.getOriginalFileName("upfile"));
				attachment.setChangeName(multiRequest.getFilesystemName("upfile"));		// 키값에 해당하는 첨부파일의 실제 저장된 경로를 알려주는 메소드
				attachment.setFilePath("resources/b_upfiles/");
			}
			// ------------------- ATTACHMENT 테이블에 추가할 데이터 정리
			
			// 4. 서비스 요청 (요청 처리)
			//	  게시글을 추가, 첨부파일이 있는 경우만 DB에 저장되도록
			//	  게시글 추가 성공, 첨부파일이 없는 경우 => 게시글 등록 성공
			//	  게시글 추가 성공, 첨부파일 정보 추가 성공 => 게시글 등록 성공
			//	  게시글 또는 첨부파일 정보 추가 실패 => 게시글 등록 실패
			int result = new BoardService().insertBoard(board, attachment);
			
			// 5. 결과에 따라 응답페이지 지정
			if(result != 0) {	// 게시글 등록 성공! => 게시글목록페이지(첫번째)로 재요청
				request.getSession().setAttribute("alertMsg", "게시글 등록 성공!");
				// /jsp/list.bo?cpage=1 url로 재요청
				response.sendRedirect(request.getContextPath() + "/list.bo?cpage=1");
				
		
			} else {			// 게시글 등록 실패 => 첨부파일이 있을 경우 파일 삭제, 에러페이지로 응답
				
				if(attachment != null) {	// 첨부파일이 있는 경우
					new File(savePath + attachment.getChangeName()).delete();	// 파일 삭제하는 메소드 : delete()
				}
				request.setAttribute("errorMsg", "게시글 저장 실패!");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}
				
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
