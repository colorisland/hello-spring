package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// AOP 등록하려면 Aspect 애노테이션 필요함
// Throwable 은 예외처리 클래스 중에서 최상위 클래스임
// Component 애노테이션 달아도 되지만 Config에다 하는게 더 추천됨.
@Aspect
@Component
public class TimeTraceAop {

    // Around에 쓰이는 문법은 크게 신경쓰지말기 어차피 쓰는거만 쓴다고 한다.
    @Around("execution(* hello.hellospring..*(..))")
    public Object excute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        // 호출하는 메소드 이름 출력
        System.out.println("Start: " + joinPoint.toString());
        try {
            // 다음 메소드로 진행된다는 뜻.
            return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("End: " + joinPoint.toString() + " " + timeMs);
        }

    }
}
