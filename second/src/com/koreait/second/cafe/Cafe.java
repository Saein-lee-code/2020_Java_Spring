package com.koreait.second.cafe;

public class Cafe {
	public static void main(String[] args) {		
//		setter 가 없으면 객체 생성후에 값을 넣을 수 없다.
//		MenuItem mi = new MenuItem("아메리카노", 1500);
//		MenuItem mi2 = new MenuItem("카푸치노", 2000);
//		System.out.println(mi);
//		System.out.println(mi2);
		
//		Menu m = new Menu();		
//		m.showMenu();
//
		Menu menu = new Menu();
		Customer cus = new Customer();				
		Barista bas = new Barista();
		MenuItem choiceMenu = cus.order(menu);
		// Coffee coffee = new Coffee(choiceMenu);
		Coffee coffee = bas.makeCoffee(choiceMenu);
		cus.drinkCoffee(coffee);		
		
	}
}
