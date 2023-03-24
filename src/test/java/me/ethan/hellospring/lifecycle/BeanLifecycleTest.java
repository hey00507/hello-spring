package me.ethan.hellospring.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class BeanLifecycleTest {

    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class  LifeCycleConfig{
        @Bean
        public NetworkClient networkClient(){
            System.out.println("생성자가 호출되는 시점");
            NetworkClient networkClient = new NetworkClient();
            System.out.println("생성자가 호출된 후, networkClient = " + networkClient);
            networkClient.setUrl("https://hello-spring.dev");
            System.out.println("URL 이 등록된, networkClient = " + networkClient);
            return networkClient;
        }

    }

}