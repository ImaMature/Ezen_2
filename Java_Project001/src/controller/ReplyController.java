package controller;

import model.Reply;

public class ReplyController {

	//�޼ҵ�
	
	//1. ��� ����
	
	public static boolean add(int index, Reply reply) {
							//�Խù� ��ȣ, ��� ��ü
							//� �Խù��� ����� �߰��� ���� �Ǵ��ؾ��ϱ� ������ index�ʿ�.
		try {
		BoardController.boardlist.get(index).getReplylist().add(reply);
		//������Ʈ�ѷ��� ���帮��Ʈ���� ������ �Խù��� ���� ����Ʈ�� ����ͼ� ������ ���ϴ°�
		return true;
		}
		catch (Exception e) {
			
			return false;
		}
	}
}
