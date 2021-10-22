package controller;

import model.Reply;

public class ReplyController {

	//메소드
	
	//1. 댓글 저장
	
	public static boolean add(int index, Reply reply) {
							//게시물 번호, 댓글 객체
							//어떤 게시물에 댓글을 추가할 건지 판단해야하기 때문에 index필요.
		try {
		BoardController.boardlist.get(index).getReplylist().add(reply);
		//보드컨트롤러의 보드리스트에서 선택한 게시물의 리플 리스트를 갖고와서 리플을 더하는것
		return true;
		}
		catch (Exception e) {
			
			return false;
		}
	}
}
