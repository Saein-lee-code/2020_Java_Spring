package com.koreait.second.test;

public class Card {
	private final String pattern;
	private final String denomination;	
	public Card(String pattern, String denomination) {
		this.pattern = pattern;
		this.denomination = denomination;
	}
	public String getPattern() {
		return pattern;
	}
	public String getDenomination() {
		return denomination;
	}
	@Override
	public String toString() {
		return String.format("[%s, %s]", pattern, denomination);
	}
}
