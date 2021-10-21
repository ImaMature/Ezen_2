package controller;

import java.util.ArrayList;

import database.File;
import model.Member;

public class MemberController {
	
	//1. 필드 [모든 회원을 담고 있는 객체 = 컬렉션 프레임워크 vs 배열 ] // 배열은 문제가 많아서 컬렉션 프레임 워크를 사용.
	public static ArrayList<Member> memberList = new ArrayList<>();
	
	//2. 메소드
		//회원가입
	public static boolean signup(Member member) { //static이면 입력받을 매개변수 괄호안에 넣어야됨
		//반환타입 :  
		
		// 1. 유효성 검사 //회원가입 안되는 경우
		if (member.getId().length() < 4) {
			System.out.println("[알림] : ID는 4글자 이상만 가능합니다.");
			return false;
		} //false가 실패이므로 false를 리턴
		if (member.getPassword().length() != 4) {
			System.out.println("[알림] : Password는 4글자만 입니다."); 
			return false;
		}
		if (member.getName().length() < 2) {
			System.out.println("[알림] : Name는 2글자 이상만 입니다."); 
			return false;
		}
		if (!member.getEmail().contains("@")) {
			System.out.println("[알림] : Email은 반드시 @ 포함해야 합2니다.");
			return false;
		}
		
		// 2. ID 중복체크
		for( Member temp : memberList) {
			if(temp.getId().equals(member.getId())) {
				System.out.println("[알림] : 이미 사용중인 아이디입니다.");
				return false;
			}
		}
		// 3. 리스트 저장
		memberList.add(member);
		
		// 4. 파일 처리
		File.filesave(1); // type으로 제어해서 구분하겠다는 것
		
		return true; //회원가입 성공시
	}
	
		//로그인 : 
	public boolean login(String id, String password) {
		
		return true; //로그인 성공시
	}
	
		//아이디 찾기 [ 아이디, 이메일 ] [이름과 이메일을 인수로 받아 해당 메일에 아이디 전송]
	public boolean forgotid(String name, String email) {
		return true; //아이디 찾기 성공시
	}
	
		//비번 찾기 [아이디와 이메일을 인수로 받아 해당 메일에 비밀번호 전송 ]
	public boolean forgotpassword(String id, String email) {
		return true; //비밀번호 찾기 성공시
	}
	
		//회원정보 [아이디르 인수로 받아 해당 아이디의 모든 정보 반환 ]
	public Member info(String loginid) {
		Member member = null;
		return member;
	}
		//회원정보수정 [아이디와 수정정보를 받아서 업데이트 처리후 성공여부 반환]
	public boolean info (String loginid, Member updatemember) {
		return true;
		
	}
		//회원탈퇴	[아이디를 인수로 받아 해당 아이디 삭제 후 성공여부 반환]
	public boolean delete(String loginid) {
		return true;
	}
	
	
	
}
