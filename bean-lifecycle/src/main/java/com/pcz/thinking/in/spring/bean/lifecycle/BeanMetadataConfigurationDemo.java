package com.pcz.thinking.in.spring.bean.lifecycle;

import com.pcz.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;

public class BeanMetadataConfigurationDemo {
    public static void main(String[] args) {
        String location = "META-INF/user.properties";
        EncodedResource resource = new EncodedResource(
                new ClassPathResource(location), "UTF-8");

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        int count = beanDefinitionReader.loadBeanDefinitions(resource);
        System.out.println("BeanDefinitions count: " + count);

        User user = beanFactory.getBean(User.class);
        System.out.println("User: " + user);
    }
}
