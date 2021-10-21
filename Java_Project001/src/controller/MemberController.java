package controller;


import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
			System.out.println("[알림] : Email은 반드시 @ 포함해야 합니다.");
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
		File.filesave(1); // 1을 넣은 이유는 type으로 제어해서 구분하겠다는 것
		
		return true; //회원가입 성공시
	}
	
		//로그인 : 
	public static boolean login(String id, String password) {
		for(Member member : memberList) {
			if ( member.getId().equals(id) && 
					member.getPassword().equals(password)) {
				return true;//로그인 성공시
			}
		}
		return false; // 로그인 실패시
	}
	
		//아이디 찾기 [ 아이디, 이메일 ] [이름과 이메일을 인수로 받아 해당 메일에 아이디 전송]
	public static boolean forgotid(String name, String email) {
		for(Member member : memberList) {
			if(member.getName().equals(name) && 
					member.getEmail().equals(email)) {
				
				//메일 메소드 호출	//받는사람 이멜 , 1(아이디 찾기), 정보(찾은 아이디)
				sendmail(member.getEmail(), 1, member.getId());
				
				return true; //아이디 찾기 성공시
			}
		}
		return false; //아이디 찾기 실패시
	}
	
		//비번 찾기 [아이디와 이메일을 인수로 받아 해당 메일에 비밀번호 전송 ]
	public static boolean forgotpassword(String id, String email) {
		for(Member member : memberList) {
			if(member.getId().equals(id) && 
					member.getEmail().equals(email)) {
				return true; //아이디 찾기 성공시
			}
		}
		return false; //아이디 찾기 실패시
	}
	
		//회원정보 [아이디를 인수로 받아 해당 아이디의 모든 정보 반환 ]
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
	
		//파일전송 메소드
	public static void sendmail(String tomail, int type, String contents) {
					//tomail : 받는 사람 이메일		//type : 아이디찾기(1), 비밀번호찾기(2), 가입메일(3)
					//contents : 메일에 넣을 정보
	
		//1) API 라이브러리 다운 [ activation.jar , mail.jar ]
		//2) 현재 프로젝트에 라이브러리 등록
		
		//설정 [ 보내는 사람 아이디, 비밀번호, 메일회사의 호스트 ]
		String fromemail = "";
		String frompassword = "";
		
		//smtp : 간이 우편 전송 프로토콜
		//pop3 : 받을 때 프로토콜
		//프로토콜 : 통신 규약 [약속]
		
		Properties properties = new Properties(); //설정 컬렉션 map 프레임워크
		properties.put("mail.smtp.host", "smtp.naver.com"); //host호스트 
		properties.put("mail.smtp.port", 587); //port : 호스트의 접속하는 번호
		properties.put("mail.smpt.auth", true); //auth : 회원 인증
		
		//1. 인증
//		Session session = Session.getDefaultInstance(properties,new Authenticator() {코드});
		
		Session session = Session.getDefaultInstance(properties,new Authenticator() {
		
			//익명 구현 객체 : 1회성 객체 
			@Override // 패스워드 인증해주는 메소드
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromemail, frompassword);
													//인증 메일, 인증 패스워드
			}
		}); // 인증 끝
		
		try {
			//메일 보내기
				// 1. 보내는 사람의 인증 정보
			MimeMessage message = new MimeMessage(session);
				// 2. 보내는 사람의 메일 주소 설정
			message.setFrom(new InternetAddress(fromemail));
				// 3. 받는 사람 메일 주소 설정
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(tomail));
			if(type == 1) {
				// 4. 메일 제목
				message.setSubject("java console(forgot ID)");
				// 5. 메일 내용
				message.setText("회원님의 아이디 : " + contents);
			}
			if(type == 2) {	
				message.setSubject("java console(forgot Password)");
				message.setText("회원님의 아이디 : " + contents);
			}
			if(type == 3) {
				message.setSubject("java console(Member Signup");
				message.setText("java console에 가입해주셔서 감사합니다~" );
			}
				// 6. 메일 전송
				Transport.send(message);
		
		}
		catch(Exception e) {
			System.err.println("[알림] : 메일전송 실패 [관리자에게 문의]" + e);
			}
	}	
}
