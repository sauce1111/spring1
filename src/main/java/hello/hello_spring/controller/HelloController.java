package hello.hello_spring.controller;

import hello.hello_spring.dto.HelloDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.Name;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = true) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public HelloDto helloApi(@RequestParam("name") String name) {
        // @ResponseBody -> ViewResolver 대신 HttpMessageConverter 가 동작한다.
        // HelloDto 와 같이 객체일 경우 Json 으로 변환 (MappingJackson2HttpMessageConverter)
        // HTTP Accept 헤더 와 컨트롤러 반환타입을 조합해 알맞는 converter 가 동작한다.
        HelloDto helloDto = new HelloDto();
        helloDto.setName(name);
        return helloDto;
    }
}
