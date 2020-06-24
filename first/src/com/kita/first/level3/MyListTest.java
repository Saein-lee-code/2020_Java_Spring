package com.kita.first.level3;

import java.util.Arrays;

// 파일이름이랑 같은 클래스가 있어야한다.
// public 다른 패키지에서 접근 할 수 있음. (1개)
public class MyListTest {
	public static void main(String[] args) {
		MyList list = new MyList();
		list.add(10);
		list.add(15);
		list.add(20);
		list.add(30);
		list.add(1, 100);	
		
		int delVal = list.remove();
		System.out.println("delVal: " + delVal);
		
		
		int len = list.size(); // 2
		System.out.println("list size: " + len);
		int val = list.get(1); // 15
		System.out.println("arr[1] val: " + val);
		val = list.get(0); // 10
		System.out.println("arr[0] val: " + val);
		// list.remove();
		//list.arrDisplay();		
		
//		list.remove(1);
//		System.out.print("1번째 인덱스 값 삭제: ");
//		list.arrDisplay();
		for(int i=0; i<list.size(); i++) {
			System.out.print(list.get(i) + ", ");
		}
	}	
}

class MyList{
	private int[] arr;
	int count;
	
	MyList(){
		 init();
	}
	
	void init() {
		arr = new int[0];
		count = 0;
	}
	
	void add(int n) {	
		int[] temp = new int[arr.length + 1];
		for(int i=0; i < arr.length; i++) {			
			temp[i] = arr[i];
		}
		temp[arr.length] = n;
		arr = temp;		
	}
	
	void add(int index, int n) {		
		int[] temp = new int[arr.length + 1];
		temp[index] = n;
		for(int i=0; i < arr.length; i++) {
			// temp[i < index ? i : i + 1] = arr[i];
			if(i < index) {
				temp[i] = arr[i];
			}else {
				temp[i+1] = arr[i];
			}			
		}		
		arr = temp;
	}
	
	int size() {
		return arr.length;
	}
	
	int get(int n) {		
		return arr[n];
	}
	
//	void remove() {
//		int[] temp = new int[arr.length - 1];
//		for(int i=0; i < temp.length; i++) {
//			temp[i] = arr[i];
//		}
//		arr = temp;		
//	}
	int remove() {
		int del = 0;
		del = arr[arr.length-1];
		int[] temp = new int[arr.length - 1]; 
		for(int i=0; i < temp.length; i++) {			
			temp[i] = arr[i];
		}
		arr = temp;		
		return del;
	}
	
	int remove(int n) {
		int[] temp = new int[arr.length - 1];
		for(int i=0; i < temp.length; i++) {
			if(i>=n)
				temp[i] = arr[i+1];
			else
				temp[i] = arr[i];						
		}
		int del = arr[n];
		arr = temp;
		return del;
	}
	
//	void arrDisplay() {
//		System.out.println("arr list: " + Arrays.toString(arr));
//	}
}
