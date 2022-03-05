package com.pcz.thinking.in.spring.configuration.metadata;

import com.pcz.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;

public class PropertiesBeanDefinitionReaderDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        int count = beanDefinitionReader.loadBeanDefinitions(new EncodedResource(
                new ClassPathResource("META-INF/users.properties"), "UTF-8"));
        System.out.println("Loaded bean definition count: " + count);

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }
}
