package com.kita.first.level3;

public class Car {
	String name;
	String color;
	int cc;
	// int의 객체화 : Integer (Integer.parseInt 숫자 -> 문자열) 쓰기 위해 만들어짐.
	// 자료저장용으로는 쓸 수 있으나, 잘 안쓰임.
	Car() {		
		this("소나타","흰색",2500);				
	}	
	
	public Car(String name, String color, int cc) {
		super();
		this.name = name;
		this.color = color;
		this.cc = cc;		
	}
	public void introduceMyCar() {
		System.out.printf("%s의 컬러는 %s이고, %dcc이다.\n", name, color, cc);
	}
}
