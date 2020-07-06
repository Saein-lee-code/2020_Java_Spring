package com.koreait.second.blackjack;

public class Game {
	public static void main(String[] args) {
		CardDeck cd = new CardDeck();
		Card c = cd.getCard(); // 랜덤한 카드를 1장 리턴 and 카드덱에서 삭제
		// Card card = cd.getCard();
		// gamer.receiveCard(card);

		// 대문자 클래스 변수 = new .. 주소값 저장 (reference)
		// 주소값 저장안하는 type : 8개
		
		Gamer gamer = new Gamer(); // 주소값 저장( 대문자로 시작 )
		// 다른 클래스에서 불러오는 method = public 일 확률이 높다.
		// 변수 = something 이 아니면 void
		// parameter 타입 확인해서 넣기
		
		gamer.receiveCard(cd.getCard());		
		gamer.receiveCard(cd.getCard());
		gamer.receiveCard(cd.getCard());
		gamer.receiveCard(cd.getCard());
		gamer.receiveCard(cd.getCard());
		
		// System.out.println(cd);				
		gamer.openCards();
	}
}
