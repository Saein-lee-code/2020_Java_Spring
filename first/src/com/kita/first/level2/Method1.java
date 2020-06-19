package com.kita.first.level2;
// 라이브러리 만들때
// .java를 다 모아서
// 압축해서 -> .jar 이되면  
// JRE System Library 에 넣어서 쓴다.

public class Method1 {
	// String[] args (parameter 매개변수
	// 
	public static void main(String[] args) {
		// method1 에 속해있는 sum을 부르기때문에 호출해줄 필요 없음.
		sum(1, 3);
		
		// sum method에 static을 빼면
		// Method1 me1 = new Method1();
		// me1.sum(1,3) 로 호출 해야함.
		// 왜냐하면 static을 빼면 메모리에 할당이 안되기 때문에
		// 객체화 (new Method1)을 통해서 메모리에 할당을 시키고
		// sum을 불러 올 수 있음.
		minus(20, 5);
		
		// error. 왜냐하면 sum()과 minus 는 data를 회신하지 않기때문에 더할수 없다.
		// System.out.println(sum(1, 3) + minus(20, 5));
	}
	// sum
	public static void sum(int n1, int n2) {
		// sustem.out에 속해있는 println method 호출
		System.out.println(n1 + n2);
		// 호출을 할 순있으나 무한 루프임..
		main(null);
	}
	// minus
	public static void minus(int n1, int n2) {
		System.out.println(n1 - n2);
	}
}
