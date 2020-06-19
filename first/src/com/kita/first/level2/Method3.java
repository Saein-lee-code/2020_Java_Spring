package com.kita.first.level2;

public class Method3 {	
	public static int sum(int n1, int n2) {
		return n1 + n2;
		//return n1 + n2 + ""; "40"
		//return "" + n1 + n2 + ""; "3010" 
	}
	public static int minus(int n1, int n2) {
		if(n1>10)
			return n1 - n2;
		else
			return 0;
	}
	
	public static void main(String[] args) {		
		int result = sum(10, 12);
		result = minus(10, 22);
		System.out.println(result);
	}
}

