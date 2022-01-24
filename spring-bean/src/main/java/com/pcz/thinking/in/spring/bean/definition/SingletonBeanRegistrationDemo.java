package com.pcz.thinking.in.spring.bean.definition;

import com.pcz.thinking.in.spring.bean.factory.DefaultUserFactory;
import com.pcz.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author picongzhi
 */
public class SingletonBeanRegistrationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        UserFactory userFactory = new DefaultUserFactory();

        SingletonBeanRegistry singletonBeanRegistry = applicationContext.getBeanFactory();
        singletonBeanRegistry.registerSingleton("userFactory", userFactory);

        applicationContext.refresh();

        UserFactory userFactoryByLookup = applicationContext.getBean("userFactory", UserFactory.class);
        System.out.println(userFactory == userFactoryByLookup);

        applicationContext.close();
    }
}
