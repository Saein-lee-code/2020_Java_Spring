package com.kita.first.level6;

public class PlayerTest {
	public static void main(String[] args) {
		// Player player = new Player(); //abstract class 이므로.
		CDDPlayer player = new CDDPlayer();
		player.play();
		player.ff();
		
		Player player2 = new DVDPlayer();		
		player2.play();
	}
}
