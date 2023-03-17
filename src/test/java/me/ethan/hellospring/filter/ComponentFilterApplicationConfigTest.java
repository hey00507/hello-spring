package me.ethan.hellospring.filter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ComponentFilterApplicationConfigTest {

    @Test
    void filterScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterApplicationConfig.class);

        IncludedBean includedBean = ac.getBean(IncludedBean.class);

        // 필터에 의해 추가 된 빈인 경우 잘 색인된다.
        assertThat(includedBean).isNotNull();

        // 필터에 의해 제외된 빈인 경우, 오류 발생
        assertThatThrownBy(()-> ac.getBean(ExcludedBean.class))
                .isExactlyInstanceOf(NoSuchBeanDefinitionException.class);
    }

    @Configuration
    @ComponentScan(
            includeFilters = @Filter(classes = MyIncludeComponent.class),
            excludeFilters = @Filter(classes = MyExcludeComponent.class)
    )
    static class ComponentFilterApplicationConfig{}
}
