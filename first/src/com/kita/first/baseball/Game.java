package com.kita.first.baseball;

public class Game {
	public static void main(String[] args) {
		int gameCnt = 3;
		if(args.length != 0) {
			try {
				gameCnt = Integer.parseInt(args[0]);
			}catch(Exception e) {}
		}
		System.out.printf("%d개 숫자야구게임 Start\n", gameCnt);
		Baseball ball = new Baseball(gameCnt);
		// int val = ball.get(0);
		
		MyBall myball = new MyBall(gameCnt);
		// myball.setNumbers();
		// int val = myball.get(0);
		do {
			myball.setNumbers();
		} while(Checker.check(gameCnt, ball, myball));		
	}
}
