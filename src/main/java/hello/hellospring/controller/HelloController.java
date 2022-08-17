package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @GetMapping("hello") //GetMapping : HTTP GET 요청을 특정 핸들러 메서드에 맵핑하기 위한 annotation
    //메서드
    public String hello(Model model) {
        model.addAttribute("data", "spring!!");
        return "hello";
    }

    @GetMapping("hello-mvc") // e.g) localhost:8080/hello-mvc
    //메서드
    public String helloMvc(@RequestParam(value = "name") String name, Model model) { //외부에서 파라미터로 받음
        model.addAttribute("name", name);
        return "hello-template";  //hello-template.html 으로 return
    }
}
