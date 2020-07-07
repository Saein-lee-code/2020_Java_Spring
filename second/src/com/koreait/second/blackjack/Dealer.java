package com.koreait.second.blackjack;

public class Dealer extends Gamer {	
	public void moreCards(CardDeck cd) {
		int stopPoint = 17;
		while(getTotalScore() < stopPoint) {
			receiveCard(cd.getCard());
		}
	}	
}

//int totalPoint = getTotalScore();		
//while(true) {				
//	if(totalPoint <= 17) {
//		receiveCard(cd.getCard());
//		totalPoint = getTotalScore();	
//		
//	}else
//		break;		
//}
//openCards(); 