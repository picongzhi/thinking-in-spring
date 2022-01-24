package com.pcz.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author picongzhi
 */
public class BeanInstantiationExceptionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.registerBeanDefinition("errorBean",
                BeanDefinitionBuilder
                        .genericBeanDefinition(CharSequence.class)
                        .getBeanDefinition());

        applicationContext.refresh();
        applicationContext.close();
    }
}
