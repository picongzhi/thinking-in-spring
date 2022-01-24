package com.pcz.thinking.in.spring.bean.scope.web;

import com.pcz.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author picongzhi
 */
@Configuration
@EnableWebMvc
public class WebConfiguration {
    @Bean
    @RequestScope
//    @SessionScope
//    @ApplicationScope
    public User user() {
        return User.createUser();
    }
}
