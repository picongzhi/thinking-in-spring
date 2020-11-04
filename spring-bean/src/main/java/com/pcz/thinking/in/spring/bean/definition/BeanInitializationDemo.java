package com.pcz.thinking.in.spring.bean.definition;

import com.pcz.thinking.in.spring.bean.factory.DefaultUserFactory;
import com.pcz.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

/**
 * @author picongzhi
 */
public class BeanInitializationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);
        applicationContext.refresh();
        System.out.println("applicationContext启动完成");

        UserFactory userFactory = applicationContext.getBean(UserFactory.class);

        System.out.println("applicationContext准备关闭");
        applicationContext.close();
        System.out.println("applicationContext已关闭");
    }

    @Lazy(false)
    @Bean(initMethod = "initUserFactory", destroyMethod = "doDestroy")
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
