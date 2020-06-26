package com.kita.first.baseball;

public class Checker {
			
	// s:0 / b:0/ O:0
	// 성공하면 false, 성공전이면 true
	public static boolean check(int gameCnt, Baseball b1, MyBall b2) {		
		int s = 0;
		int b = 0;
		for (int i = 0; i < b1.size(); i++) {
			for (int j = 0; j < b2.size(); j++) {
				if(b1.get(j) == b2.get(i)) {
					if(i == j) s++;
					else b++;
				}				
			}
		}			
	System.out.printf("S:%d B:%d O:%d\n\n", s, b, gameCnt-s-b);
									
	return s!=gameCnt;
	}
}

	

