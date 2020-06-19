package com.kita.first.level2;

public class Method2 {
	// 외부에서 parameter 에 3이나 4를 넣으면
	// 게임을 3번이나 4번을 돌릴수있게 함
	// 내부에서 수정을 할 필요가 없음.
	// *javac Method.2java 3 4 이런식으로 쓰면 main method가 호출 됌.
	// args[0] : 3 args[1] : 4
	// *Coverage configurations -> argument -> 값 넣기 3 4 이런식으로
	// 위에 두 줄은 같은 말임.
	
	
	public static void main(String[] args) {
		System.out.println("args.length: " + args.length);
		for(int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}
	}
}
