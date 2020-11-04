package com.pcz.thinking.in.spring.dependency.injection;


import com.pcz.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author picongzhi
 */
public class AnnotationDependencyFieldInjectionDemo {
    @Autowired
    private UserHolder userHolder;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyFieldInjectionDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        AnnotationDependencyFieldInjectionDemo demo = applicationContext.getBean(
                AnnotationDependencyFieldInjectionDemo.class);
        System.out.println(demo.userHolder);
        System.out.println(demo.userHolder == applicationContext.getBean(UserHolder.class));

        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user) {
        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user);

        return userHolder;
    }
}
