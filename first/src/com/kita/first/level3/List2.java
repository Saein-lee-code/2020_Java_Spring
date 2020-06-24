package com.kita.first.level3;

import java.util.ArrayList;

public class List2 {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(2, 11); //2번째 자리에 11을 삽입한다.
		list.remove(list.size() - 1);
		//ArrayList<Integer> list = new ArrayList(); Generic
		// int val = list.get(i); 로 쓸수있음.
		
		int value = list.get(2);
		System.out.println(value);
		
		for(int i=0; i<list.size(); i++) {
			int val = (int)list.get(i);
			if(i < list.size() - 1)
				System.out.print(val + ", ");
			else 
				System.out.println(val);
		}
		// Object는 최상위 class.
		Object obj = 1;
		obj = "aaa";
		obj = 1.1;
		// 자바스크립트의 var 과 같은 역할을 할 수 있다.
		// but, 사용 할려면 형변환을 해줘야 한다.(강제 형변환)
		// System.out.println(obj + obj); 에러가 나기 때문.
 
		for(Object val : list) {
			System.out.print((int)val + ",");
		}
		/*
		 * int i = 0;
		 * for(int val : list){
		 * 	System.out.printf("i: %d, %d / ", i++ val)
		 * }*/
	}
}
