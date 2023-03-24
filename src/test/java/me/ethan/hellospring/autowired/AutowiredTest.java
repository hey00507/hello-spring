package me.ethan.hellospring.autowired;

import me.ethan.hellospring.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Optional;

public class AutowiredTest {
    @Test
    void AutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    @Component
    static class TestBean{

        @Autowired(required = false)
        public void setNoBean1(Member member){
            System.out.println("member = " + member);
            // 첫번 째 멤버는 호출되지 않음 -> 메서드 자체가 호출되지 않는다.
        }


        @Autowired
        public void setNoBean2(@Nullable Member member){
            System.out.println("Nuallable member = " + member);
        }


        @Autowired
        public void setNoBean3(Optional<Member> member){
            System.out.println("Optional member = " + member);
        }
    }
}
