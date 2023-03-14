package me.ethan.hellospring.singleton;


import me.ethan.hellospring.config.CoreApplicationConfig;
import me.ethan.hellospring.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링을 사용하지 않은 순수한 DI 컨테이너 -> 계속 객체를 생성한다.")
    void pureContainer(){
        CoreApplicationConfig appConfig = new CoreApplicationConfig();
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        assertThat(memberService1).isNotSameAs(memberService2);
        // 요청이 있을 때마다 계속 instance 를 생성함
    }

    @Test
    @DisplayName("싱글톤으로 생성된 객체를 가져와서, 같은 인스턴스인지 비교한다.")
    void getSingletonInstance(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        // 생성할 때마다 new 를 통해서 새로 인스턴스를 생성하는 것보다 비용을 훨씬 절약할 수 있다. ( 서비스규모가 커질수록 효과가 올라감)
        assertThat(singletonService1).isSameAs(singletonService2);
        // 인스턴스를 비교할 때는 SameAs
    }

    @Test
    @DisplayName("스프링컨테이너를 이용해서 AppConfig 를 싱글톤으로 관리한다.")
    void singletonContainerWithSpring(){
        ApplicationContext ac =new AnnotationConfigApplicationContext(CoreApplicationConfig.class);
        CoreApplicationConfig appConfig = ac.getBean(CoreApplicationConfig.class);

        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();
        // 스프링 빈에 등록된, MemberConfig 를 이용해서 memberService 를 가져오면 같은 인스턴스를 참조하는 객체를 호출하게 된다.

        assertThat(memberService1).isEqualTo(memberService2);
    }
}
