package me.ethan.hellospring.singleton;

import me.ethan.hellospring.config.CoreApplicationConfig;
import me.ethan.hellospring.member.MemberRepository;
import me.ethan.hellospring.member.MemberServiceImpl;
import me.ethan.hellospring.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {
    
    @Test
    void configurationTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(CoreApplicationConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

        MemberRepository originMemberRepository = ac.getBean(MemberRepository.class);
        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        assertThat(memberRepository1)
                .isSameAs(memberRepository2)
                .isSameAs(originMemberRepository);
    }

    @Test
    void configurationDeep(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(CoreApplicationConfig.class);
        CoreApplicationConfig bean = ac.getBean(CoreApplicationConfig.class);

        System.out.println("bean = " + bean.getClass());// EnhancerBySpringCGLIB -> 바이트코드를 조작하는 라이브러리를 상속한 다른 클래스를 생성함
    }
}
