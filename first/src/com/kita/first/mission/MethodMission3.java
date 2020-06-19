package com.kita.first.mission;

public class MethodMission3 {
	public static void main(String[] args) {
		int abs = ABS(10);
		System.out.println(abs);
		
		abs = ABS(-10);
		System.out.println(abs);
	}
	public static int ABS(int n) {
		if(n < 0) {
			return (n-(2 * n));
		}
		return n;
		// return (n < 0) ? -n : n;
	}	
}
