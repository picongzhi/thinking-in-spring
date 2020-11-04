package com.pcz.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author picongzhi
 */
public class NoUniqueBeanDefinitionExceptionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(NoUniqueBeanDefinitionExceptionDemo.class);
        applicationContext.refresh();

        try {
            String bean = applicationContext.getBean(String.class);
        } catch (NoUniqueBeanDefinitionException e) {
            e.printStackTrace();
        }

        applicationContext.close();
    }

    @Bean
    public String bean1() {
        return "bean1";
    }

    @Bean
    public String bean2() {
        return "bean2";
    }
}
