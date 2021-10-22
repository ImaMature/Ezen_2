package controller;

import java.util.ArrayList;

import database.File;
import model.Board;

public class BoardController {

	// 1. 필드
	public static ArrayList<Board> boardlist = new ArrayList<>();
		// static 없을경우에 해당 필드 호출시 [ BoardController 객체 생성 ]
		// static 있을경우 해당 필드 바로호출 가능 
	
	// 2. 메소드 
	// 게시물 등록 메소드 
	public static boolean add( Board board ) {
		
		//1. 유효성검사[X]	//2. 중복체크[X]
		//3. 리스트에 저장	
		boardlist.add(board);
		
		//4. 파일 처리
		File.filesave(2); //2. 게시물 저장
		
		return true; // 등록 성공시 
	}
	// 게시물 상세페이지[조회] 메소드 
	public static Board detail( int index ) {
		try {
			Board board = boardlist.get(index);
			return board; // 검색한 게시물 반환
		}
		catch(Exception e) {
			
			return null; // 검색한 게시물 없을 경우 
		}
		
	}
	// 게시물 삭제 메소드 
	public static boolean delete( int index ) {
		return true;
	}
	// 게시물 수정 메소드 
	public static boolean update( int index , String title , String contents ) {
		return true;
	}
	
	
	
	
	
	
	
}