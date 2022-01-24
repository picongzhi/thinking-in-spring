package com.pcz.thinking.in.spring.bean.definition;

import com.pcz.thinking.in.spring.bean.factory.DefaultUserFactory;
import com.pcz.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author picongzhi
 */
public class SpecialBeanInstantiationDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath:/META-INF/special-bean-instantiation-context.xml");

        // ServiceLoader方式
        serviceLoader();

        ServiceLoader<UserFactory> userFactoryServiceLoader = applicationContext.getBean("userFactoryServiceLoader", ServiceLoader.class);
        displayServiceLoader(userFactoryServiceLoader);

        // AutowireCapableBeanFactory方式
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        UserFactory userFactory = autowireCapableBeanFactory.createBean(DefaultUserFactory.class);
        System.out.println(userFactory.createUser());
    }

    public static void serviceLoader() {
        ServiceLoader<UserFactory> serviceLoader = ServiceLoader.load(
                UserFactory.class, Thread.currentThread().getContextClassLoader());
        displayServiceLoader(serviceLoader);
    }

    public static void displayServiceLoader(ServiceLoader<UserFactory> serviceLoader) {
        Iterator<UserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().createUser());
        }
    }
}
