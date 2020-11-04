package com.pcz.thinking.in.spring.dependency.injection;

import com.pcz.thinking.in.spring.dependency.injection.annotation.UserGroup;
import com.pcz.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

/**
 * @author picongzhi
 */
public class QualifierAnnotationDependencyInjectionDemo {
    @Autowired
    private User user;

    @Qualifier("user")
    @Autowired
    private User namedUser;

    @Bean
    // 进行逻辑分组
    @Qualifier
    public static User user1() {
        return createUser(7L);
    }

    @Bean
    // 进行逻辑分组
    @Qualifier
    public static User user2() {
        return createUser(8L);
    }

    @Bean
    // 进行逻辑分组
    @UserGroup
    public static User user3() {
        return createUser(9L);
    }

    @Bean
    // 进行逻辑分组
    @UserGroup
    public static User user4() {
        return createUser(10L);
    }

    private static User createUser(Long id) {
        User user = new User();
        user.setId(id);

        return user;
    }

    @Autowired
    private Collection<User> allUsers;

    @Autowired
    @Qualifier
    private Collection<User> qualifiedUsers;

    @Autowired
    @UserGroup
    private Collection<User> userGroupUsers;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(
                QualifierAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        beanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");

        applicationContext.refresh();

        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(
                QualifierAnnotationDependencyInjectionDemo.class);
        System.out.println("user: " + demo.user);
        System.out.println("named user: " + demo.namedUser);

        System.out.println("all users: ");
        demo.allUsers.forEach(System.out::println);

        System.out.println("qualified users: ");
        demo.qualifiedUsers.forEach(System.out::println);

        System.out.println("user group users: ");
        demo.userGroupUsers.forEach(System.out::println);

        applicationContext.close();
    }

//    @Configuration
//    class UserConfig {
//        @Bean
//        // 进行逻辑分组
//        @Qualifier
//        public User user1() {
//            User user = new User();
//            user.setId(8L);
//
//            return user;
//        }
//
//        @Bean
//        // 进行逻辑分组
//        @Qualifier
//        public User user2() {
//            User user = new User();
//            user.setId(7L);
//
//            return user;
//        }
//    }
}

