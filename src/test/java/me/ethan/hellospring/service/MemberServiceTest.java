package me.ethan.hellospring.service;

import me.ethan.hellospring.domain.Member;
import me.ethan.hellospring.repository.MemberRepository;
import me.ethan.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;



    @AfterEach
    void clean(){
        memberRepository.clearStore();
    }


    @Test
    @DisplayName("회원가입 기능이 정상 동작한다.")
    void join() {
        // given
        Member member = new Member();
        member.setName("Ethan");

        // when
        Long savedId = memberService.join(member);

        //then
        Member getMember = memberService.findOne(savedId).orElse(null);
        assertThat(savedId).isEqualTo(getMember.getId());
        
    }

    @Test
    @DisplayName("중복 회원일 경우 펑!")
    void errorJoin(){
        //given
        Member member1 = new Member();
        member1.setName("Ethan");
        Member member2 = new Member();
        member2.setName("Ethan");


        //when
        memberService.join(member1);
        //then
        IllegalStateException error = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(error.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}