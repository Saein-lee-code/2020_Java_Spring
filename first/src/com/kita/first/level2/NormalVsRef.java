package com.kita.first.level2;

public class NormalVsRef {
	public static void main(String[] args) {
		int num = 10;
		System.out.println("before num: "+ num);
		changeVal(num); // ? return 값 없음.
		System.out.println("after num: " + num);
		
		int[] numArr = {10}; // 주소값을 저장 (여기 주소값에 값이 같이 포함)
		System.out.println("before num[0]: " + numArr[0]);
		changeVal(numArr);
		System.out.println("after num[0]: " + numArr[0]);
		
	}
	
	// method 가 한번 실행 되고 끝나면서, parameter 안의 변수는 사라짐.
	
	// 두개 다른 변수이고, 
	// changeVal의 num 은 main의 num 값인 리터럴 값(30)을 받음.
	
	
	
	public static void changeVal(int num) {
		num = 30;
	}
	// 두개 다른 변수이나,
	// changeVal의 numArr은 main의 numArr 주소값을 받음. 
	public static void changeVal(int[] numArr) {
		numArr[0] = 30;
	}
}
