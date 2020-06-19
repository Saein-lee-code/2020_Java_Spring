package com.kita.first.level2;

import java.util.Arrays;

public class ArrCopy {
	public static void main(String[]args) {
		int[] arr = {2,4,6,7,10};
		int[] arr2 = new int[arr.length];
		// arr2 = arr; 주소값 복사 
		for(int i = 0; i < arr.length; i++) {
			arr2[i] = arr[i];
		}
		// data 복사
		// 이렇게 안하면 arr[2] = 66; 하면 arr[2] 도 66으로 바뀜.
		System.out.println(arr == arr2); // 주소값 비교 
		System.out.println("arr : " + Arrays.toString(arr));		
		System.out.println("arr2 : " + Arrays.toString(arr2));			
	}
}
