package com.pcz.thinking.in.spring.configuration.metadata;

import com.pcz.thinking.in.spring.ioc.overview.domain.User;
import com.pcz.thinking.in.spring.ioc.overview.domain.enums.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 * @author picongzhi
 */
@PropertySource(
        name = "yamlPropertySource",
        value = "classpath:META-INF/user.yaml",
        factory = YamlPropertySourceFactory.class
)
public class AnnotatedBasedYamlPropertySourceDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotatedBasedYamlPropertySourceDemo.class);

        applicationContext.refresh();

        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);

        applicationContext.close();
    }

    @Bean
    public User user(
            @Value("${user.id}") Long id,
            @Value("${user.name}") String name,
            @Value("${user.city}") City city) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setCity(city);

        return user;
    }
}
