package me.ethan.hellospring.spring;

import me.ethan.hellospring.discount.DiscountPolicy;
import me.ethan.hellospring.discount.FixDiscountPolicy;
import me.ethan.hellospring.discount.RateDiscountPolicy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);// 어떤 Class  에 서 Bean 을 조회할지 잡아줘야함

    @Configuration
    static class TestConfig{

        @Bean
        public DiscountPolicy rateDiscountPolicy(){
         return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }


    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면 중복 오류가 발생한다.")
    void findBeanByParentTypeDuplication(){
        Assertions.assertThatThrownBy(()-> ac.getBean(DiscountPolicy.class))
                .isExactlyInstanceOf(NoUniqueBeanDefinitionException.class);
    }


    @Test
    @DisplayName("자식이 둘 이상 있으면 빈 이름을 지정하면 된다.")
    void findBeanByParentTypeBeanName(){
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        Assertions.assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }


    @Test
    @DisplayName("하위타입을 명시하여 Bean 을 꺼내줄 수도 있다. (좋은 방법은 아님)")
    void findBeanBySubType(){
        DiscountPolicy rateDiscountPolicy = ac.getBean(RateDiscountPolicy.class);
        Assertions.assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }


    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeansByParentsType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        Assertions.assertThat(beansOfType.size()).isEqualTo(2);
    }


    @Test
    @DisplayName("부모 타입으로 모두 조회하기- ObjectType 의 경우")
    void findAllBeansWithObjectType(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key: beansOfType.keySet()){
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
    }
}
