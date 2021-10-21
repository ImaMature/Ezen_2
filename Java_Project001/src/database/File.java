package database;

import java.io.FileOutputStream;
import controller.MemberController;
import model.Member;




public class File {

	//�ʵ�
	private static String memberpath =
			"D:/work0928/Java_Project001/src/database/memberlist.txt";
	
	//�����ϱ� �޼ҵ�
	public static boolean filesave (int type) {
					//1. type : 1. ȸ������ 2. �Խù� ���� 3. �������
		try {	
			FileOutputStream fileOutputStream = null; //����

			if(type ==1 ) { //ȸ������
				//1. ���ϰ�ü�� ��� ����
				fileOutputStream = new FileOutputStream(memberpath);
				//2. �ݺ������̿��� ȸ������Ʈ���� �ϳ��� ȸ�� ��������
				for(Member member : MemberController.memberList) {
					String outString = member.getId()+","+member.getPassword()+","+ 
										member.getName() + "," + member.getEmail() + ","+ 
										member.getPoint() + "\n";
					
					//4. ����Ʈ�� ��������
					fileOutputStream.write(outString.getBytes());
				}
				//5. ��Ʈ�� ����� �ʱ�ȭ
				fileOutputStream.flush(); // ���� ��Ʈ�� �� �����ϴ� ����Ʈ ����
				fileOutputStream.close(); // ���� ��Ʈ�� �ݱ�
			
			return true; //����ó�� ����
			}
		}catch (Exception e) {
			System.out.println("[�˸�] : ���� ���� ���� �߻� [ �����ڿ��� ���� ]");
		}
		return false;
	}
	//�ҷ����� �޼ҵ�
	public boolean fileload(int type) {
		return true; // ���� �ҷ����� ������
	}
}
