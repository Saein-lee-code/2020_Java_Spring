package com.koreait.second.blackjack;

public class Card {
	static String[] PATTERNS = {"스페이드", "하트", "클로버", "다이아몬드"};
	private String pattern;
	private String denomination;
	
	Card(String p, String d){
		super();
		this.pattern = p;
		this.denomination = d;
		// System.out.println(this.pattern + " " + this.denomination);
	}

	public String getPattern() {
		return pattern;
	}

	public String getDenomination() {
		return denomination;
	}

	@Override
	public String toString() {
		return String.format("p: %s, d: %s", pattern, denomination);	
	}
}
	
