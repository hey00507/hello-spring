package me.ethan.hellospring.repository;

import me.ethan.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository =new MemoryMemberRepository();

    @AfterEach
    void clear(){
        repository.clearStore();
    }


    @Test
    @DisplayName("저장 후 아이디로 검색이 된다.")
    void save(){
        Member member = new Member();
        member.setName("Ethan");

        repository.save(member);

        Member result = repository.findById(member.getId()).orElse(null);

        // TestCode
        assertThat(member).isEqualTo(result);
    }


    @Test
    @DisplayName("이름으로 검색이 잘 된다.")
    void findByName(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        Member result = repository.findByName("Spring1").get();

        assertThat(result).isEqualTo(member1);
    }


    @Test
    @DisplayName("findAll 로 리스트를 한방에 가지고 온다.")
    void findAll(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        List<Member> memberList = repository.findAll();

        assertThat(memberList.size()).isEqualTo(2);
    }

}