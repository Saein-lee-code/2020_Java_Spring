package com.kita.first.level4;

public class Bird extends Animal {	
//	//자동 생성된 생상자
//	private Bird() {
//		// super(); //animal 에 기본생성자 없음-> error
//		//부모에 있는 생성자를 호출
//		super("","");
//	}
	protected Bird(String s){
		super(s ,"조류");	
		System.out.println(s);
	}
	void flying() {
		System.out.println("훨~훨~");
	}
	//overriding
	@Override
	void crying() {
		super.crying();
		System.out.println("짹짹");
	}
}
