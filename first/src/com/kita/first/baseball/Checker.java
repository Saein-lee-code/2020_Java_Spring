package com.kita.first.baseball;

public class Checker {
	static int s, b, o = 0;
	static Baseball gameArr;
	static MyBall myArr;
	// s:0 / b:0/ O:0
	// 성공하면 false, 성공전이면 true
	public static boolean check(int gameCnt, Baseball b1, MyBall b2) {		
		gameArr = b1;
		myArr = b2;		
		for (int i = 0; i < gameArr.size(); i++) {
			for (int j = 0; j < myArr.size(); j++) {
				if(gameArr.get(j) == myArr.get(i)) {
					if(i == j) s++;
					else b++;
				}				
			}
		}
	System.out.printf("S:%d B:%d O:%d\n\n", s, b, gameCnt-s-b);	
	return false;
	}		
	
}
