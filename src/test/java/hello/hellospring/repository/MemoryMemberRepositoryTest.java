package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 각 테스트 할 때마다 호출되는 콜백함수.
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("Spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();

        //그냥 출력하는 것.
        System.out.println("result: " + (result==member));

        //아래는 일반적으로 쓰는 값 비교 함수 (junit 제공) 정답 , 기대값 순.
        Assertions.assertEquals(member, result);

        //아래는 요즘 많이 쓰는 값 비교 함수 (core api 제공) 이게 더 해석하기 쉬운편.
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member3 = new Member();
        member3.setName("Spring2");
        repository.save(member3);

        Member result = repository.findByName(member1.getName()).get();
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("Spring1");

        Member member2 = new Member();
        member2.setName("Spring2");

        repository.save(member1);
        repository.save(member2);

        List<Member> result = repository.findAll();
        org.assertj.core.api.Assertions.assertThat(result.size()).isEqualTo(2);

    }
}
