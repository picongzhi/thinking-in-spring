package com.pcz.thinking.in.spring.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * @author picongzhi
 */
public class ResolvableDependencySourceDemo {
    @Autowired
    private String helloWorld;

    @PostConstruct
    public void init() {
        System.out.println(helloWorld);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ResolvableDependencySourceDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerResolvableDependency(String.class, "hello world");
        });

        applicationContext.refresh();

        applicationContext.close();
    }
}
