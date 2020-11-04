package com.pcz.thinking.in.spring.dependency.lookup;

import com.pcz.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * @author picongzhi
 */
public class ObjectProviderDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);
        applicationContext.refresh();

        lookupByObjectProvider(applicationContext);
        lookupIfAvailable(applicationContext);
        lookupByStreamOps(applicationContext);

        applicationContext.close();
    }

    @Primary
    @Bean
    public String helloWorld() {
        return "hello world";
    }

    @Bean
    public String message() {
        return "message";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider.getObject());
    }

    private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> objectProvider = applicationContext.getBeanProvider(User.class);
        User user = objectProvider.getIfAvailable(User::createUser);
        System.out.println(user);
    }

    private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
//        Iterable<String> iterable = objectProvider;
        objectProvider.forEach(System.out::println);
    }
}
