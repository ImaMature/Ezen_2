package database;

import java.io.FileOutputStream;
import controller.MemberController;
import model.Member;




public class File {

	//필드
	private static String memberpath =
			"D:/work0928/Java_Project001/src/database/memberlist.txt";
	
	//저장하기 메소드
	public static boolean filesave (int type) {
					//1. type : 1. 회원저장 2. 게시물 저장 3. 댓글저장
		try {	
			FileOutputStream fileOutputStream = null; //선언만

			if(type ==1 ) { //회원저장
				//1. 파일객체에 경로 설정
				fileOutputStream = new FileOutputStream(memberpath);
				//2. 반복문을이용한 회원리스트에서 하나씩 회원 가져오기
				for(Member member : MemberController.memberList) {
					String outString = member.getId()+","+member.getPassword()+","+ 
										member.getName() + "," + member.getEmail() + ","+ 
										member.getPoint() + "\n";
					
					//4. 바이트로 내보내기
					fileOutputStream.write(outString.getBytes());
				}
				//5. 스트림 사용후 초기화
				fileOutputStream.flush(); // 파일 스트림 내 존재하는 바이트 제거
				fileOutputStream.close(); // 파일 스트림 닫기
			
			return true; //파일처리 성공
			}
		}catch (Exception e) {
			System.out.println("[알림] : 파일 저장 오류 발생 [ 관리자에게 문의 ]");
		}
		return false;
	}
	//불러오기 메소드
	public boolean fileload(int type) {
		return true; // 파일 불러오기 성공시
	}
}
