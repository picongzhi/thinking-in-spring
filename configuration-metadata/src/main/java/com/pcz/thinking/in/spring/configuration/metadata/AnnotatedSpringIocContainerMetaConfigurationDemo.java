package com.pcz.thinking.in.spring.configuration.metadata;

import com.pcz.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Map;

@ImportResource("classpath:META-INF/dependency-lookup-context.xml")
@Import(User.class)
@PropertySource(value = "classpath:META-INF/users.properties")
@PropertySource(value = "classpath:META-INF/users.properties")
public class AnnotatedSpringIocContainerMetaConfigurationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotatedSpringIocContainerMetaConfigurationDemo.class);
        applicationContext.refresh();

        Map<String, User> userMap = applicationContext.getBeansOfType(User.class);
        userMap.forEach((name, user) -> {
            System.out.println("Name: " + name + ", user: " + user);
        });

        applicationContext.close();
    }

    @Bean
    public User configuredUser(
            @Value("${user.id}") Long id,
            @Value("${user.name}") String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);

        return user;
    }
}
