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
// Scanner ���⿡
	
	//0. ���� 
	public static Scanner scanner = new Scanner(System.in);
		// main �ۿ� ����� ����? : �޸� ������ ��ü�� �����ؼ� �ٸ� Ŭ���������� �����Ӱ� �� �� �ְ� ����� ���ؼ� 
		// public ? ��� Ŭ������ �����Ӱ� ������ �� �ֵ��� �ϱ� ���� public ���.
	public static void main(String[] args) {
		//0.���α׷� ���Ϸε�
		File.fileload(1); //ȸ�� ���� �ҷ�����
		File.fileload(2); //�Խù� ���Ϻҷ�����
		
		//1.���α׷�����
		mainmenu(); // ���θ޴� �޼ҵ� ȣ��
	}
	
	//1. ���� �޴� �޼ҵ�
	public static void mainmenu() {
		while(true) {
			try {
				System.out.println("\n**************** ȸ�� Ŀ�´�Ƽ ********************");
				System.out.println("1. �α��� 2. ȸ������ 3. ���̵� ã�� 4. ��й�ȣ ã��");
				System.out.println("\t ���� : ");	int ch =scanner.nextInt();
				
				if(ch==1) {System.out.println("***************** �α��� ������ *****************");
					System.out.println("ID : ");		String id = scanner.next();
					System.out.println("Password : ");	String password = scanner.next();
					
					boolean result = MemberController.login(id, password);
					
					if(result) { // �α��� ���� ��
						System.err.println("[�˸�] : �α��� ����");
						membermenu(id); // ȸ���޴� �޼ҵ� ȣ�� (�α��� ������ ���̵� �μ��� ����)
						
					}else {
						System.err.println("[�˸�] : �α��� ���� ( ������ ȸ�������� �����ϴ�.)");
					}
				
				}
				else if(ch==2) {System.out.println("**************** ȸ������ ������ *****************");
				//�Է� �ޱ� -> ���� ���� -> ������ ������ -> ��ü���� -> ��ü ������ -> �迭/�÷��� ��´�
					System.out.println("ID [4���� �̻�] : ");		String id = scanner.next();
					System.out.println("Password [4���ڸ�] : ");	String password = scanner.next();
					System.out.println("Name [2���� �̻�] : ");	String name = scanner.next();
					System.out.println("Email [@����] : ");		String email = scanner.next();
					
					// 4���� ������ ���� �����ϸ� ����� ������ => 4���� ������ ������ ��üȭ
					Member member = new Member(id, password, name, email, 0);
					
					// Controller ���� [ �ٸ� Ŭ������ �޼ҵ带 ȣ���ϴ� ��� ]
					//1. �ش� �޼ҵ忡  static�� �ִٸ�? Ŭ������.�޼ҵ��(��ü) 
					boolean result = MemberController.signup(member); 
					// ��ü(���⼭�� member)�� �Ű������� ��ȣ�ȿ� ����������� ���� 4���� ���ε��� �̵���.
					
					if(result ) {
						System.err.println("[�˸�] : ȸ������ ����");
					}else {
						System.err.println("[�˸�] : ȸ������ ����");
					}
					System.out.println("**********************************************");
					//2. ���ٸ�? 
//					MemberController memberController = new MemberController();
//					memberController.signup(member);
					
				}
				else if(ch==3) {
					System.out.println("*************** ���̵� ã�� ������ ***************");
					System.out.println("Name : ");		String name = scanner.next();
					System.out.println("Email : ");		String email = scanner.next();
					
					boolean result = MemberController.forgotid(name, email);
					
					if(result) {
						System.err.println("[�˸�] : ȸ������ ���̵� �̸��Ϸ� �����߽��ϴ�.");
					}
					else {
						System.err.println("[�˸�] : ������ ȸ�������� �����ϴ�.");
					}
					System.out.println("**********************************************");
				}
				else if(ch==4) {
					System.out.println("************** ��й�ȣ ã�� ������ ***************");
					System.out.println("ID : ");		String id = scanner.next();
					System.out.println("Email : ");		String email = scanner.next();
					
					boolean result = MemberController.forgotpassword(id, email);
					
					if(result) {
						System.err.println("[�˸�] : ȸ������ ��й�ȣ�� �̸��Ϸ� �����߽��ϴ�.");
					}else {
						System.err.println("[�˸�] : ������ ȸ�������� �����ϴ�.");
					}
					System.out.println("**********************************************");
				}	
				else {
					System.err.println("�˸�. �� �� ���� �ൿ�Դϴ�.");
				}
			}
			catch(Exception e) {
				System.err.println("�˸� : �޴� ������ ���� [������ ����]"); //err.print ���� �۾�ȭ(���� ǥ���ϴ°�)
				scanner = new Scanner(System.in); // ������ ���� ���� ����� �Է°�ü �ʱ�ȭ
						//new -> �޸𸮸� �ʱ�ȭ�ϴ� ��.
			}
		}
	}
		//void : �ƹ��͵� ���ٴ� ������, 
	
	//2. ȸ�� �޴� �޼ҵ�
	public static void membermenu(String id) {
		while(true) {
			try {
			System.out.println("\n**************** ȸ�� �޴� ***********************");
			System.out.println("1. ȸ������ 2. Ŀ�´�Ƽ 3. �α׾ƿ�");
			System.out.println("*************************************************");
			System.out.println("\t\t ���� : ");	int ch =scanner.nextInt();
				if(ch==1) {
					System.out.println("\n**************** ȸ�� ���� ������ ******************"); 
					System.out.println("*************************************************");
				}
				if(ch==2) {
					boardmenu(id); //�Խ��� �޼ҵ� ȣ��
				}
				if(ch==3) {
				System.out.println("[�˸�] : �α׾ƿ� �Ǿ����ϴ�.");
				//break; // ���� ����� �ݺ��� Ż��
				return; //��ü �޼ҵ� ����[��ȯ] ������
				}
			}catch (Exception e) {
				System.err.println("�˸� : �޴� ������ ���� [������ ����]"); //err.print ���� �۾�ȭ(���� ǥ���ϴ°�)
				scanner = new Scanner(System.in);
			}
		 }
	}
								// �μ� : �ٸ�Ŭ�������� �� �޼ҵ带 �̿��� ������ ���� �ٲٱ� ���Ͽ�. �Ű������� �����ϱ� ���� ���.
	
	//3. �Խ��� �޴� �޼ҵ�
	public static void boardmenu(String id) {
		while(true) {
			try {
				System.out.println("\n**************** �Խ��� ������ ********************");
				System.out.println("\n��ȣ\tŸ��Ʋ\t\t�ۼ���\t��¥\t��ȸ��");
				
				//�Խù� ���
				int i = 1; //�ε��� �� �����(�Խù� ��ȣ ��)
				//for �ݺ�Ƚ�� i => �Խù� ��ȣ
				for(Board board : BoardController.boardlist) {
					
					System.out.println(i + "\t" + board.getTitle() +
							"\t\t" + board.getWriter() + "\t" + board.getDate() + 
							"\t" + board.getView());
					
					i++;
				}
				System.out.println("\n**************** �Խ��� ������ ********************");
				System.out.println("1. ��� 2. �Խù� �󼼺��� 3. �ڷΰ���");
				System.out.println("*************************************************");
				System.out.println("\t\t ���� : ");	int ch =scanner.nextInt();
				if(ch==1) {
					System.out.println("\n**************** �Խù� ��� ********************");
					//�Է¹ޱ� - > �������� -> ��ü -> ����Ʈ -> ����
						scanner.nextLine(); //next������ nextLine���°��
					System.out.println(" ���� : ");	String title = scanner.nextLine();
					System.out.println(" ���� : ");	String contents = scanner.nextLine();	
					//��üȭ
					Board board = new Board(title, contents, id);
					BoardController.add(board);
					
					System.out.println("*************************************************");
					
				}
				if(ch==2) {
					System.out.println(" *** �Խù� ��ȣ : "); int index = scanner.nextInt();
					Board board = BoardController.detail(index-1); //�ε����� 0�̱� ������ -1�� ���ش�
					if(board == null) {
						System.err.println("[�˸�] : ������ �Խù� ��ȣ�� �������� �ʽ��ϴ�.");
					}else {
						System.out.println("\n****************** �Խù� �� ********************");
						System.out.println(" ���� : " + board.getTitle());
						System.out.println(" ���� : " + board.getContents());
						System.out.println(" �ۼ��� : " + board.getWriter());
						System.out.println(" �ۼ��� : " + board.getDate());
						System.out.println(" ��ȸ�� : " + board.getView());
						System.out.println("\n********************* ��� **********************");
						System.out.println("�ۼ���\t���\t\t�ۼ���");
						
						//��� ���
						for(Reply reply : board.getReplylist()) {
							
							System.out.println(reply.getWriter() + "\t" + reply.getContents() +"\t\t" + 
												reply.getDate());
							
						}
						System.out.println("*************************************************");
						
						System.out.print("1. ��۾��� | 2. �ڷΰ���"); 
						if(board.getWriter().equals(id)) { // �Խù� �ۼ��� == �α��� �� id
							System.out.print(" 3. �Խù� ���� | 4. �Խù� ����");
						}
						
						int ch2 = scanner.nextInt();
						if( ch2 == 1) {
							//��� ����
							scanner.nextLine();			
							System.out.println("��۳��� : "); String contents = scanner.nextLine();
																		// nextLine()? ���� ���� �� �����ϱ�
							Reply reply = new Reply(contents, id);
							boolean result = ReplyController.add(index-1, reply);
							if(result) {
								System.out.println("[��� ���.]");
								
							}else {
								System.err.println("[�˸�] �ش� �Խù� ��ȣ�� �������� �ʽ��ϴ�.");
							}
							
						}
					}
					
				return; //��ü �޼ҵ� ����[��ȯ] ������
				}
				if( ch == 3 ) {
					return; // ���� �޼ҵ� ����[��ȯ] ������ 
				}
			}
				catch (Exception e) {
				System.err.println("�˸� : �޴� ������ ���� [������ ����]"); //err.print ���� �۾�ȭ(���� ǥ���ϴ°�)
				scanner = new Scanner(System.in);
			}
			
		 }
	}
}
