package com.kh.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

// FileRenamePolicy 인터페이스 구현
public class MyFileRenamePolicy implements FileRenamePolicy {
	// rename 메소드 재정의
	//	=> 파일명 수정 후 수정된 파일을 반환해주는 메소드
	@Override
	public File rename(File originFile) {
		
		// 원본 파일명 ( test.jpg )
		String originName = originFile.getName();
		
		// => 수정할 파일명 ("20240322092130xxxxx.jpg") : 파일 업로드 시간(년월일시분초) + 5자리 랜덤값 (10000 ~ 99999) + 원본파일의 확장자
		
		// 1) 파일 업로드 시간
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		// 2) 5자리 랜덤값 : Math.random() => 0.0000xxx1 ~ 0.99999xxx
		int randNum = (int)(Math.random() * 90000 + 10000);		// 12123
		
		// 3) 원본 파일의 확장자
		//	  문자열.substring(마지막 .의 위치)   // substring(문자열을 자를 위치의 시작점)
		//					-> 문자열.lastIndexOf(찾을문자열) => 마지막 위치
		String ext = originName.substring(originName.lastIndexOf(".")); // => .jpg 
		
		String changeName = currentTime + randNum + ext;	// -> 2024032209322413123.jpg
		
		return new File(originFile.getParent(), changeName);	// new File(원본파일 디렉토리, 변경할 파일명)
		
	}

}
