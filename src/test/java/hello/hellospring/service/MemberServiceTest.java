package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

// 어노테이션 있어도 되고 없어도 되고..
@SpringBootTest
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        // given 검증에 쓰이는 데이터
        Member member = new Member();
        member.setName("김진원");

        // when 검증할 부분
        Long saveId = memberService.join(member);

        // then 검증 내용
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void duplicatedMember() {
        // given
        Member member1 = new Member();
        member1.setName("김진원");

        Member member2 = new Member();
        member2.setName("김진원");

        // when
        memberService.join(member1);
        /*try{
            memberService.join(member2);
            fail("예외가 발생해야 합니다.");
        }
        // then
        catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/
        // try catch 대신에 예외를 테스트하는 문법이 있다. 일어나야할 예외, 예외가 일어나는 함수(람다로 하기).
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}