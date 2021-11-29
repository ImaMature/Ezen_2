package view;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import javax.swing.RepaintManager;

import controller.BoardController;
import controller.MemberController;
import controller.ReplyController;
import database.File;
import model.Board;
import model.Member;
import model.Reply;

public class Application {
// Scanner 여기에
	
	//0. 설정 
	public static Scanner scanner = new Scanner(System.in);
		// main 밖에 만드는 이유? : 메모리 영역에 객체를 생성해서 다른 클래스에서도 자유롭게 쓸 수 있게 만들기 위해서 
		// public ? 모든 클래스가 자유롭게 접근할 수 있도록 하기 위해 public 사용.
	public static void main(String[] args) {
		//0.프로그램 파일로딩
		File.fileload(1); //회원 파일 불러오기
		File.fileload(2); //게시물 파일불러오기
		
		//1.프로그램시작
		mainmenu(); // 메인메뉴 메소드 호출
	}
	
	//1. 메인 메뉴 메소드
	public static void mainmenu() {
		while(true) {
			try {
				System.out.println("\n**************** 회원 커뮤니티 ********************");
				System.out.println("1. 로그인 2. 회원가입 3. 아이디 찾기 4. 비밀번호 찾기");
				System.out.println("\t 선택 : ");	int ch =scanner.nextInt();
				
				if(ch==1) {System.out.println("***************** 로그인 페이지 *****************");
					System.out.println("ID : ");		String id = scanner.next();
					System.out.println("Password : ");	String password = scanner.next();
					
					boolean result = MemberController.login(id, password);
					
					if(result) { // 로그인 성공 시
						System.err.println("[알림] : 로그인 성공");
						membermenu(id); // 회원메뉴 메소드 호출 (로그인 성공한 아이디를 인수로 전달)
						
					}else {
						System.err.println("[알림] : 로그인 실패 ( 동일한 회원정보가 없습니다.)");
					}
				
				}
				else if(ch==2) {System.out.println("**************** 회원가입 페이지 *****************");
				//입력 받기 -> 변수 저장 -> 변수가 여러개 -> 객체생성 -> 객체 여러개 -> 배열/컬렉션 담는다
					System.out.println("ID [4글자 이상] : ");		String id = scanner.next();
					System.out.println("Password [4글자만] : ");	String password = scanner.next();
					System.out.println("Name [2글자 이상] : ");	String name = scanner.next();
					System.out.println("Email [@형식] : ");		String email = scanner.next();
					
					// 4개의 변수를 따로 저장하면 힘들기 때문에 => 4개의 변수를 묶어줄 객체화
					Member member = new Member(id, password, name, email, 0);
					
					// Controller 전달 [ 다른 클래스내 메소드를 호출하는 방법 ]
					//1. 해당 메소드에  static이 있다면? 클래스명.메소드명(객체) 
					boolean result = MemberController.signup(member); 
					// 객체(여기서는 member)를 매개변수로 괄호안에 안집어넣으면 변수 4개가 따로따로 이동됨.
					
					if(result ) {
						System.err.println("[알림] : 회원가입 성공");
					}else {
						System.err.println("[알림] : 회원가입 실패");
					}
					System.out.println("**********************************************");
					//2. 없다면? 
//					MemberController memberController = new MemberController();
//					memberController.signup(member);
					
				}
				else if(ch==3) {
					System.out.println("*************** 아이디 찾기 페이지 ***************");
					System.out.println("Name : ");		String name = scanner.next();
					System.out.println("Email : ");		String email = scanner.next();
					
					boolean result = MemberController.forgotid(name, email);
					
					if(result) {
						System.err.println("[알림] : 회원님의 아이디를 이메일로 전송했습니다.");
					}
					else {
						System.err.println("[알림] : 동일한 회원정보가 없습니다.");
					}
					System.out.println("**********************************************");
				}
				else if(ch==4) {
					System.out.println("************** 비밀번호 찾기 페이지 ***************");
					System.out.println("ID : ");		String id = scanner.next();
					System.out.println("Email : ");		String email = scanner.next();
					
					boolean result = MemberController.forgotpassword(id, email);
					
					if(result) {
						System.err.println("[알림] : 회원님의 비밀번호를 이메일로 전송했습니다.");
					}else {
						System.err.println("[알림] : 동일한 회원정보가 없습니다.");
					}
					System.out.println("**********************************************");
				}	
				else {
					System.err.println("알림. 알 수 없는 행동입니다.");
				}
			}
			catch(Exception e) {
				System.err.println("알림 : 메뉴 페이지 오류 [관리자 문의]"); //err.print 빨간 글씨화(에러 표시하는것)
				scanner = new Scanner(System.in); // 오류가 났을 때를 대비해 입력객체 초기화
						//new -> 메모리를 초기화하는 것.
			}
		}
	}
		//void : 아무것도 없다는 뜻으로, 
	
	//2. 회원 메뉴 메소드
	public static void membermenu(String id) {
		while(true) {
			try {
			System.out.println("\n**************** 회원 메뉴 ***********************");
			System.out.println("1. 회원정보 2. 커뮤니티 3. 로그아웃");
			System.out.println("*************************************************");
			System.out.println("\t\t 선택 : ");	int ch =scanner.nextInt();
				if(ch==1) {
					System.out.println("\n**************** 회원 정보 페이지 ******************"); 
					System.out.println("*************************************************");
				}
				if(ch==2) {
					boardmenu(id); //게시판 메소드 호출
				}
				if(ch==3) {
				System.out.println("[알림] : 로그아웃 되었습니다.");
				//break; // 가장 가까운 반복문 탈출
				return; //전체 메소드 리턴[반환] 끝내기
				}
			}catch (Exception e) {
				System.err.println("알림 : 메뉴 페이지 오류 [관리자 문의]"); //err.print 빨간 글씨화(에러 표시하는것)
				scanner = new Scanner(System.in);
			}
		 }
	}
								// 인수 : 다른클래스에서 이 메소드를 이용해 구성을 쉽게 바꾸기 위하여. 매개변수를 참조하기 위해 사용.
	
	//3. 게시판 메뉴 메소드
	public static void boardmenu(String id) {
		while(true) {
			try {
				System.out.println("\n**************** 게시판 페이지 ********************");
				System.out.println("\n번호\t타이틀\t\t작성자\t날짜\t조회수");
				
				//게시물 출력
				int i = 1; //인덱스 값 만들기(게시물 번호 용)
				//for 반복횟수 i => 게시물 번호
				for(Board board : BoardController.boardlist) {
					
					System.out.println(i + "\t" + board.getTitle() +
							"\t\t" + board.getWriter() + "\t" + board.getDate() + 
							"\t" + board.getView());
					
					i++;
				}
				System.out.println("\n**************** 게시판 페이지 ********************");
				System.out.println("1. 등록 2. 게시물 상세보기 3. 뒤로가기");
				System.out.println("*************************************************");
				System.out.println("\t\t 선택 : ");	int ch =scanner.nextInt();
				if(ch==1) {
					System.out.println("\n**************** 게시물 등록 ********************");
					//입력받기 - > 변수저장 -> 객체 -> 리스트 -> 파일
						scanner.nextLine(); //next다음에 nextLine오는경우
					System.out.println(" 제목 : ");	String title = scanner.nextLine();
					System.out.println(" 내용 : ");	String contents = scanner.nextLine();	
					//객체화
					Board board = new Board(title, contents, id);
					BoardController.add(board);
					
					System.out.println("*************************************************");
					
				}
				if(ch==2) {
					System.out.println(" *** 게시물 번호 : "); int index = scanner.nextInt();
					Board board = BoardController.detail(index-1); //인덱스가 0이기 때문에 -1을 해준다
					if(board == null) {
						System.err.println("[알림] : 선택한 게시물 번호가 존재하지 않습니다.");
					}else {
						System.out.println("\n****************** 게시물 상세 ********************");
						System.out.println(" 제목 : " + board.getTitle());
						System.out.println(" 내용 : " + board.getContents());
						System.out.println(" 작성자 : " + board.getWriter());
						System.out.println(" 작성일 : " + board.getDate());
						System.out.println(" 조회수 : " + board.getView());
						System.out.println("\n********************* 댓글 **********************");
						System.out.println("작성자\t댓글\t\t작성일");
						
						//댓글 출력
						for(Reply reply : board.getReplylist()) {
							
							System.out.println(reply.getWriter() + "\t" + reply.getContents() +"\t\t" + 
												reply.getDate());
							
						}
						System.out.println("*************************************************");
						
						System.out.print("1. 댓글쓰기 | 2. 뒤로가기"); 
						if(board.getWriter().equals(id)) { // 게시물 작성자 == 로그인 된 id
							System.out.print(" 3. 게시물 삭제 | 4. 게시물 수정");
						}
						
						int ch2 = scanner.nextInt();
						if( ch2 == 1) {
							//댓글 쓰기
							scanner.nextLine();			
							System.out.println("댓글내용 : "); String contents = scanner.nextLine();
																		// nextLine()? 띄어쓰기 있을 수 있으니까
							Reply reply = new Reply(contents, id);
							boolean result = ReplyController.add(index-1, reply);
							if(result) {
								System.out.println("[댓글 등록.]");
								
							}else {
								System.err.println("[알림] 해당 게시물 번호가 존재하지 않습니다.");
							}
							
						}
					}
					
				return; //전체 메소드 리턴[반환] 끝내기
				}
				if( ch == 3 ) {
					return; // 현재 메소드 리턴[반환] 끝내기 
				}
			}
				catch (Exception e) {
				System.err.println("알림 : 메뉴 페이지 오류 [관리자 문의]"); //err.print 빨간 글씨화(에러 표시하는것)
				scanner = new Scanner(System.in);
			}
			
		 }
	}
}
