package com.kita.first.baseball;

public class Baseball {	
	private int[] rArr;		
	public Baseball(int num) {
		init(num);
	}
	private void init(int n){	
		rArr = new int[n];	
		setRandom();
		display();
	}
	
	
	private void setRandom() {		
		for(int i=0; i<rArr.length; i++) {
			rArr[i] = (int)(Math.random() * 9) + 1;
			for(int j=0; j<i; j++) {
				if(rArr[i] == rArr[j]) {
					i--;
					break;
				}
			}
		}		
	}
	
	int get(int n) {
		return rArr[n];
	}
	
	private void display() {
		for(int i=0; i < rArr.length; i++)
			System.out.print(i==(rArr.length-1)? rArr[i] + "\n" : rArr[i] + ", ");	
		
	}
	int size() {		
		return rArr.length;
	}	
}
