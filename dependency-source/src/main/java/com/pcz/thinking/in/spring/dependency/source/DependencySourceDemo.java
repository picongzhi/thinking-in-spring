package com.pcz.thinking.in.spring.dependency.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * @author picongzhi
 */
public class DependencySourceDemo {
    /**
     * 注入在postProcessProperties方法执行，早于setter注入，也早于@PostConstructor
     */
    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostConstruct
    public void initByInjection() {
        System.out.println("beanFactory: " + beanFactory);
        System.out.println("resourceLoader: " + resourceLoader);
        System.out.println("applicationContext: " + applicationContext);
        System.out.println("applicationEventPublisher: " + applicationEventPublisher);
    }

    @PostConstruct
    public void initByLookup() {
        getBean(BeanFactory.class);
        getBean(ResourceLoader.class);
        getBean(ApplicationContext.class);
        getBean(ApplicationEventPublisher.class);
    }

    private <T> void getBean(Class<T> cls) {
        try {
            beanFactory.getBean(cls);
        } catch (NoSuchBeanDefinitionException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DependencySourceDemo.class);
        applicationContext.refresh();

        System.out.println("Demo: " + applicationContext.getBean(DependencySourceDemo.class));

        applicationContext.close();
    }
}
