package com.pcz.thinking.in.spring.configuration.metadata;

import com.pcz.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ObjectUtils;

public class BeanConfigurationMetadataDemo {
    public static void main(String[] args) {
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
                .genericBeanDefinition(User.class)
                .addPropertyValue("name", "pcz")
                .getBeanDefinition();

        beanDefinition.setAttribute("name", "picongzhi");
        beanDefinition.setSource(BeanConfigurationMetadataDemo.class);

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new MyBeanPostProcessor(beanFactory));

        beanFactory.registerBeanDefinition("user", beanDefinition);

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }

    private static class MyBeanPostProcessor implements BeanPostProcessor {
        private final DefaultListableBeanFactory beanFactory;

        public MyBeanPostProcessor(DefaultListableBeanFactory beanFactory) {
            this.beanFactory = beanFactory;
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("user", beanName) &&
                    User.class.equals(bean.getClass())) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                if (BeanConfigurationMetadataDemo.class.equals(beanDefinition.getSource())) {
                    String name = (String) beanDefinition.getAttribute("name");

                    User user = (User) bean;
                    user.setName(name);

                    return user;
                }
            }

            return null;
        }
    }
}
