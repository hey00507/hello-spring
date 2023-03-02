package me.ethan.hellospring.service;

import me.ethan.hellospring.domain.Member;
import me.ethan.hellospring.repository.MemberRepository;
import me.ethan.hellospring.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member){

        //중복회원은 안된다.
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository
                .findByName(member.getName())
                .ifPresent(m-> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원 검색
     * @return memberList
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * 아이디로 회원 조회
     * @param memberId
     * @return Option<Member>
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
