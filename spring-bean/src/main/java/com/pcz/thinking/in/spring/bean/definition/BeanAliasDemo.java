package com.pcz.thinking.in.spring.bean.definition;

import com.pcz.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author picongzhi
 */
public class BeanAliasDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath:/META-INF/bean-definition-context.xml");
        User user = applicationContext.getBean("user", User.class);
        User aliasUser = applicationContext.getBean("alias-user", User.class);
        System.out.println(user == aliasUser);
    }
}
