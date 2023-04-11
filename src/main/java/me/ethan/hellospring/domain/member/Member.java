package me.ethan.hellospring.domain.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    private Long id;
    private String userName;
    private int age;

    public Member(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }
}
