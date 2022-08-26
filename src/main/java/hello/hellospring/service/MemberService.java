package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//@Service
@Transactional
public class MemberService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository; //private

    //@Autowired
    public MemberService(MemberRepository memberRepository) { //생성자, 외부에서 주입 public
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member) {
        /*
        Optional<Member> result = memberRepository.findByName(member.getName());// Optional 단축키 : [CTRL] + [ALT] + V
        result.ifPresent(m -> { //Optional로 감쌌기 때문에  ifPresent 함수 사용 가능
                throw new IllegalStateException("이미 존재하는 회원입니다."); //NULL이 아닌, 멤버에 값이 있으면 아래 예외구문 처리
        });
        */
        /*
        Optional로 바로 반환하는게 좋지 않기 때문에,
        아래처럼 간단히 코드 작성
        */

        validateDuplicateMember(member); //중복 회원 검증 (핵심관심사항)
        memberRepository.save(member);
        return member.getId();

        /*
        //AOP 추가 finally~
        long start = System.currentTimeMillis();

        try {
            validateDuplicateMember(member); //중복 회원 검증 (핵심관심사항)
            memberRepository.save(member);
            return member.getId();
        } finally { // AOP 적용이 필요한 부분 (공통관심사항)
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }
        */
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    //전체 회원조회
    public List<Member> findMembers() { //findAll()의 리턴타입이 List였기 때문에 List<>로 선언함
        return memberRepository.findAll(); //List 형태로 리턴


        /*
        long start = System.currentTimeMillis();

        try {
            return memberRepository.findAll(); //List 형태로 리턴
        } finally { // AOP 적용이 필요한 부분 (공통관심사항)
          long finish = System.currentTimeMillis();
          long timeMs = finish - start;
          System.out.println("findMembers " + timeMs + "ms");
        }
        */
    }

    public Optional<Member> findOne (Long memberId){
        return memberRepository.findById(memberId);
    }
}
