package me.ethan.hellospring;

import me.ethan.hellospring.member.Grade;
import me.ethan.hellospring.member.Member;
import me.ethan.hellospring.member.MemberService;
import me.ethan.hellospring.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {

        CoreApplicationConfig coreApplicationConfig =new CoreApplicationConfig();

        MemberService memberService = coreApplicationConfig.memberService();

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMemberById(1L);

        System.out.println("new.member = " + member);
        System.out.println("findMember = " + findMember);
    }
}
