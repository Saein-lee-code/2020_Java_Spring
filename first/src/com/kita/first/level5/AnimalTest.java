package com.kita.first.level5;

public class AnimalTest {
	public static void main(String[] args) {
		Animal ani = new Dog();
		ani.cry(); // 멍멍
		System.out.println("ani 주소값: " + ani);
		
		// cat 에 sleep()을 추가 할 경우,
		// ani.sleep() 을 불러올 수 없다.
		// 왜냐하면 animal에 sleep이 없기 때문 (모르기 때문)
		// 이때 ㅌㅏ입 Animal
		// 타입은 메소드를 호출 할수 있나 없나 결정.
		ani = new Cat();
		ani.cry(); // 야옹
		System.out.println("ani 주소값: " + ani);
		
		Cat cat = (Cat)ani;
		// Cat에 method를 추가할 경우 Animal 보다 method가 더많음
		// 타입은 메소드를 호출 할수 있나 없나 결정.
		// ani타입을 Animal -> Cat으로 강제 형변환.
		
		cat.cry();
		cat.sleep();
		System.out.println("cat 주소값: " + cat);
	}
}
