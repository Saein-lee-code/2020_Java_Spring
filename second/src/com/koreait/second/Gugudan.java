package com.koreait.second;
// 자바 파일명이랑 같은 이름의 클래스만 public을 붙일 수 있음.
public class Gugudan {
	public static void main(String[] args) {
		gugudan(2, 5);
		int sum = sum(1,2);
		System.out.printf("첫번째 sum값 : %d\n", sum);
		sum = sum(1,2,3);
		System.out.printf("두번째 sum값 : %d\n", sum);
//		sum = sum(1,2,3,4,5,6,7,8,9,10,11,12);	
//		System.out.printf("세번째 sum값 : %d\n", sum);
		int[] arr = new int[12];
		for(int i=0; i<arr.length; i++) {
			arr[i] = i+1;
		}
		sum = sum(arr);
		System.out.println("sum arr 합: " + sum);
		
		int result = Utils.parseStringToInt("10",0);
		System.out.printf("첫번째 result: %d\n", result);
		result = Utils.parseStringToInt("aa10", 1);
		System.out.printf("두번째 result: %d\n", result);
		result = Utils.parseStringToInt("aa10");
		System.out.printf("세번째 result: %d\n", result);
		result=Utils.parseStringToInt("9");
		System.out.printf("네번째 result: %d\n", result);
		
		int[] arr2 = {4,5,11,223,3,10};
		Utils.sortASC(arr2);
		Utils.printArr(arr2);
		Utils.sortDESC(arr2);
		Utils.printArr(arr2);
		
		int[] rArr = Utils.createRandomArr(1,9,5);
		Utils.printArr(rArr);
		rArr = Utils.createRandomArrNoDuplication(5,15,13);
		Utils.printArr(rArr);
		rArr = Utils.createRandomArrNoDuplication(5,15,11);
		Utils.printArr(rArr);
		String star = makeStarTriangle(5);
		System.out.println(star);
		
	}
	public static String makeStarTriangle(int n) {
		String s = "";
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=i; j++) {
				s += "*";				
			}
			s += "\n";
		}
		return s;		
	} 
	public static void gugudan(int s, int e) {
		for(int k = s; k <= e; k++) {
			System.out.printf("-----%d단-----\n", k);
			for(int i=1; i <= 9; i++) {
				if(k*i % 2 == 0) {
					System.out.printf("%d * %d = %d\n", k, i, k*i);
				}
			}			
		}		
	}
	
	public static int sum(int n1, int n2) {	return n1+n2; }	
	public static int sum(int n1, int n2, int n3) {	return n1+n2+n3; }	
	public static int sum(int ...arr) {
		int total = 0;
		for(int i=1; i<=arr.length; i++) {
			total += i;
		}
		return total;
	}
}

class Utils {	
	public static int parseStringToInt(String s, int n) {
		int temp = 0;
		try {
			 temp = Integer.parseInt(s);
		}catch(Exception e){
			temp = n;
		}
		return temp;		
	}
	public static void printArr(int[] arr) {
		try {
			System.out.print("[");
			for(int i=0; i<arr.length; i++) {
				System.out.print(i == arr.length-1? arr[i] : arr[i] + ", ");
			}
			System.out.print("]\n");
		}
		catch(Exception e){
			System.out.println("arr is null]");
		}		
	}
//	public static int[] sortASC(int[] arr) {	
		//4,5,11,223,3,10 6
//		for(int i=arr.length - 1; i>0; i--) {
//			for(int j=0; j<i; j++) {
//				if(arr[j] > arr[j+1]) {
//					int n = arr[j];
//					arr[j] = arr[j+1];
//					arr[j+1] = n;					
//				}
//			}
//		}		
//		return arr;
	public static void sortASC(int[] arr) {
		sort(arr, true);
	}
//	public static int[] sortDESC(int[] arr) {		
//		for(int i=arr.length - 1; i>0; i--) {
//			for(int j=0; j<i; j++) {
//				if(arr[j] < arr[j+1]) {
//					int n = arr[j];
//					arr[j] = arr[j+1];
//					arr[j+1] = n;					
//				}
//			}
//		}		
//		return arr;
	public static void sortDESC(int[] arr) {
		sort(arr, false);
	}
	public static void sort(int[] arr, boolean asc) {
		for(int i=0; i<arr.length-1; i++) {
			for(int j=i+1; j<arr.length; j++) {
				if(asc == (arr[i] > arr[j])) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}
	public static int parseStringToInt(String s) {
		int temp = 0;
		try {
			 temp = Integer.parseInt(s);
		}catch(Exception e){
			temp = 0;
		}
		return temp;		
	}
	
	public static int[] createRandomArr(int s, int e, int n) {
		int[] temp = new int[n];
		for(int i=0; i<temp.length; i++) {
			temp[i] = (int) (Math.random() * e) + s;
		}
		return temp;
	}
	public static int[] createRandomArrNoDuplication(int s, int e, int n) {
		// 5 15 13 5~15랜덤한 값출력
		int rangeE = e - s + 1;
		int[] temp = new int[n];
		if(rangeE < n) {
			
			return null;
		}
		
		for(int i=0; i< temp.length; i++) {			
			temp[i] = (int) (Math.random() * rangeE) + s;			 
			for(int j=0; j<i; j++) {					
				if(temp[i] == temp[j]) {					
					i--;					
					break;													
				}	
			}
		}				
		return temp;
	}	
}
