package me.ethan.hellospring.member;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId);
    }


    /**
     * 싱글턴 테스트용 코드
     */
    public MemberRepository getMemberRepository(){
        return this.memberRepository;
    }
}
