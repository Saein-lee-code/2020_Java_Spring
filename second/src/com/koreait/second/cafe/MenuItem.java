package com.koreait.second.cafe;
// private 멤버필드 값넣는법
// 1. 생성자 2. 메소드(setter)

// 값 뽑는법
// 1. 메소드(getter)
public class MenuItem {
	// 전역변수
//	public final static String[] COFFEE = {"아메리카노", "카푸치노", "카라멜 마키아또", "에스프레소"};
//	public final static int[] PRICE = {1500, 2000, 2500, 2500};
	private String name;
	private int price;		
	
	// 메소드와 생성자 다른점 두가지, type x, class 명과 같아야함
	// 메소드는 대문자로 시작하면 안됌
	// parameter = 지역변수
	public MenuItem(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}
	

	
	// 실수를 줄이고자 함
	// 부모 클래스에서 있는지 확인하기위해
	@Override
	public String toString() {
		return String.format("%s - %d원", name, price);
	}
}
