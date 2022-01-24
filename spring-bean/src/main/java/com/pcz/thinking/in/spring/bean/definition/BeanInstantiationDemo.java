package com.pcz.thinking.in.spring.bean.definition;

import com.pcz.thinking.in.spring.bean.factory.UserFactoryBean;
import com.pcz.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author picongzhi
 */
public class BeanInstantiationDemo {
    public static void main(String[] args) throws Exception {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(
                "classpath:/META-INF/bean-instantiation-context.xml");
        User userByStaticMethod = beanFactory.getBean("user-by-static-method", User.class);
        System.out.println(userByStaticMethod);

        User userByInstanceMethod = beanFactory.getBean("user-by-instance-method", User.class);
        System.out.println(userByInstanceMethod);
        System.out.println(userByInstanceMethod == userByStaticMethod);

        User userByFactoryBean = beanFactory.getBean("user-by-factory-bean", User.class);
        System.out.println(userByFactoryBean);
        System.out.println(userByFactoryBean == userByInstanceMethod);
    }
}
