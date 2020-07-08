package com.koreait.second.cafe;

import java.util.ArrayList;
import java.util.List;

// 클래스 : 멤버필드 + 멤버 메소드
// 멤버 필드 (전역변수) >> 속성, 값 저장 (이름 나이 이런것) 
// 멤버 메소드 >> 생성자는 메소드의 한 부분

public class Menu {
	private List<MenuItem> arr = new ArrayList();	
	public Menu() {
		init();
	}	
	private void init() {
		arr.add(new MenuItem("아메리카노" , 1500));
		arr.add(new MenuItem("카푸치노" , 2000));
		arr.add(new MenuItem("카라멜 마키아또" , 2500));
		arr.add(new MenuItem("에스프레소" , 2500));
//		for(int i=0; i < MenuItem.COFFEE.length; i++) {
//			arr.add(new MenuItem(MenuItem.COFFEE[i], MenuItem.PRICE[i]));
//		}		
	}
	public void showMenu() {
		System.out.println("Menu");
		for(int i=1; i<=arr.size(); i++) {
			System.out.println(i + ". " + arr.get(i-1));
		}		
	}
	
	public MenuItem choose(int i) {
		return arr.get(i);
	}
	
	@Override
	public String toString() {
		String str = "";
		for(MenuItem i : arr) {
			str += i + "\n";
		}
		return str;
	}
}
