package com.koreait.second.blackjack;

public class Game {
	public static void main(String[] args) {		
		CardDeck cd = new CardDeck();
		Dealer dealer = new Dealer();
		Gamer gamer = new Gamer();
		
		// 딜러 2장 , 게이머 2장
		for(int i=0; i<2; i++) {
			dealer.receiveCard(cd.getCard());
			gamer.receiveCard(cd.getCard());
		}
		dealer.moreCards(cd);
		gamer.moreCards(cd);
		dealer.openCards();	
		Rule.whoIsWin(gamer, dealer);
		
	}
}
