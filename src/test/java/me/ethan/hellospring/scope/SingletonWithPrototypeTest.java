package me.ethan.hellospring.scope;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest {

    @Test
    @DisplayName("프로토타입 빈 테스트")
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        // 첫 번째 프로토타입 빈의 카운트를 증가시킨 후 확인
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        // 두 번째 프로토타입 빈의 카운트를 증가시킨 후 확인
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }


    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class , PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);


        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
        //ObjectProvider 를 통해서 생성할 경우 프로토 타입 빈이다.
    }

    @Scope("singleton")
    static class ClientBean{


        private Provider<PrototypeBean> prototypeBeanProvider;

        public ClientBean(Provider<PrototypeBean> prototypeBeanProvider) {
            this.prototypeBeanProvider = prototypeBeanProvider;
        }

        public int logic(){
            PrototypeBean prototypeBean = prototypeBeanProvider.get(); // get 메서드 하나로 기능이 굉장히 단순함
            System.out.println("prototypeBean = " + prototypeBean);
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }


    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;
        
        public void addCount(){
            count++;
        }
        
        public int getCount(){
            return count;
        }
        
        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }

    }
}
