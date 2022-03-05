package com.pcz.thinking.in.spring.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

public class AnnotatedBeanDefinitionParsingDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AnnotatedBeanDefinitionReader beanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);

        int countBeforeRegister = beanFactory.getBeanDefinitionCount();
        // 注册的类不需要是@Configuration注解的类
        beanDefinitionReader.register(AnnotatedBeanDefinitionParsingDemo.class);
        int countAfterRegister = beanFactory.getBeanDefinitionCount();
        System.out.println("Register beanDefinition count: " +
                (countAfterRegister - countBeforeRegister));

        AnnotatedBeanDefinitionParsingDemo demo =
                beanFactory.getBean("annotatedBeanDefinitionParsingDemo", AnnotatedBeanDefinitionParsingDemo.class);
        System.out.println(demo);
    }
}
