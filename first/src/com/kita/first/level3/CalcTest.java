package com.kita.first.level3;

public class CalcTest {
	public static void main(String[] args) {
		System.out.println(Calc.sum(10,20)); // 30
		
		Calc calc1 = new Calc();
		calc1.n1 = 20;
		calc1.n2 = 30;
		System.out.println(calc1.sum()); // 50		
	}
}
