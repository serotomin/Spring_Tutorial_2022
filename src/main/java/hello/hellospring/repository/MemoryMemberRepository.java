package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // store에 넣기 전 Member의 id를 셋팅해주고 seq 1올림
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //null이 반환될 가능성이 있으면 optional.ofnullable()로 감싸서 반환
    }

    @Override
    public Optional<Member> findByName(String name) { //루프를 돌면서 같은 name이 찾아지면 반환, 끝까지 돌았는데 없으면 Null 반환
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) //파라미터로 넘어온 equals(name)과 같은 경우에만 필터링됨.
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //store에 있는 멤버들을 반환
    }

    public void clearStore() { //store 초기화
        store.clear();
    }
}
