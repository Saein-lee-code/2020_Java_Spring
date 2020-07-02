package com.kita.first.level4;

public class ValueTest2 {
	public static void main(String[] args) {
		String str = new String("안녕");
		System.out.println(str);
		// println method가 overriding 되있음
		// parameter 을 보두 string으로 변환
		System.out.println();
		
		Value val = new Value(2000);
		System.out.println(val);
		// val은 object기 때문에 toString을 자동으로 불러옴.(호출)
		// object.toString();
		
		// 모든 객체는 toString을 가지고있음 (object를 상속받으니까)
		// valueOf 와 toString은 모두 String에 있는 method
		// 
//		String strVal = String.valueOf(val);
//		String strVal2 = val.toString(); 		
		// 주소값 나오는걸 overriding 하면 위에처럼 나옴
//		System.out.println("strVal : " + strVal + 
//				"\n" + "strVal2: " + strVal2);
	}	
}
