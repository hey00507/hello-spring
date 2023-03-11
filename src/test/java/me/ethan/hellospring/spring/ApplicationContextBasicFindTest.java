package me.ethan.hellospring.spring;

import me.ethan.hellospring.config.CoreApplicationConfig;
import me.ethan.hellospring.member.MemberService;
import me.ethan.hellospring.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(CoreApplicationConfig.class);

    @Test
    @DisplayName("이름으로 빈 조회")
    void findByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());

        // 내가 조회한 memberService 가 MemberServiceImpl 의 인스턴스인지.
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }



    @Test
    @DisplayName("이름없이 타입으로만 조회")
    void findByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());

        // 내가 조회한 memberService 가 MemberServiceImpl 의 인스턴스인지.
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    /**
     * 밑의 두 코드는 역할이 아닌 구현에 의존하는 것이므로, 좋은 코드는 아니다.
     */
    @Test
    @DisplayName("이름으로 구체적인 타입을 조회한다.")
    void findByName2(){
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());

        // 내가 조회한 memberService 가 MemberServiceImpl 의 인스턴스인지.
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    @Test
    @DisplayName("구체적인 타입으로 조회 타입으로만 조회")
    void findByType2(){
        MemberService memberService = ac.getBean(MemberServiceImpl.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());

        // 내가 조회한 memberService 가 MemberServiceImpl 의 인스턴스인지.
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }


    @Test
    @DisplayName("이름으로 빈 조회가 안되는 경우")
    void failToFindByName(){
        Assertions.assertThatThrownBy(()->ac.getBean("xxxx", MemberService.class))
                .isExactlyInstanceOf(NoSuchBeanDefinitionException.class);
    }



}
