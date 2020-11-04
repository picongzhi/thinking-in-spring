package com.pcz.thinking.in.spring.dependency.injection;

import com.pcz.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Set;

/**
 * @author picongzhi
 */
public class LazyAnnotationDependencyInjectionDemo {
    @Autowired
    @Qualifier("user")
    private User user;

    @Autowired
    private ObjectProvider<User> userObjectProvider;

    @Autowired
    private ObjectFactory<Set<User>> usersObjectFactory;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(
                LazyAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        beanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");

        applicationContext.refresh();

        LazyAnnotationDependencyInjectionDemo demo = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);
        System.out.println("user: " + demo.user);

        System.out.println("userObjectProvider: " + demo.userObjectProvider.getObject());
        demo.userObjectProvider.forEach(System.out::println);

        System.out.println("usersObjectFactory: " + demo.usersObjectFactory.getObject());

        applicationContext.close();
    }
}

