package com.pcz.thinking.in.spring.dependency.injection;

import com.pcz.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Lazy;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @author picongzhi
 */
public class AnnotationDependencyInjectionResolutionDemo {
    @Autowired
    @Lazy
    private User user;

    @Autowired
    private Map<String, User> users;

    @Autowired
    private Optional<User> userOptional;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(
                AnnotationDependencyInjectionResolutionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        beanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");

        applicationContext.refresh();
        AnnotationDependencyInjectionResolutionDemo demo = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);
        System.out.println("user: " + demo.user);
        System.out.println("users: " + demo.users);
        System.out.println("userOptional: " + demo.userOptional.get());

        applicationContext.close();
    }
}

