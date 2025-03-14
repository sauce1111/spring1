package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void cleanup() {
        repository.clearStore();
    }

    @Test
    void testSave() {
        // given
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        // when
        Member result = repository.findById(member.getId()).get();
//        Assertions.assertEquals(result, member);

        // then
        assertThat(member).isEqualTo(result);
    }

    @Test
    void testFindByName() {
        // given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // when
        Member result = repository.findByName("spring1").get();
        // then
        assertThat(result).isEqualTo(member1);
    }

    @Test
    void findAll() {
        // given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // when
        List<Member> members = repository.findAll();

        // then
        assertThat(members.size()).isEqualTo(2);
    }
}
