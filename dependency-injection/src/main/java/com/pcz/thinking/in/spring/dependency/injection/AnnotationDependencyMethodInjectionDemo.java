package com.pcz.thinking.in.spring.dependency.injection;


import com.pcz.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author picongzhi
 */
public class AnnotationDependencyMethodInjectionDemo {
    private UserHolder userHolder;

    @Autowired
    public void initUserHolder(UserHolder userHolder) {
        this.userHolder = userHolder;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyMethodInjectionDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        AnnotationDependencyMethodInjectionDemo demo = applicationContext.getBean(
                AnnotationDependencyMethodInjectionDemo.class);
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
