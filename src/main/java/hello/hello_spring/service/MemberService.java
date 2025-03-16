package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private MemberRepository memberRespository;

    public MemberService(MemberRepository memberRespository) {
        this.memberRespository = memberRespository;
    }

    public Long signup(Member member) {
        // 이름 중복 체크
        checkDuplicateName(member);

        memberRespository.save(member);

        return member.getId();
    }

    private void checkDuplicateName(Member member) {
        memberRespository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    public List<Member> findMembers() {
        return memberRespository.findAll();
    }

    public Optional<Member> findOne(Long id) {
        return memberRespository.findById(id);
    }
}
