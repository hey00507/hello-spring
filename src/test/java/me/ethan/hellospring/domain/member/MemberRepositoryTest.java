package me.ethan.hellospring.domain.member;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberRepositoryTest {

    MemberRepository memberRepository =MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
        memberRepository.clear();
    }

    @Test
    @DisplayName("멤버가 저장이 된다.")
    void save(){
        Member member =new Member("hello", 20);

        memberRepository.save(member);

        Member findMember = memberRepository.findById(member.getId());

        assertThat(findMember).isEqualTo(member);
    }

    @Test
    @DisplayName("모두 조회한다.")
    void findAll(){
        Member member1 =new Member("hello", 20);
        Member member2 =new Member("hello", 20);
        Member member3 =new Member("hello", 20);

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        List<Member> all = memberRepository.findAll();

        assertThat(all).hasSize(3);
        assertThat(all).contains(member1,member2,member3);
    }


}
