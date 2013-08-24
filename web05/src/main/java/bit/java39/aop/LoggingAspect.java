package bit.java39.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	// fully-qualified name(QName) = 패키지명 + 클래스명
	// AspectJ pointcut expression
	// execution(메서드범위 리턴타입 QName.메서드명(파라미터들))
	// 예) execution(public int net.bitacademy.**.add(*))
	@Before("execution(* *..dao.*.*(..))")
	public void logBefore(JoinPoint joinPoint) {
		System.out.println(joinPoint.getSignature().getDeclaringTypeName());
		System.out.println(joinPoint.getSignature().getName());
		System.out.println(Arrays.toString(joinPoint.getArgs()));
		System.out.println("======================> 이전....");
	}
	
	@After("execution(* *..dao.*.*(..))")
	public void logAfter() {
		System.out.println("======================> 이후...");
	}
	
	/*
	@AfterReturning
	public void logAfterReturning() {
		System.out.println("이후...");
	}
	*/
}
