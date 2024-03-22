package com.kh.member.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;		// static 사용 시 바로 메소드 호출 가능.
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;

public class MemberService {
	// Member loginUser = new MemberService().loginMember(userId, userPwd);
	public Member loginMember(String userId, String userPwd) {
		Connection conn = getConnection();
		
		Member m = new MemberDao().loginMember(conn, userId, userPwd);
		close(conn);
		
		return m;
	}
	
	public int insertMember(Member m) {
		// 1) Connection 객체 생성
		Connection conn = getConnection();
		
		// 2) Dao 에게 데이터 전달과 함께 필요한 메소드 호출
		int result = new MemberDao().insertMember(conn, m);
		
		// 3) DML 사용 시 트랜잭션 처리
		if(result >0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		// 4) Connection 객체 반납(close)
		close(conn);
		
		return result;
	}
	
	// 회원 정보 수정 처리 메소드
	public Member updateMember(Member m) {
		// Connection 객체 생성
		Connection conn = getConnection();
		
		// 조건으로 사용될 기준은 userId
		// 1) 유저정보의 변경된 내용을 반영 => (DML) update
		int result = new MemberDao().updateMember(conn, m);
		
		Member updateMember = null;
		// 2) 수정이 성공했을 경우, 유저 정보를 조회하여 반환(리턴)
		if(result >0) {
			commit(conn);
			updateMember = new MemberDao().selectMember(conn, m.getUserId());
		} else {
			// 회원 정보 수정이 실패했을 경우 null이 반환될 것임.
			rollback(conn);
		}
		
		close(conn);
		return updateMember;
	}
	
	public int updatePwd(String userId, String userPwd, String updatePwd) {
		Connection conn = getConnection();
		
		int result = new MemberDao().updatePwd(conn, userId, userPwd, updatePwd);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
		
	}
	
	public Member updatePassword(String userId, String userPwd, String updatePwd) {
		// Connection 객체 생성
		Connection conn = getConnection();
		// Dao에게 전달된 데이터와 Connection 객체를 가지고 요청
		// 비밀번호 변경 (update, DML) => int (처리된 행 수)
		int result = new MemberDao().updatePwd(conn, userId, userPwd, updatePwd);
		
		// 변경 성공 시 유저정보를 조회하여 리턴
		Member updateMem = null;
		if(result >0) {
			commit(conn);
			updateMem = new MemberDao().selectMember(conn, userId);
		} else {
			// 변경 실패 시 null을 리런
			rollback(conn);
		}
		close(conn);
		return updateMem;
	}
	
	public int deleteMember(String userId, String userPwd) {
		Connection conn = getConnection();
		
		// update 실행 (DML) => 트랜잭션 처리
		int result = new MemberDao().deleteMember(conn, userId, userPwd);
		
		if(result >0) {
			commit(conn);
			
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
}
