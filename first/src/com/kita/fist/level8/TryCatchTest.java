package com.kita.fist.level8;

public class TryCatchTest {
	public static void main(String[] args) {
		meth();		
		try {
			meth2();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			div(10, 2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("dddd");
	}
	// throw쓰면 위에서 method call할때 무조건 try/catch로 감싸줘야한다.
	// throw로 던져버릴거기때문에 그 에러를 handle해줘야함.
	// 만약 throw 안해주고 안에서 try catch를 써줬다면
	// 위에서 method call 할때 try catch 써줄 필요 없음.
	public static int div(int n1, int n2) throws Exception{
		return n1 / n2;
	}
	public static void meth2() throws ClassNotFoundException {
	//public static void math2() throws Exception {
		Class.forName("dsdd");
	}
	public static void meth() {
		int result = 2;
		try {
			// result = 10 / 0;
			// 예외발생 밑에것 실행 x
//			if(result % 2 == 0) {
//				return; 
//			}
			Class.forName("dd");
		}
		catch(ClassNotFoundException e){
			
		}catch(Exception e) {
			// 예외가 터졌을 때
			e.printStackTrace();
			System.out.println("예외가 발생하였습니다.");
		}finally {
			// 무조건 실행
			// 위에 return 으로 method 가 종료 되지만
			// finally 는 무조건 실행.
			System.out.println("finally");			
		}
		System.out.println("result: " + result);
		System.out.println("종료");
	}
}
