package com.pcz.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

/**
 * @author picongzhi
 */
public class BeanCreationExceptionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(POJO.class);
        applicationContext.registerBeanDefinition("pojo", builder.getBeanDefinition());

        applicationContext.refresh();
        applicationContext.close();
    }

    static class POJO implements InitializingBean {
        @PostConstruct
        public void init() throws Exception {
            throw new Exception("init: For purpose...");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("afterPropertiesSet: For purpose...");
        }
    }
}
