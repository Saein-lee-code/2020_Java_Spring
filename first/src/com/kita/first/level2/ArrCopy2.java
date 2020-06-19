package com.kita.first.level2;

import java.util.Arrays;

public class ArrCopy2 {
	public static void main(String[] args) {
		int[] arr = {2, 4, 6, 7, 10, 55};
		int[] arr2 = copyArr(arr);
		int[] arr3 = copyArr(arr);
		// 배열로 받으니까 method 도 배열로 만들어야 함.
		
		System.out.println(arr == arr2); // 주소값 비교 
		System.out.println("arr : " + Arrays.toString(arr)+ "\n주소값: " + arr);		
		System.out.println("arr2 : " + Arrays.toString(arr2)+ "\n주소값: " + arr2);
		System.out.println("arr3 : " + Arrays.toString(arr3) + "\n주소값: " + arr3);
		
		System.out.println(arr);
		for(int i=0;i<arr.length;i++) {
			System.out.println(arr[i]);
		}
	}
	// 객체를 생성하는게 아니라, 주소값을 참조하는 것.
	// public static int[] copyArr(주소값){}
	public static int[] copyArr(int ...arr) {
		int[] temp = new int[arr.length];
		for(int i=0; i<arr.length; i++) {
			temp[i] = arr[i];								
		}	
		return temp;			
	}
	// 총 객체가 3개가 생기고, temp는 method 안에서 생겼다 사라짐.
}
