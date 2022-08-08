package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello") //GetMapping : HTTP GET 요청을 특정 핸들러 메소드에 맵핑하기 위한 annotation
    //메소드
    public String hello(Model model) {
        model.addAttribute("data", "spring!!");
        return "hello";
    }

    @GetMapping("hello-mvc") // e.g) localhost:8080/hello-mvc
    public String helloMvc(@RequestParam(value = "name") String name, Model model) { //외부에서 파라미터로 받음
        model.addAttribute("name", name);
        return "hello-template";  //hello-template.html 으로 return
    }

    @GetMapping("hello-string")
    @ResponseBody // HTTP의 바디부에 데이터를 직접 넣어주기 위해 필요
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name; // ViewResolver 거치지 않고 API  통해 name이 바로 내려감
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 문자가 아닌 객체를 리턴
    }

    //HelloController.Class 내에서 사용하는 static class
    //자바 빈 표준방식 or 프로퍼티 접근방식 : Key는 private, getter, setter는 public
    static class Hello {
        private String name; // Key == name
        //꺼낼 때 getName
        public String getName() {
            return name;
        }
        //넣을 때 setName
        public void setName(String name) { //value(spring!!)는 이번 강의에서 URL 에 입력 http://localhost:8080/hello-api?name=Spring!!
            this.name = name;
        }
    }
}

