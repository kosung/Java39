package bit.java39.test;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* 학습목표: Annotation 다루기
 * 1) Annotation 정의 
 * - 어떤 정보를 기록할 것인가? 명세를 정의.
 * - 문법
 * @interface 애노테이션명 {
 * 		속성타입  속성명();
 * 		속성타입	속성명() default 기본값;
 * }
 * 
 * 2) Annotation 사용
 * - 변수(인스턴슨,클래스,로컬,파라미터) 선언 앞에, 메서드 선언 앞에, 클래스 선언 앞에
 * - 문법
 * @애노테이션이름(속성명=값, 속성명=값, ...)
 * class 클래스명 {...}
 * 
 * - 속성명이 value인 경우 값을 설정할 때 속성이름을 생략할 수 있다.
 * 예) @애노테이션이름(value=값)   -----> @애노테이션이름(값)
 * - 만약 속성 값을 여러개 설정할 때는 그 중 하나가 value라는 이름을 가질지라도 생략할 수 없다.
 * 예) @애노테이션이름(값,other=값)  <--------- 오류다
 *    @애노테이션이름(value=값,other=값) <------------ 정상!
 */

/* 주석으로 저장할 값의 규칙을 정의 */
@Retention(value=RetentionPolicy.RUNTIME) /* @Retention(RetentionPolicy.RUNTIME) */
@interface PageController {
	String path();
	String writer();
}

/* 주석 달기 */
@PageController(path="/test.do", writer="ohora")
class MyController {
	public String execute() {
		return "okok";
	}
}

public class AnnotationTest {
	public static void main(String[] args) throws Exception {
		/* 애노테이션 정보를 꺼내려면 먼저 클래스를 로딩해야 한다.*/
		//Class clazz = Class.forName("bit.java39.test.MyController");
		Class clazz = MyController.class;
		
		/* 클래스로부터 애노테이션 객체를 꺼낸다.
		 * - 아래의 코드는 PageController라는 이름의 애노테이션을 꺼낸다.
		 */
		PageController annotation = (PageController)clazz.getAnnotation(PageController.class);
		
		/* 애노테이션 객체로부터 속성 값을 꺼낸다.*/
		if (annotation != null) {
			System.out.println("path=" + annotation.path());
			System.out.println("writer=" + annotation.writer());
		} else {
			System.out.println("해당 클래스로부터 PageController라는 애노테이션 정보를 찾을 수 없습니다.");
		}
	}

}










