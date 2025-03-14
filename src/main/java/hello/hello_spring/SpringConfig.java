package hello.hello_spring;

import hello.hello_spring.repository.MemberRespository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
* @Service, @Repository, @Autowired 등 을 제거하고 JAVA 코드로 빈 등록
* */
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRespository());
    }

    @Bean
    public MemberRespository memberRespository() {
        return new MemoryMemberRepository();
    }
}
