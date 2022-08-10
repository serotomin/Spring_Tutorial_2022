package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired //생성자에 @Autowired가 되어 있으면멤버 서비스를 스프링이 스프링컨테이너에서 가져다가 연결해줌
    public MemberController(MemberService memberService) { //Constructor(생성자 주입), CTRL + N 로 생성
        this.memberService = memberService;
    }
}
