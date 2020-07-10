package com.koreait.second.cafe;

public class Barista {	
	public Coffee makeCoffee(MenuItem choiceMenu) {
		// type은 메소드 호출 , 호출했을 땐 객체 기준으로 
		Coffee c = new Coffee(choiceMenu);
		return c;
	}	
	
}
