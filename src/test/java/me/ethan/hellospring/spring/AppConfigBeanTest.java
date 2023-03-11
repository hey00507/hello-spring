package me.ethan.hellospring.spring;

import me.ethan.hellospring.config.CoreApplicationConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppConfigBeanTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(CoreApplicationConfig.class);

    @Test
    @DisplayName("모든 정의된 빈을 출력한다.")
    void findAllBeans(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName:
             beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + "object = " + bean);

        }
    }

    @Test
    @DisplayName("애플리케이션 빈만 출력한다.")
    void findApplicationBeans(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName:
                beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + "object = " + bean);            }
        }
    }
}
