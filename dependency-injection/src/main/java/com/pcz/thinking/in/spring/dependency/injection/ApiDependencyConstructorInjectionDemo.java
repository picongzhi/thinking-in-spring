package com.pcz.thinking.in.spring.dependency.injection;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author picongzhi
 */
public class ApiDependencyConstructorInjectionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.registerBeanDefinition("userHolder", createUserHolderBeanDefinition());

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
        System.out.println(userHolder);

        applicationContext.close();
    }

    private static BeanDefinition createUserHolderBeanDefinition() {
        BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
        definitionBuilder.addConstructorArgReference("superUser");

        return definitionBuilder.getBeanDefinition();
    }
}
