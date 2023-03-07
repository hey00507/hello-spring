package me.ethan.hellospring.member;

import me.ethan.hellospring.config.CoreApplicationConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void dependencyInjection(){
        CoreApplicationConfig coreApplicationConfig = new CoreApplicationConfig();
        memberService=coreApplicationConfig.memberService();
    }



    @Test
    void join(){
        //given
        Member member =new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMemberById(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);

    }

}