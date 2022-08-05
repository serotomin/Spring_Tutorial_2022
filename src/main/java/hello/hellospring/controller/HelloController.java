package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //스프링의 경우 컨트롤러 annotation 을 적어줘야 라이브러리를 import 해옴
public class HelloController {


    @GetMapping("hello") //웹 애플리케이션에서 /hello 로 들어오면 아래 메서드를 호출해줌
    public String hello(Model model){
        model.addAttribute
    }
}
