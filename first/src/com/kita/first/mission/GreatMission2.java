package com.kita.first.mission;

import java.util.Scanner;

public class GreatMission2 {
	/*
	 * 사이다 500원
	 * 콜라 600원
	 * 환타 700원
	 * 마운틴듀 800원
	 * 초코우유 400원
	 * 바나나우유 400원
	 * 메뉴를 선택하세요
	 * 종류 0*/
	public static void main(String[] args) {		
		Scanner scan = new Scanner(System.in);
		boolean isTrue = true;
		int total = 0;

		String[] drinks = {"사이다", "콜라", "환타", "마운틴듀", "초코우유", "바나나우유"};		
		int[] price = {500, 600, 700, 800, 400, 400};
		
		System.out.print("-메뉴-\n");
		for(int i = 0; i < drinks.length; i++) {			
			System.out.printf("%d. %s (%s원)\n", i+1, drinks[i], price[i]);
		}		
		System.out.println();
		while(isTrue) {
			System.out.print("메뉴를 선택하세요:종료(0)");
			int n = scan.nextInt();		
			if(n <= drinks.length && n >= 0) {
				if(n != 0) {
					total += price[n-1];
					System.out.print(drinks[n-1] + " (" + price[n-1] + "원)\n");
				}else
					isTrue = false;
			}else
				System.out.println("잘못 입력!");					
		}			
		System.out.printf("총 %d원 사용. 종료!", total);
		scan.close();
	}
}

