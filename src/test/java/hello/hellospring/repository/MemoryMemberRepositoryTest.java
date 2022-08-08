package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //어떤 메소드의 동작이 다 끝나면 afterEach 메소드가 동작함
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member(); //[SHFIT] + [ENTER] 숙지
        member.setName("spring");

        repository.save(member); // member 저장
        /* member 정상적으로 저장됐는지 체크
           findById가 Optional로 반환타입이 선언되어 있기 때문에 값을 꺼낼 때는 get()으로 꺼냄
           */
        Member result = repository.findById(member.getId()).get();
        //Assertions.assertEquals(result, member); // result, member가 같은지 체크 Junit.jupiter API 활용(Assertions) / 같으면 : 정상실행 , 다르면 : 오류발생
        assertThat(member).isEqualTo(result); //assertj.core.api.Assertions 활용
        //assertThat(member).isEqualTo(result); //Error
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //Optional<Member> result =  repository.findByName("spring1").get();
        Member result =  repository.findByName("spring1").get(); //get()을 사용하여 optional 생략 가능

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findByAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}
