package com.pcz.thinking.in.spring.dependency.injection;


import com.pcz.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author picongzhi
 */
public class AnnotationDependencyConstructorInjectionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyConstructorInjectionDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
        System.out.println(userHolder);

        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
