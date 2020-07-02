package com.kita.first.level6;

// 추상 method
// 선언만 하고 동작할진 모름
public abstract class Player {
	// 클래스 앞에 abstract를 붙임 -> 객체화를 하기 싫다는 의미. 
	public abstract void play();
	// 이걸 누가 상속받아서 쓰겠다는 의미.
	
	// 객체화가 안됌.
	// 부모역할만 한다는 의미. Player player = new CDPlayer(); 앞
	// 내 자식의 주소값을 가리키겠음.
	public void stop() {
		System.out.println("재생을 멈춥니다.");
	}	
}
abstract class CDPlayer extends Player{
	public abstract void ff();
}
// 미리 붙여놓고 이 클래스를 상속받아서 뭔갈 하겠다는 의미를 내포함.	
class CDDPlayer extends CDPlayer{
	@Override
	public void play() {
		System.out.println("CDD를 재생합니다.");
	}

	@Override
	public void ff() {
		System.out.println("ff를 재생합니다.");		
	}
}

class CDDDPlayer extends Player {
	//추상 method 는 강제 overriding
	@Override
	public void play() {
		System.out.println("CDDD를 재생합니다.");
	}
	
}

class DVDPlayer extends Player{
	@Override
	public void play() { 
		System.out.println("DVD를 재생합니다.");
		
	} 
	
}