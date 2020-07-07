package com.koreait.second.blackjack;

public class Rule extends Gamer{
	public static void whoIsWin(Gamer gamer, Dealer dealer) {
		int failPoint = 21;
		int gamerPoint = gamer.getTotalScore();
		int dealerPoint = dealer.getTotalScore();
		System.out.printf("Dealer: %d, Gamer: %d\n", dealerPoint, gamerPoint);
		if(gamerPoint > failPoint || (gamerPoint < dealerPoint)&& dealerPoint <= failPoint) {			
			System.out.println("Dealer WIN!!");
		}else if ((gamerPoint <= failPoint && gamerPoint > dealerPoint)|| dealerPoint > failPoint) {
			System.out.println("Gamer WIN!!");
		}else if(gamerPoint == dealerPoint || (gamerPoint > failPoint && dealerPoint > failPoint)) {
			System.out.println("비겼음");
		}
					
	}
}
