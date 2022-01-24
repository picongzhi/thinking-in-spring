package com.pcz.thinking.in.spring.dependency.injection;

import com.pcz.thinking.in.spring.dependency.injection.annotation.InjectedUser;
import com.pcz.thinking.in.spring.dependency.injection.annotation.MyAutowired;
import com.pcz.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.util.ClassUtils;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.*;

/**
 * @author picongzhi
 */
public class AnnotationDependencyInjectionResolutionDemo {
    @Autowired
//    @Lazy
    private User user;

    @Autowired
    private Map<String, User> users;

    @MyAutowired
    private Optional<User> userOptional;

    @Inject
    private User injectedUser;

    @InjectedUser
    private User myInjectedUser;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(
                AnnotationDependencyInjectionResolutionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        beanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");

        applicationContext.refresh();

        AnnotationDependencyInjectionResolutionDemo demo = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);
        System.out.println("user: " + demo.user);
        System.out.println("users: " + demo.users);
        System.out.println("userOptional: " + demo.userOptional);
        System.out.println("injectedUser: " + demo.injectedUser);
        System.out.println("myInjectedUser: " + demo.myInjectedUser);

        applicationContext.close();
    }

//    @Bean(name = AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//    public static AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor() {
//        AutowiredAnnotationBeanPostProcessor postProcessor = new AutowiredAnnotationBeanPostProcessor();
//        Set<Class<? extends Annotation>> annotationClasses =
//                new LinkedHashSet<>(Arrays.asList(Autowired.class, Inject.class, InjectedUser.class));
//        postProcessor.setAutowiredAnnotationTypes(annotationClasses);
//
//        return postProcessor;
//    }

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    public static AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor postProcessor = new AutowiredAnnotationBeanPostProcessor();
        postProcessor.setAutowiredAnnotationType(InjectedUser.class);

        return postProcessor;
    }
}

