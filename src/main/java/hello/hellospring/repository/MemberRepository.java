package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원 저장소에 저장
    Optional<Member> findById(Long id); //id로 회원찾기, Null 반환되는 경우를 대비하여 Optional로 감싸서 처리
    Optional<Member> findByName(String name); //name으로 회원찾기
    List<Member> findAll(); //저장된 모드 회원리스트 반환
}
