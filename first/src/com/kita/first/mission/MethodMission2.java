package com.kita.first.mission;

public class MethodMission2 {
	public static void main(String[] args) {
		// printStar(6);		
		// printStar(3);
		// printStarSquare(4);
		// printStarTriangle(5);
		printStarTriangleReverse(5);
	}
	public static void printStar(int n) {
		for(int i=1; i<=n; i++) {			
			System.out.print("*");
		}
		System.out.println();
	} 	
	public static void printStarSquare(int n) {
		for(int i=1; i<=n; i++) {
			printStar(n);			
		}
	}
	public static void printStarTriangle(int n) {
		for(int i=1; i<=n; i++) {
			printStar(i);
		}
	}
	public static void printChar(char ch, int cnt) {
		for(int i=0; i<cnt; i++) {
			System.out.print(ch);
		}
	}
	public static void printStarTriangleReverse(int n) {			
		for(int i=n-1; i >= 0; i--) {
			// i : 4 3 2 1
			printChar(' ', i);
			// * : 1 2 3 4 5
			printStar(n-i);
		}
		/*
			// 5 4 3 2 1
			for(int z=1; z<i; z++) {
				// 0-5 1-5 2-5 3-5 4-5
				// i=5 > 1234
				// i=4 > 123
				// i=3 > 12
				// i=2 > 1
				System.out.print(" ");								
			}	
			//printStar(1)* 
			//printStar(2)** 
			//printStar(3)***
			//printStar(4)****
			//printStar(5)*****
			printStar(n-(i-1));
		}
		*/
	}
}
