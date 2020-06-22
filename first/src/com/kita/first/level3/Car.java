package com.kita.first.level3;
// Object : car. 하면 나오는 모든 method 들을 불러올수 있게 함.
public class Car extends Object {
	String name;
	String color;
	int cc;
	
	// int의 객체화 : Integer (Integer.parseInt 숫자 -> 문자열) 쓰기 위해 만들어짐.
	// 자료저장용으로는 쓸 수 있으나, 잘 안쓰임.
	// this 자기자신 주소값
	// super 부모의주소값
	Car() {		
        this("소나타","흰색",2500);	
        /* this.name = "소나타"
         * this.color = "흰색"
         * this.cc = 2500 */
	}	
	
	public Car(String name) {
		this(name, "검정색", 3000);	// 밑에 method 를 호출하러 감.		
	}
	// 이쪽으로 다 몰아주고 검사하면 됌.
	public Car(String name, String color, int cc) {
		super();
		// super 위에 ㅇ ㅏ무것도 못쓰게 되어있음.
		// 이걸 안쓰면 컴파일러가 자동생성하게 함.
		// this(name, color, cc); 를 하면 public Car(String name, String color, int cc)를 불러오기 때문에
		// 무한 반복이 됌.
		this.name = name;
		this.color = color;
		this.cc = cc;
	}
	public void introduceMyCar() {
		System.out.printf("%s의 컬러는 %s이고, %dcc이다.\n", name, color, cc);
	}
}
