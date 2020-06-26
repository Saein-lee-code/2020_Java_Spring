package com.kita.first.level4;

public class AnimalTest {
	public static void main(String[] args) {
		// 객체화가 되면 무조건 생성자를 호출한다.
		
		//Animal animal = new Animal();
		//Bird bird = new Bird();
		
//		protected를 했음에도 되는ㅇ ㅣ유
		// 
		// Bird bird1 = new Bird("기러기");
		// Bird bird2 = new Bird("참새");
//		animal.crying();
//		// animal.flying(); 불러올 수 없음..
//		bird.crying();
//		bird.flying();
		
		Sparrow sparrow = new Sparrow();
		sparrow.crying(); // bird crying , bird에도 없으면animal crying
		
//		bird1.whoAmI();
//		bird2.whoAmI();
		sparrow.whoAmI();
	}
}
