package com.kita.first.level4;

public class Value extends Object{
	private int val;

	public Value() {}
	public Value(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
		
	@Override
	 public boolean equals(Object obj){
		// 상속관계에서는 강제형변환이 가능하다. (다향성)
		Value objValue = (Value)obj;	
		return this.val == objValue.getVal();
	}	 
	@Override
	public String toString() {
		// return String.format("%,d", val); 2,000 원하는데로 format을 바꿈
		 return String.valueOf(val);
	}
}
