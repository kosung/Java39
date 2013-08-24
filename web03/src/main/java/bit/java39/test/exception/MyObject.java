package bit.java39.test.exception;

public class MyObject {
	public void m(int a) throws Exception {
		if (a < 0) {
			throw new Exception();
		} else {
			System.out.println("오호라..m()");
		}
	}
	
	public void m2(int a) throws Error {
		if (a < 0) {
			throw new Error();
		} else {
			System.out.println("오호라..m()");
		}
	}
	
	static public void main(String[] args) {
		MyObject o = new MyObject();
		
		//1. Exception을 던질 수 있는 메서드 호출
		// - 반드시 예외처리를 해야한다.
		// - RuntimeException은 Error와 마찬가지로 예외처리를 하지 않더라도 
		//   컴파일 오류가 발생하지 않는다.
		try {
			o.m(-1);
		} catch (Exception e) {
			System.out.println("오호라..Exception 예외!");
		}
		
		//2. Error를 던질 수 있는 메서드 호출
		// - Error 예외는 처리하지 않아도 컴파일 에러가 발생하지 않는다.
		// - 컴파일 에러가 발생하지 않더라도... 가능하면 오류 처리를 한다.
		//try {
			o.m2(-1);
		//} catch (Error e) {
		//	System.out.println("오호라..Error.....ok");
		//}
		System.out.println("나는 수행될까?");
	}
}








