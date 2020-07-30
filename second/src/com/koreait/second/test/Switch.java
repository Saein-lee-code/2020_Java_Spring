package com.koreait.second.test;

import java.util.Scanner;
public class Switch {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		System.out.print("월을 입력하세요:");
		String temp = scan.nextLine();
		int mon = Integer.parseInt(temp);
		
		String season = "";
		scan.close();
		
		switch(mon) {
			case 1: case 2: case 12:
				season = "겨울";
				break;
			case 3: case 4: case 5:
				season = "봄";
				break;
			case 6: case 7: case 8:
				season= "여름";
				break;
			case 9: case 10: case 11:
				season = "가을";
				break;
			default:
				System.out.println("잘못된 값입니다.");				
		}
		if(mon <= 12 && mon >= 1)
			System.out.println(season + "입니다.");		
	}
}
