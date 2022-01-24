package com.pcz.thinking.in.spring.bean.definition;

import com.pcz.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author picongzhi
 */
// 3. 通过@Import方式导入
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration配置类
        applicationContext.register(Config.class);
        applicationContext.refresh();

        // 1. 命名bean的注册方式
        registerUserBeanDefinition(applicationContext, "user2");
        // 2. 非命名bean的注册方式
        registerUserBeanDefinition(applicationContext);

        System.out.println(applicationContext.getBeansOfType(User.class));
        System.out.println(applicationContext.getBeansOfType(Config.class));

        applicationContext.close();
    }

    // 2. 通过@Component方式
    @Component
    public static class Config {
        // 1. 通过@Bean方式定义
        @Bean(name = {"user", "pcz-user"})
        public User user() {
            User user = new User();
            user.setId(2L);
            user.setName("pcz");

            return user;
        }
    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder
                .genericBeanDefinition(User.class)
                .addPropertyValue("id", "1")
                .addPropertyValue("name", "picongzhi");
        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
        if (StringUtils.hasText(beanName)) {
            registry.registerBeanDefinition(beanName, beanDefinition);
        } else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition, registry);
        }
    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry) {
        registerUserBeanDefinition(registry, null);
    }
}
