package com.kita.first.level3;

public class BoardTest {
	public static void main(String[] args) {		
		// bv1.title = "test"; 같은 package 지만, private으로 선언 했기 때문에 값을 넣을 수가 없다.
		// 값넣기 1.생성자 2.method
		// 값불러오기 1. method
		
		BoardVO bv1 = new BoardVO("Test1", "This is test", 1);						
		BoardVO bv2 = new BoardVO();		
		bv2.setTitle("Test2");
		bv2.setContent("This is test2.");
		bv2.setWriteId(2);
		//static 으로 만들어 줘야함
		display(bv1);
		display(bv2);
	}	
	public static void display(BoardVO temp) {
		System.out.printf("Title: %s\n" + "Content: %s\n" + "WriteID: %d\n\n", 
				temp.getTitle(), temp.getContent(), temp.getWriteId());		
	}
}
