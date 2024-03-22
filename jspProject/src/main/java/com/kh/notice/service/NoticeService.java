package com.kh.notice.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.notice.model.dao.NoticeDao;
import com.kh.notice.model.vo.Notice;

public class NoticeService {

	// 	공지사항 목록을 조회하여 반환해주는 메소드
	//	- 공지사항 목록을 반환
	//  - Connection 객체 생성 -> Dao 전달하여 DB작업 요청
	
	public ArrayList<Notice> selectNoticeList() {
		// DQL(select) => 트랜잭션x => 자원 반납
		Connection conn = getConnection();			// Connection 객체 생성
		
		// NoticeDao에게 Connection 객체와 함께 목록 조회 요청
		ArrayList<Notice> list = new NoticeDao().selectNoticeList(conn);
		close(conn);	// 자원 반납
		
		// 조회된 목록을 리턴
		return list;
	}
	
	public Notice selectDetailNotice(int noticeNo) {
		Connection conn = getConnection();
		Notice n = null;
		
		// noticeNo에 해당하는 공지사항의 조회수를 증가(업데이트) -> DML -> int
		int result = new NoticeDao().increaseCount(conn, noticeNo);
		
		// 조회수 증가를 성공하면, 트랜잭션(commit) 처리 후 상세 조회
		if(result > 0) {
			commit(conn);
			
			n = new NoticeDao().selectNotice(conn, noticeNo);
		} else {
			//	 실패하면, 트랜잭션(rollback) 처리
			rollback(conn);
		}
		// Connection 객체 반납
		close(conn);
		// 결과를 리턴(Notice|null)
		
		return n;
	}
	
	public int insertNotice(Notice notice) {
		Connection conn = getConnection();
		
		
		int result  = new NoticeDao().insertNotice(conn, notice);
		
		if(result > 0) {
			commit(conn);
			
		} else {
			rollback(conn);
		}
		// 자원 반납
		close(conn);
		return result;
	}
	
	public int updateNotice(Notice notice) {
		Connection conn = getConnection();
		
		int result = new NoticeDao().updateNotice(conn, notice);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int deleteNotice(int noticeNo) {
		Connection conn = getConnection();
		
		int result = new NoticeDao().deleteNotice(conn, noticeNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
}

