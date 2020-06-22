package com.kita.first.level3;

public class MonitorTest2 {
	public static void main(String[] args) {
		Monitor.brand = "삼성";
		Monitor.displayBrand();
		// 클래스 method가 아니므로 m1.display() 이런식으로 호출해야 함.
		// static이 안붙은 method는 호출하려면 객체화가 필요하다.
		// Monitor.display();
		
		int result = Integer.parseInt("11");
		// Integer.parseInt -> 클래스변수 (static)
		// ("11") parameter로만 이용해서 작업 함.
		// 여기서 Integer.parseInt는 static임을 알 수 있다.
		
		//
	}
}
