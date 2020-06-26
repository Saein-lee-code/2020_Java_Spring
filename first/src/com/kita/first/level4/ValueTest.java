package com.kita.first.level4;

public class ValueTest {
	public static void main(String[] args) {
		String str = new String("A");
		String str2 = new String("A");
		System.out.println("1: " + (str == str2)); // 주소값 비교
		System.out.println("2: " + str.equals(str2)); // 값 비교
		
		Value val1 = new Value(1);
		Value val2 = new Value(1);
		
		System.out.println("3: " + (val1 == val2));
		// 오브젝트로 상속받는 equals
		// equals는 기본으로 주솟값비교인데
		// string은 부모것을 안쓰고 string overriding 한 equals를 써서
		// true이고,
		// value 는 equals 를 overriding 안했기 때문에
		// 
		System.out.println("4: " + val1.equals(val2)); 
	}
}
