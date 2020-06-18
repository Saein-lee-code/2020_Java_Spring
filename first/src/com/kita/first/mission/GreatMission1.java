package com.kita.first.mission;

import java.util.Scanner;

public class GreatMission1 {
	public static void main(String[] args) {
		/* 랜덤한 중복되지 않은 숫자 3개 1~9
		 * 숫자도 맞추고 자리도 맞추면 S
		 * 자리는 틀렸지만 숫자는 맞추면 B
		 * 나머지 O
		 * */
		final int LEN = 3;
		int[] rArr = new int[LEN];
		int[] myArr = new int[LEN];	
		int count = 0;
		Scanner scan = new Scanner(System.in);
				
		// rArr에 중복 되지 않는 랜덤 숫자 생성
		for(int i=0; i < rArr.length; i++) {
			rArr[i] = (int)(Math.random() * 9) + 1;
			for(int j=0; j<i; j++) {
				if(rArr[i] == rArr[j]) {
					i--;
					break;
				}
			}			
		}
		 
		int scoreS = 0;
		int scoreB = 0;
		
		while(true) {
			System.out.printf("%d번째 야구게임 시작!\n\n", count + 1);
			// myArr 에 input 3개 넣기
			for(int i=0; i < myArr.length; i++) {
				System.out.print("값" + (i + 1) + ": ");
				int input = scan.nextInt();
				myArr[i] = input;
			}
			for(int i=0; i < myArr.length; i++) {		
				for(int j=0; j < rArr.length; j++) {
					// System.out.print(rArr[j] + " " + myArr[i]);				
					if(rArr[j] == myArr[i]) {						
						if(i == j) {
							scoreS++;							
						}else {
							scoreB++;
						}					
					}
				}		
			}
			if(scoreS == myArr.length) {
				break;
			}
			
			System.out.printf("S:%d B:%d O:%d\n\n", scoreS, scoreB, LEN - scoreS - scoreB);
			count++;
		}	
	}
}
