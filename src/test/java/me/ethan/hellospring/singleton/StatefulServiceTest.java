package me.ethan.hellospring.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA
        statefulService1.order("userA", 10000);
        int originPrice= statefulService1.getPrice();
        //ThreadB
        statefulService2.order("userB", 20000);

        //ThreadA 의 주문 금액을 조회할 경우?
        int price = statefulService1.getPrice();

        System.out.println("originPrice = " + originPrice);
        System.out.println("price = " + price);
        // 같은 인스턴스를 사용하기에, 값을 변경할 수 있는 필드가 Singleton 으로 관리되면 망~
        Assertions.assertThat(originPrice).isNotEqualTo(price);
    }


    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}