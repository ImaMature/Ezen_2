package view;

import java.util.Scanner;

import controller.MemberController;
import database.File;
import model.Member;

public class Application {
// Scanner ���⿡
	
	//0. ���� 
	public static Scanner scanner = new Scanner(System.in);
		// main �ۿ� ����� ����? : �޸� ������ ��ü�� �����ؼ� �ٸ� Ŭ���������� �����Ӱ� �� �� �ְ� ����� ���ؼ� 
		// public ? ��� Ŭ������ �����Ӱ� ������ �� �ֵ��� �ϱ� ���� public ���.
	public static void main(String[] args) {
		File.fileload(1);

		mainmenu(); // ���θ޴� �޼ҵ� ȣ��
	}
	
	//1. ���� �޴� �޼ҵ�
	public static void mainmenu() {
		while(true) {
			try {
				System.out.println("\n**************** ȸ�� Ŀ�´�Ƽ ********************");
				System.out.println("1. �α��� 2. ȸ������ 3. ���̵� ã�� 4. ��й�ȣ ã��");
				System.out.println("\t\t ���� : ");	int ch =scanner.nextInt();
				
				if(ch==1) {System.out.println("***************** �α��� ������ *****************");
					System.out.println("ID : ");		String id = scanner.next();
					System.out.println("Password : ");	String password = scanner.next();
					
					boolean result = MemberController.login(id, password);
					
					if(result) { // �α��� ���� ��
						System.out.println("[�˸�] : �α��� ����");
						membermenu(id); // ȸ���޴� �޼ҵ� ȣ�� (�α��� ������ ���̵� �μ��� ����)
						
					}else {
						System.out.println("[�˸�] : �α��� ���� ( ������ ȸ�������� �����ϴ�.)");
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
						System.out.println("[�˸�] : ȸ������ ����");
					}else {
						System.out.println("[�˸�] : ȸ������ ����");
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
		
	}
								// �μ� : �ٸ�Ŭ�������� �� �޼ҵ带 �̿��� ������ ���� �ٲٱ� ���Ͽ�. �Ű������� �����ϱ� ���� ���.
	
	//3. �Խ��� �޴� �޼ҵ�
	public static void boardmenu(String id) {
		
	}
}
