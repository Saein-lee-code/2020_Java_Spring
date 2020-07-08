package com.koreait.second.cafe;

public class Cafe {
	public static void main(String[] args) {		
//		setter 가 없으면 객체 생성후에 값을 넣을 수 없다.
//		MenuItem mi = new MenuItem("아메리카노", 1500);
//		MenuItem mi2 = new MenuItem("카푸치노", 2000);
//		System.out.println(mi);
//		System.out.println(mi2);
		
		Menu m = new Menu();		
		m.showMenu();
		
		MenuItem mi = m.choose(0);
		System.out.println(mi); 
		// 아메리카노, 1500원 객체가 넘어와야함
		
	}
}
