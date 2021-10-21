package controller;

import java.util.ArrayList;

import database.File;
import model.Member;

public class MemberController {
	
	//1. �ʵ� [��� ȸ���� ��� �ִ� ��ü = �÷��� �����ӿ�ũ vs �迭 ] // �迭�� ������ ���Ƽ� �÷��� ������ ��ũ�� ���.
	public static ArrayList<Member> memberList = new ArrayList<>();
	
	//2. �޼ҵ�
		//ȸ������
	public static boolean signup(Member member) { //static�̸� �Է¹��� �Ű����� ��ȣ�ȿ� �־�ߵ�
		//��ȯŸ�� :  
		
		
		// 1. ��ȿ�� �˻� //ȸ������ �ȵǴ� ���
		if (member.getId().length() < 4) {
			System.out.println("[�˸�] : ID�� 4���� �̻� �����մϴ�.");
			return false;
		} //false�� �����̹Ƿ� false�� ����
		if (member.getPassword().length() != 4) {
			System.out.println("[�˸�] : Password�� 4���ڸ� �Դϴ�."); 
			return false;
		}
		if (member.getName().length() < 2) {
			System.out.println("[�˸�] : Name�� 2���� �̻� �Դϴ�."); 
			return false;
		}
		if (!member.getEmail().contains("@")) {
			System.out.println("[�˸�] : Email�� �ݵ�� @ �����ؾ� ��2�ϴ�.");
			return false;
		}
		
		// 2. ID �ߺ�üũ
		for( Member temp : memberList) {
			if(temp.getId().equals(member.getId())) {
				System.out.println("[�˸�] : �̹� ������� ���̵��Դϴ�.");
				return false;
			}
		}
		// 3. ����Ʈ ����
		memberList.add(member);
		
		// 4. ���� ó��
		File.filesave(1); // 1�� ���� ������ type���� �����ؼ� �����ϰڴٴ� ��
		
		return true; //ȸ������ ������
	}
	
		//�α��� : 
	public static boolean login(String id, String password) {
		for(Member member : memberList) {
			if ( member.getId().equals(id) && 
					member.getPassword().equals(password)) {
				return true;//�α��� ������
			}
		}
		return false; // �α��� ���н�
	}
	
		//���̵� ã�� [ ���̵�, �̸��� ] [�̸��� �̸����� �μ��� �޾� �ش� ���Ͽ� ���̵� ����]
	public static boolean forgotid(String name, String email) {
		for(Member member : memberList) {
			if(member.getName().equals(name) && 
					member.getEmail().equals(email)) {
				return true; //���̵� ã�� ������
			}
		}
		return false; //���̵� ã�� ���н�
	}
	
		//��� ã�� [���̵�� �̸����� �μ��� �޾� �ش� ���Ͽ� ��й�ȣ ���� ]
	public static boolean forgotpassword(String id, String email) {
		for(Member member : memberList) {
			if(member.getId().equals(id) && 
					member.getEmail().equals(email)) {
				return true; //���̵� ã�� ������
			}
		}
		return false; //���̵� ã�� ���н�
	}
	
		//ȸ������ [���̵� �μ��� �޾� �ش� ���̵��� ��� ���� ��ȯ ]
	public Member info(String loginid) {
		Member member = null;
		return member;
	}
		//ȸ���������� [���̵�� ���������� �޾Ƽ� ������Ʈ ó���� �������� ��ȯ]
	public boolean info (String loginid, Member updatemember) {
		return true;
		
	}
		//ȸ��Ż��	[���̵� �μ��� �޾� �ش� ���̵� ���� �� �������� ��ȯ]
	public boolean delete(String loginid) {
		return true;
	}
	
	
	
}
