package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    // 이런식으로 함수를 만들어주면 Spring 애플리케이션이 실행될 때 Spring Bean에 등록해준다.
    // 굳이 메소드로 안해도되고 바로 객체로 만들어도 되지만, 메소드로 하면 추가동작로직을 더해줄수 있어서 편하다.
    @Bean
    public MemberService memberService() {
        // MemberService는 현재 생성자에 파라미터가 필요함.

        return new MemberService(memberRepository);
    }

    //    @Bean
//    public MemberRepository memberRepository() {
//        //return new JpaMemberRepository(em);
//    }


//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }
}
