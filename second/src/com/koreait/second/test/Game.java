package com.koreait.second.test;

import java.util.ArrayList;
import java.util.List;

public class Game {	
	private final static String[] PATTERNS = {"SPADES", "DIAMONDS", "HEARTS", "CLUBS"};	
	private static List<Card> arr = new ArrayList<Card>();	
	
	public static void main(String[] args) {
		for(int i=0; i < PATTERNS.length; i++) {
			for(int z=1; z<=13; z++)
				arr.add(new Card(PATTERNS[i], getDeno(z)));			
		}	
		for(int i=0; i < arr.size(); i++)
			System.out.println(arr.get(i));		
	}
	
	private static String getDeno(int z) {
		switch(z) {
			case 1: return "A";
			case 11: return "J";
			case 12: return "Q";
			case 13: return "K";
			default:
				return String.valueOf(z);
		}
	}
	
	@Override
	public String toString() {
		String str ="";
		for(Card i:arr) {
			str += i + "\n";			
		}
		return str;
	}
	
}
