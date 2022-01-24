package com.pcz.thinking.in.spring.bean.scope;

import com.pcz.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;

/**
 * @author picongzhi
 */
public class BeanScopeDemo implements DisposableBean, BeanFactoryAware {
    @Qualifier("singletonUser")
    @Autowired
    private User singletonUser;

    @Qualifier("singletonUser")
    @Autowired
    private User singletonUser2;

    @Qualifier("prototypeUser")
    @Autowired
    private User prototypeUser;

    @Qualifier("prototypeUser")
    @Autowired
    private User prototypeUser2;

    @Autowired
    private Map<String, User> users;

    @Autowired
    private BeanFactory beanFactory;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    if (bean instanceof User) {
                        System.out.printf("%s: %s after initialization\n",
                                bean.getClass().getName(), ((User) bean).getId());
                    }
                    return bean;
                }
            });
        });

        applicationContext.refresh();

        scopedBeansByLookup(applicationContext);

        scopedBeansByInjection(applicationContext);

        applicationContext.close();
    }

    private static void scopedBeansByLookup(ApplicationContext applicationContext) {
        for (int i = 0; i < 3; i++) {
            User singletonUser = applicationContext.getBean("singletonUser", User.class);
            System.out.println("Singleton user: " + singletonUser);
        }

        for (int i = 0; i < 3; i++) {
            User prototypeUser = applicationContext.getBean("prototypeUser", User.class);
            System.out.println("Prototype user: " + prototypeUser);
        }
    }

    private static void scopedBeansByInjection(ApplicationContext applicationContext) {
        BeanScopeDemo beanScopeDemo = applicationContext.getBean(BeanScopeDemo.class);
        System.out.println(beanScopeDemo.singletonUser);
        System.out.println(beanScopeDemo.singletonUser2);
        System.out.println(beanScopeDemo.prototypeUser);
        System.out.println(beanScopeDemo.prototypeUser2);

        // 如果依赖注入集合类型的对象，singleton和prototype的bean均会存在一个，
        // 且prototype的bean有别与其他依赖注入的prototype的bean
        System.out.println(beanScopeDemo.users);
    }

    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Bean
    public static User singletonUser() {
        return createUser();
    }

    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Bean
    public static User prototypeUser() {
        return createUser();
    }

    private static User createUser() {
        User user = new User();
        user.setId(System.nanoTime());

        return user;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println(getClass().getSimpleName() + " is destroying");

        this.prototypeUser.destroy();
        this.prototypeUser2.destroy();

        for (Map.Entry<String, User> entry : users.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition =
                    ((ConfigurableListableBeanFactory) beanFactory).getBeanDefinition(beanName);
            if (beanDefinition.isPrototype()) {
                entry.getValue().destroy();
            }
        }
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
