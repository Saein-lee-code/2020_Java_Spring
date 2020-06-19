package com.kita.first.level3;

public class TvTest {
	public static void main(String[] args) {
		Tv tv1 = new Tv();
		// type : Tv
		// tv1 = Tv타입의 객체 주소값만 저장 하겠다.
		// new: Tv를 객체화 하겠다. 
		// Tv() 기본 생성자 (자동으로 들어감)
		// Constructor 객체의 초기화를 담당.
		
		// String a = new String(); 
		// type: String
		// a = String타입의 객체 주소값만 저장 하겠다.
		// new: String을 객체화 하겠다.
		
		// tv1.name = "삼성Tv";
		System.out.println("name: " +tv1.name); // string
		System.out.println("power: " + tv1.power); // boolean
		System.out.println("channel: " + tv1.channel); // int
		System.out.println("===================");
		tv1.name = "삼성tv"; //주소값
		System.out.println("name: " + tv1.name);
		// println (주소값) -> 값 나오게 되어있음.
		tv1.changePower();		
		System.out.println("power: " + tv1.power);
		tv1.changePower();	
		System.out.println("power: " + tv1.power);
		tv1.channelUp();
		tv1.channelUp();
		tv1.channelUp();
		System.out.println("channel: " + tv1.channel);
	}
}
