package me.ethan.hellospring.member;

public interface MemberService {
    void join(Member member);

    Member findMemberById(Long memberId);
}
