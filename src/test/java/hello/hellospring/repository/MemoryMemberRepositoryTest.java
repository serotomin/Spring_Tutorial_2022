package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @Test
    public void save(){
        Member member = new Member(); //[SHFIT] + [ENTER] 숙지
        member.setName("spring");

        repository.save(member); // member 저장
        /* member 정상적으로 저장됐는지 체크
           findById가 Optional로 반환타입이 선언되어 있기 때문에 값을 꺼낼 때는 get()으로 꺼냄
           */
        Member result = repository.findById(member.getId()).get();
        Assertions.assertEquals(result, member); // result, member가 같은지 체크 Junit.jupiter API 활용(Assertions) / 같으면 : 정상실행 , 다르면 : 오류발생





    }
}
