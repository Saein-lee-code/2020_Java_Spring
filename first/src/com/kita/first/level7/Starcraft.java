package com.kita.first.level7;

public class Starcraft {
	public static void main(String[] args) {
		Marine m1 = new Marine();
		Marine m2 = new Marine();
		Medic medic = new Medic();
		FireBat fb = new FireBat();
		
//		m2.attack(m2);
//		m2.attack(m2);
		// m1 이 m2를 때림
		
		fb.attack(m1);
		fb.attack(m1);
		fb.attack(m1);
		
//		System.out.println("m1: " + m1);
//		System.out.println("m2: " + m2);
		// 마린, 현재HP :50
		System.out.println(m1);
		m1.attack(fb);
		m1.attack(fb);
		System.out.println(fb);
		
		// medic.heal(fb); 
		medic.heal(m1);
		System.out.println(m1);
		// 형변가능한가 상속하고있나?
		// true: 상속관계 false: 아무런 상속관계가 아님
//		System.out.println(fb instanceof Carable);  
//		System.out.println(m1 instanceof Carable);
		
		
	}
}
