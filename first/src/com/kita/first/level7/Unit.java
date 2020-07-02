package com.kita.first.level7;

public class Unit {
	public final String NAME;
	public final int MAX_HP;
	private int current_hp;
	// final은 값을 무조건 넣어야함.
	// 그래서 public Unit(){} 오류가 남.
	//수정할수 없게함
	public Unit(String name, int max_hp) {
		this.NAME = name;
		this.MAX_HP = max_hp;
		this.current_hp = max_hp;
	}
	//기본생성자 없다.
	public int getCurrent_hp() {
		return current_hp;
	}
	public void getDamage(int damage) {
		current_hp -= damage;
	}
	public void setCurrent_hp(int hp) {
		this.current_hp = hp;
	}
	public void move(int x, int y) {
		System.out.printf("x:%d, y:%d 좌표로 이동\n", x, y);
	}
	@Override
	public String toString() {
		// return NAME + ", " + "현재 Hp: " +  current_hp;	
		// return String.valueOf(NAME) + ", 현재 HP: " + String.valueOf(current_hp);
		return String.format("%s , 현재HP: %d", NAME, current_hp);
	}
	
}
 