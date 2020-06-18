package com.kita.first.mission;

public class GreatMission3 {
	public static void main(String[] args) {
		int[][] arr = {
				{90, 100, 88}, //영어점수
				{34, 99, 45}, //수학점수
				{98, 65, 87} //국어점수
		};		
		String[] clsArr = {"영어", "수학", "국어"};
		int[] sumArr = new int[clsArr.length]; // 합계 점수 저장용 array
		
		for(int i = 0; i < arr.length; i++) {						
			for(int j = 0; j < arr[i].length; j++) {//			
				sumArr[i] += arr[i][j];
			}			
		}
		int totalSum = 0;
		int totalCnt = 0;
		
		for(int i = 0; i < sumArr.length; i++) {
			System.out.printf("%s합계: %d, 평균: %.1f\n", clsArr[i], sumArr[i], (float)sumArr[i]/arr[i].length);
			totalSum += sumArr[i];
			totalCnt += arr[i].length;			
		}
		System.out.printf("전체합계: %d, 평균: %.1f\n", totalSum, (float)totalSum/totalCnt);	
	}
}

