package com.kita.first.level4;

public class Value extends Object{
	private int val;
	public Value(int val) {
		this.val = val;
	}
	public int getVal() {
		return val;
	}
	@Override
	 public boolean equals(Object obj){
		// 상속관계에서는 강제형변환이 가능하다. (다향성)
		int val = (Integer) obj;
		System.out.println(val);
		return true;
	}
	 h
}
