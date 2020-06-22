package com.kita.first.level3;

public class Calc {
	int n1, n2;
	// static int n1, n2;
	// static int sum() {}
	// 객체를 아무리 만들어도 n1, n2 값이 같다.
	static int sum(int n1, int n2) {
		return n1+n2;
		// this.n1 을 쓰려면 객체화가 되었어야 하는데
		// static 은 객체화가 되기전에 이미 메모리를 할당받음
		// return Calc.n1 + Calc.n2
	}	
	int sum() {
		return n1+n2;
	}	
}
