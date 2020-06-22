package com.kita.first.level3;

public class CarTest {
	public static void main(String[] args) {
		Car car = new Car();
		//constructor 는 객체 만들때 딱 1번 생성 됌.
		
		// 객체 생성 -> 부모 불러옴(object) -> 자식 불러옴(car())
		Car car1 = new Car("그랜저1","검정색",3000);
		Car car2 = new Car("그랜저2");
		Car car3 = new Car("G80");		
		
		car.introduceMyCar();
		car1.introduceMyCar();
		car2.introduceMyCar();
		car3.introduceMyCar();
		
		System.out.println("---");
		
	}
}
