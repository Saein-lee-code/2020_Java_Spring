package com.kita.first.level5;

public class ClassB extends ClassA{
	public ClassB() {
		super();
		System.out.println("나 class B.");
	}
	@Override
	void print() {
		System.out.println("B");
	}
	void showMe() {
		System.out.println("B의 showMe");
	}
}
