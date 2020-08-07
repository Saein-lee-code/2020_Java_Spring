package com.koreait.board.common;

public class Utils {
	public static int parseStrToInt(String s, int n) {
		try {
			return Integer.parseInt(s);
		}catch(Exception e) {
			return n;
		}
	}
}
