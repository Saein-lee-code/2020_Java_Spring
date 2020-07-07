package com.koreait.second.blackjack;

import java.util.LinkedList;
import java.util.Scanner;

public class Gamer {
	private LinkedList<Card> arr;
	Scanner scan = new Scanner(System.in);
	// generic <Card> 차이?
	// 형변환 불필요
	// 객체 생성이 가능한 타입에 대해서만 제네릭 사용이 가능하다.
	// 
	public Gamer() {
		arr = new LinkedList();
	}
	
	// 추가로 카드를 뽑는다
	// 뽑은 카드를 소유한다	
	public void receiveCard(Card card) {		 
		 arr.add(card);		 	 
	}
	public void openCards() {		
		// Card[] arr = { ... } arrylist for문
		for(Card i: arr) {
			System.out.println(i);			
		}
		System.out.println("총 점수: " + getTotalScore());
	}	
	
	// 나눈 이유: 확실히 한 method는 하나의 function만 한다.
	public int getTotalScore() {
		int total = 0;
		for(Card i:arr) {
			total += i.getScore();
		}
		return total;
	}
	
	public void moreCards(CardDeck cd){				
		while(true) {			
			System.out.println("카드를 더 받겠습니까?");
			String answer = scan.next();			
			if("y".equals(answer)) {
				receiveCard(cd.getCard());				
			}else {
				System.out.println("카드를 받지 않았습니다.");
				break;				
			}
			openCards();						
		}		
	}
	

}
