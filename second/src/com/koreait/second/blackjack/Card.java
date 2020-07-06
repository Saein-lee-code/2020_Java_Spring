package com.koreait.second.blackjack;

public class Card {
	public final static String[] PATTERNS = {"스페이드", "하트", "클로버", "다이아몬드"};
	private final String pattern;
	private final String denomination;
	private final int score; 

	public Card(String pattern, String denomination, int score){		
		this.pattern = pattern;
		this.denomination = denomination;
		this.score = score;
	}
	public int getScore() {
		return score;
	}
	public String getPattern() {
		return pattern;
	}

	public String getDenomination() {
		return denomination;
	}
	
	@Override
	public String toString() {
		return String.format("[%s, %s] point: %d",  pattern, denomination, score);	
	}
}
	
