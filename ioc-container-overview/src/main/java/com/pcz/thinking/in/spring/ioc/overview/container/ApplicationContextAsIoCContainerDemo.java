package com.pcz.thinking.in.spring.ioc.overview.container;

import com.pcz.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ApplicationContextAsIoCContainerDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ApplicationContextAsIoCContainerDemo.class);
        applicationContext.refresh();

        lookupCollectionByType(applicationContext);

        applicationContext.close();
    }

    @Bean
    public User user() {
        User user = new User();
        user.setId(1L);
        user.setName("picongzhi");

        return user;
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
        Map<String, User> userMap = listableBeanFactory.getBeansOfType(User.class);
        System.out.println("查找到的User集合对象: " + userMap);
    }
}
