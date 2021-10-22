package database;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import controller.BoardController;
import controller.MemberController;
import model.Board;
import model.Member;



public class File {

	// 1. 회원정보를 저장하는 파일의 경로 
	private static String memberpath =
			"C:/Users/505/git/Ezen_2/Java_Project001/src/database/memberlist.txt";
	
	// 2. 게시물를 저장하는 파일의 경로
	private static String boardpath =
			"C:/Users/505/git/Ezen_2/Java_Project001/src/database/boardlist.txt";
	
	//저장하기 메소드
	public static boolean filesave (int type) { //[파일 관리하는 메소드] 여러개의 메소드가 아닌 하나의 메소드로 관리하는 방법
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
			if(type==2) {
				fileOutputStream = new FileOutputStream( boardpath );
				for( Board board : BoardController.boardlist ) {
					String outstring = board.getTitle()+","+board.getContents()+","+
										board.getWriter()+","+board.getDate()+","+
										board.getView()+"\n";
					fileOutputStream.write( outstring.getBytes() );
				}
				fileOutputStream.flush();
				fileOutputStream.close(); 
				return true;
			}
			if(type==3) {}
			
		}catch (Exception e) {
			System.out.println("[알림] : 파일 저장 오류 발생 [ 관리자에게 문의 ]");
		}
		return false; // 파일 처리 실패
	}
	//불러오기 메소드
	public static boolean fileload(int type) {
		try {
		FileInputStream fileInputStream = null; // 선언만
		
			if(type == 1) {
				//1. 입력 스트림 경로 설정
				fileInputStream = new FileInputStream(memberpath);
				
				//2. 스트림 (단위 : 바이트) 바이트 배열 선언
				byte[] bytes = new byte[10000]; //10kb 정도
				
				//3. 입력스트림에서 바이트 읽어와서 배열에 저장
				fileInputStream.read(bytes);
				
				//4. 바이트 배열 -> 문자열 변환
				String instring = new String(bytes); //-2 안해도 되는 이유? 다 가져오는 거라서
				
				//5. 회원 분리하기 \n
				String[] members = instring.split("\n"); //\n구분시 공백 회원이 추가됨
				
				//6. 반복문을 이용해 회원별 필드 분리하기,
				//for(String temp : members) { 아래 for문과 같은거임
					//temp.split(",")
				//}
				for(int i = 0; i<members.length-1; i++) { //-1 하는 이유? 공백회원 제거하려고
					
					//7. 회원별 필드 분리
					String [] field = members[i].split(",");
					
					//8. 분리된 필드를 객체화 [ point 필드는 int 형으로 변환 : String -> int() ]
					Member member = new Member(field[0], field[1], field[2], field[3], Integer.parseInt(field[4]));
						//static을 안쓰면 객체 없이 메모리(new 연산자)에서 불러오는 것이고  //static을 쓰면 해당 클래스명.객체명(메소드명)으로 불러올 수 있다.  
					
					//9. 각 객체를 리스트에 저장
					MemberController.memberList.add(member);
				}
				fileInputStream.close(); // 스트림 닫기
				return true; // 리턴값 출력
			}
			 if(type == 2) {
				 fileInputStream = new FileInputStream(boardpath); // 1. 파일경로 
					byte[] bytes = new byte[10000]; // 10kb 정도 // 2. 파일용량 
					fileInputStream.read( bytes );	// 3. 파일 읽기 
					String instring = new String(bytes); // 4. 문자열 변환 
					String[] boards = instring.split("\n"); // 5. 게시물 \n 구분 
					
					for( int i = 0 ; i<boards.length-1 ;i++ ) { //6.  -1 : 마지막 게시물 제외
						String[] field = boards[i].split(",");
						Board board = new Board( field[0] , field[1] , field[2], 
										field[3]  , Integer.parseInt(field[4]));
						BoardController.boardlist.add(board);
						}
					fileInputStream.close();
					return true;
			 }
			 if(type == 3) {}
			}
			catch (Exception e) {
				System.out.println("[알림] : 파일 저장 오류 발생 [ 관리자에게 문의 ]");	
			}
			return false; // 파일 불러오기 성공시
	}
}
