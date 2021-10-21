package database;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import controller.MemberController;
import model.Member;




public class File {

	//�ʵ�
	private static String memberpath =
			"C:/Users/505/git/Ezen_2/Java_Project001/src/database/memberlist.txt";
	
	//�����ϱ� �޼ҵ�
	public static boolean filesave (int type) { //[���� �����ϴ� �޼ҵ�] �������� �޼ҵ尡 �ƴ� �ϳ��� �޼ҵ�� �����ϴ� ���
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
	public static boolean fileload(int type) {
		try {
		FileInputStream fileInputStream = null; // ����
		
			if(type == 1) {
				//1. �Է� ��Ʈ�� ��� ����
				fileInputStream = new FileInputStream(memberpath);
				
				//2. ��Ʈ�� (���� : ����Ʈ) ����Ʈ �迭 ����
				byte[] bytes = new byte[10000]; //10kb ����
				
				//3. �Է½�Ʈ������ ����Ʈ �о�ͼ� �迭�� ����
				fileInputStream.read(bytes);
				
				//4. ����Ʈ �迭 -> ���ڿ� ��ȯ
				String instring = new String(bytes); //-2 ���ص� �Ǵ� ����? �� �������� �Ŷ�
				
				//5. ȸ�� �и��ϱ� \n
				String[] members = instring.split("\n"); //\n���н� ���� ȸ���� �߰���
				
				//6. �ݺ����� �̿��� ȸ���� �ʵ� �и��ϱ�,
				//for(String temp : members) { �Ʒ� for���� ��������
					//temp.split(",")
				//}
				for(int i = 0; i<members.length-1; i++) { //-1 �ϴ� ����? ����ȸ�� �����Ϸ���
					
					//7. ȸ���� �ʵ� �и�
					String [] field = members[i].split(",");
					
					//8. �и��� �ʵ带 ��üȭ [ point �ʵ�� int ������ ��ȯ : String -> int() ]
					Member member = new Member(field[0], field[1], field[2], field[3], Integer.parseInt(field[4]));
						//static�� �Ⱦ��� ��ü ���� �޸�(new ������)���� �ҷ����� ���̰�  //static�� ���� �ش� Ŭ������.��ü��(�޼ҵ��)���� �ҷ��� �� �ִ�.  
					
					//9. �� ��ü�� ����Ʈ�� ����
					MemberController.memberList.add(member);
				}
				fileInputStream.close(); // ��Ʈ�� �ݱ�
				return true; // ���ϰ� ���
			}
			 if(type == 2) {}
			 if(type == 3) {}
			}
			catch (Exception e) {
				System.out.println("[�˸�] : ���� ���� ���� �߻� [ �����ڿ��� ���� ]");	
			}
			return false; // ���� �ҷ����� ������
	}
}
