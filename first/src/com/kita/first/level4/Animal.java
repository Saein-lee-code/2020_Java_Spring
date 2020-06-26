package com.kita.first.level4;

public class Animal extends Object {
	private String name;
	private String type; //포유류, 조류, 어류
	// 이렇게 생성되는걸 막음
//	 private Animal(){} 이런식으로
	//private Animal(){}
	// default 랑 같은데 + 상속관계
	// 패키지가 달라져도 상속관계이면 접근가능.
	protected Animal(String name, String type) {
		this.name = name;
		this.type = type;
	}
	void crying() {
		System.out.println("울다~~");
	}
	void eat() {
		System.out.println("먹다~~~");
	}
	//생성자를 통해서만 값을 받으면
	//setter가 없으면, 값을 바꿀 수가 없음.
	void whoAmI() {
		System.out.printf("나는 %s입니다.\n", name);
	}

}
//Object는 static이 아니기때문에 생성할때마다 계속 생김
//Obj<-Animal
//Obj<-Animal <-Bird
//bird하나 더만들면
//Obj<- Anima <-Bird
//계속 다른 값을 저장하고 싶기 때문.
//그게아니면 static이다.