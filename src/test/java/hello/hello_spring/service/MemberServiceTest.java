package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    void cleanup() {
        memberRepository.clearStore();
    }

    @Test
    void signup() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.signup(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    @DisplayName("이름 중복 회원 - 예외발생")
    void signupException() {
        // given
        Member member1 = new Member();
        member1.setName("hello");
        Member member2 = new Member();
        member2.setName("hello");

        // when
        memberService.signup(member1);
        IllegalStateException assertThrows = assertThrows(IllegalStateException.class, () -> memberService.signup(member2));
        assertThat(assertThrows.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        try {
//            memberService.signup(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}