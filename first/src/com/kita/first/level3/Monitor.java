package com.kita.first.level3;

public class Monitor {
	// 값이 하나밖에 저장이 안된다.
	// 객체마다 값은 값을 저장할때 씀. (다른 경우는 객체화해야되서 static쓰면 안됌)
	// static변수는 딱 하나만 만들어짐.
	// 객체가 여러개여도 static변수를 하나만 가리킴.
	// 효율이 좋음.
	static String brand; // 겍체화 안해도 쓸 수 있음.
	int inch; // 객체화 할때 사용 할 수 있음.	

	/* Monitor(){
	 
		// super();
		
	}
	void display() {
		System.out.printf("brand: %s, inch: %d\n",
				this.brand, this.inch);
	}
	static void displayInch() {
		System.out.println(inch);
		// 사용 할 수 없음. 사용하려면 객체화 해야함.
		// inch는 static이 아니라서 메모리에 할당 되어 있지 않음.
		// method는 static이라서 쓸 수 없음.
		// 논리적으로 맞지 않은 method.
		// static이 아닌 멤버필드를 사용하는 method는 static이면 안됌.
		System.out.println(brand);
		// method가 static이 아닐때,
		// brand는 이미 메모리에 할당 되었기 때문에 쓸 수 있음.
		// 왜냐하면 static이 안붙어 있으면
		// 이 method는 결국 객체화할때 쓸 수 있기때문에
		// 그 말은 메모리를 할당한다는 의미 이고,
		// static 멤버필드도 쓸 수 있다.
	}
	// Monitor.displayBrand()
	static void displayBrand() {
		System.out.println(brand);
	}
	*/
}
