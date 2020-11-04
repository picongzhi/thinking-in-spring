package com.pcz.thinking.in.spring.ioc.overview.container;

import com.pcz.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

public class BeanFactoryAsIoCContainerDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        int beanCount = reader.loadBeanDefinitions(location);
        System.out.println("Bean定义加载的数量: " + beanCount);
        lookupCollectionByType(beanFactory);
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
        Map<String, User> userMap = listableBeanFactory.getBeansOfType(User.class);
        System.out.println("查找到的User集合对象: " + userMap);
    }
}
