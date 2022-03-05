package com.pcz.thinking.in.spring.configuration.metadata;

import com.pcz.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

@PropertySource(value = "classpath:META-INF/users.properties")
public class PropertySourceDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(PropertySourceDemo.class);

        Map<String, Object> properties = new HashMap<>();
        properties.put("user.id", 1L);
        properties.put("user.name", "从之");
        MapPropertySource propertySource = new MapPropertySource("user-source", properties);

        MutablePropertySources propertySources = applicationContext
                .getEnvironment().getPropertySources();
        propertySources.addFirst(propertySource);

        applicationContext.refresh();

        User user = applicationContext.getBean(User.class);
        System.out.println(user);

        propertySources.forEach(System.out::println);

        applicationContext.close();
    }

    @Bean
    public User user(@Value("${user.id}") Long id,
                     @Value("${user.name}") String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);

        return user;
    }
}
