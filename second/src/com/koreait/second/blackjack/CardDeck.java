package com.koreait.second.blackjack;

import java.util.List;
import java.util.ArrayList;

public class CardDeck {
	// List interface(부모 역할) ArrayList 자식/ LinkedList 
	private List<Card> arr = new ArrayList();
	public CardDeck() {
		init();
	}
	// 조커를 제외한 52장
	/* String pattern = Card.PATTERNS[i];
	 * String deno = getDeno(z);
	 * int score = getScore(z);
	 * Card c = new Card(pattern,deno,score);
	 * arr.add(c);*/
	private void init() {
		for(int i=0; i<Card.PATTERNS.length; i++) {
			for(int z=1; z<=13; z++) {				
				arr.add(new Card(Card.PATTERNS[i], getDeno(z), getScore(z)));								
			}			
		}		
	}
	private String getDeno(int num) {
		switch(num) {
		case 1: return "A";
		case 11: return "J";
		case 12: return "Q";
		case 13: return "K";
		default: return String.valueOf(num);
		}
	}	
	private int getScore(int score) {
		return score > 10 ? 10 : score;
	}
//	수정 삭제가 많다 linked array & array list 쓰임새 다름
	public Card getCard() {
		int rIdx = (int)(Math.random() * arr.size());
		return arr.remove(rIdx);	 // 삭제하는 동시에 주소값도 return
	}
	@Override
	public String toString() {
		String str = "";
		for(Card i: arr) {
			//System.out.println(i);
			str += i + "\n";
		}
		return str;
	}

}
