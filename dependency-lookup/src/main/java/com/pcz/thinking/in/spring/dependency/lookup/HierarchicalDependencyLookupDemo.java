package com.pcz.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author picongzhi
 */
public class HierarchicalDependencyLookupDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(HierarchicalDependencyLookupDemo.class);

        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        System.out.println("parent bean factory: " + beanFactory.getParentBeanFactory());

        // 创建parentBeanFactory
        HierarchicalBeanFactory parentBeanFactory = createParentBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);
        System.out.println("parent bean factory: " + beanFactory.getParentBeanFactory());

        // 展示本地bean
        displayContainsLocalBean(beanFactory, "user");
        displayContainsLocalBean(parentBeanFactory, "user");

        // 展示bean，包含parent的bean
        displayContainsBean(beanFactory, "user");
        displayContainsBean(parentBeanFactory, "user");

        applicationContext.refresh();
        applicationContext.close();
    }

    private static HierarchicalBeanFactory createParentBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        int beanCount = reader.loadBeanDefinitions(location);
        System.out.println("Bean定义加载的数量: " + beanCount);

        return beanFactory;
    }

    private static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前BeanFactory[%s]是否包含local bean[%s]: %s\n",
                beanFactory, beanName, beanFactory.containsLocalBean(beanName));
    }

    private static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("BeanFactory[%s]是否包含bean[%s]: %s\n",
                beanFactory, beanName, containsBean(beanFactory, beanName));
    }

    private static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        return beanFactory.containsBean(beanName);
    }
}
