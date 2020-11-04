package com.pcz.thinking.in.spring.ioc.overview.dependency.injection;

import com.pcz.thinking.in.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author picongzhi
 */
public class DependencyInjectionDemo {
    public static void main(String[] args) {
        // 配置xml配置文件，启动应用上下文
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath:META-INF/dependency-injection-context.xml");

        // 依赖来源一: 自定义Bean
        UserRepository userRepository = (UserRepository) applicationContext.getBean("userRepository");
        System.out.println(userRepository.getUsers());

        // 依赖来源二: 依赖注入（内建依赖）
        System.out.println(userRepository.getBeanFactory());

        ObjectFactory objectFactory = userRepository.getObjectFactory();
        System.out.println(objectFactory.getObject() == applicationContext);

        // 依赖查找（错误）
//        System.out.println(applicationContext.getBean(BeanFactory.class));

        // 依赖来源三: 容器内建Bean
        Environment environment = applicationContext.getBean(Environment.class);
        System.out.println(environment);
    }
}
