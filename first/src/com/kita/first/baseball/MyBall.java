package com.kita.first.baseball;

import java.util.Arrays;
import java.util.Scanner;

public class MyBall {
	private int[] myArr;
	private Scanner scan;
	MyBall(int n){
		init(n);		
	}
	
	private void init(int n) {
		myArr = new int[n];	
	}
	int get(int n) {
		return myArr[n];
	}
	public void setNumbers() {
		// System.out.println("\n숫자를 입력하세요");
		scan = new Scanner(System.in);	
		for(int i=0; i<myArr.length; i++) {
			System.out.printf("숫자 %d: " , i+1);
			String val = scan.nextLine();			
			try {
				myArr[i] = Integer.parseInt(val);
				// 숫자는 숫자로 변형 해주고, 문자는 catch로 감.
				// 문자가 들어오면 밑에 myArr[i] = 0이 됌.
			}catch(Exception e) {
				i--;
				System.out.println("숫자만 입력하세요.");
				continue;
			}			
			if(myArr[i] < 1 || myArr[i] > 9) {
				i--;
				System.out.println("1~9값만 입력하세요.");
				continue;
			}
			for(int z=0; z<i; z++) {				
				if(myArr[i] == myArr[z]) {
					i--;
					System.out.println("중복된 값이 존재합니다.");
					break;
				}
			}					
			
			System.out.println(Arrays.toString(myArr));			
		}		
	}
	int size() {		
		return myArr.length;
	}	
}

