package com.koreait.second.cafe;

import java.util.Scanner;

public class Customer {	
	Scanner scan = new Scanner(System.in);

	public MenuItem order(Menu menu) {
//		menu.showMenu();
//		Scanner scan = new Scanner(System.in);
//		int choice = scan.nextInt();
//		while(true) {
//			System.out.print("\n몇번 커피를 드시겠습니까?");
//			if(choice > 0 && choice < 5) {				
//				return menu.choose(choice-1);
//			}
//			else {
//				System.out.println("다시 선택해 주세요");
//				choice = scan.nextInt();				
//			}				
//		}
		menu.showMenu();
		MenuItem mi = null;
		while(mi == null) {
			try {
				System.out.println("메뉴 번호를 입력해 주세요.");
				String choice = scan.nextLine();
				int choiceNo = Integer.parseInt(choice);
				mi = menu.choose(choiceNo - 1);
			}catch(NumberFormatException e) {
				System.out.println("숫자를 입력해 주세요.");
			}catch(Exception e){
				System.out.println("메뉴를 잘 못 선택하셨습니다.");
			}			
		}
		scan.close();
		return mi;
	}

	public void drinkCoffee(Coffee coffee) {
		System.out.printf("%s를 마신다 :)", coffee.getName());
	}	
}
