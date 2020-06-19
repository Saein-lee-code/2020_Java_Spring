package com.kita.first.mission;

import java.util.Scanner;

public class MethodMission1 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);		
		System.out.print("단수를 입력하세요: ");		
		int temp = scan.nextInt();
		scan.close();

		dan(temp);
		System.out.println();
		// overloading
		// 똑같은 method를 여러개 만들 수 있는 것. (parameter가 달라져야 함)
		// dan(2) dan(2, 4) 2단부터 4단 return 타입과는 전혀 관련없다. parameter하고만 관련 있음.
		// dan(int , string) 과 dan (string, int)는 다름.		
		 dan(2, 4);
		
		// overriding
	}
	public static void dan(int s, int e) {
		System.out.printf("%d단 부터 %d단 까지\n", s, e);
		for(int i = s; i < e + 1; i++) {			
			dan(i);					
		}		
	}
	
	public static void dan(int n1) {
		int range = 10;
		System.out.printf("%d단\n", n1);
		for(int i = 1; i < range; i++) {			
			System.out.printf("%d * %d = %d\n", n1, i, n1 * i );				
		}
		System.out.println();
	}
}
