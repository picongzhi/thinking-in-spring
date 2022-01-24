package com.pcz.thinking.in.spring.ioc.overview.dependency.lookup;

import com.pcz.thinking.in.spring.ioc.overview.annotation.Super;
import com.pcz.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @author picongzhi
 */
public class DependencyLookupDemo {
    public static void main(String[] args) {
        // 配置xml配置文件，启动应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(
                "classpath:/META-INF/dependency-lookup-context.xml");

        lookupInRealTime(beanFactory);
        lookupInLazy(beanFactory);

        lookupByType(beanFactory);
        lookupByCollectionType(beanFactory);

        lookupByAnnotationType(beanFactory);
    }

    /**
     * 实时查找
     *
     * @param beanFactory BeanFactory
     */
    private static void lookupInRealTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时查找: " + user);
    }

    /**
     * 延迟查找
     *
     * @param beanFactory BeanFactory
     */
    private static void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟查找: " + user);
    }

    /**
     * 按照类型查找
     *
     * @param beanFactory BeanFactory
     */
    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("类型查找: " + user);
    }

    /**
     * 按照集合类型查找
     *
     * @param beanFactory BeanFactory
     */
    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("集合类型: " + users);
        }
    }

    /**
     * 根据注解查找
     *
     * @param beanFactory BeanFactory
     */
    private static void lookupByAnnotationType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> superUsers = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("注解: " + superUsers);
        }
    }
}
