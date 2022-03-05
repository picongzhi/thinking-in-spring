package com.pcz.thinking.in.spring.bean.lifecycle;

import com.pcz.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class UserHolder implements
        BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, EnvironmentAware,
        InitializingBean, SmartInitializingSingleton, DisposableBean {
    private final User user;

    private Integer number;

    private String description;

    private String beanName;

    private ClassLoader classLoader;

    private BeanFactory beanFactory;

    private Environment environment;

    public UserHolder(User user) {
        this.user = user;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", number=" + number +
                ", description='" + description + '\'' +
                ", beanName='" + beanName + '\'' +
                '}';
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void postConstruct() {
        this.description = "This is userHolder V3";
        System.out.println("postConstruct() => description: " + this.description);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.description = "This is userHolder V4";
        System.out.println("afterPropertiesSet() => description: " + this.description);
    }

    public void initMethod() {
        this.description = "This is userHolder V5";
        System.out.println("initMethod() => description: " + this.description);
    }

    @Override
    public void afterSingletonsInstantiated() {
        this.description = "This is userHolder V7";
        System.out.println("afterSingletonsInstantiated() => description: " + this.description);
    }

    @PreDestroy
    public void preDestroy() {
        this.description = "This is userHolder V9";
        System.out.println("preDestroy() => description: " + this.description);
    }

    @Override
    public void destroy() throws Exception {
        this.description = "This is userHolder V10";
        System.out.println("destroy() => description: " + this.description);
    }

    public void destroyMethod() {
        this.description = "This is userHolder V11";
        System.out.println("destroyMethod() => description: " + this.description);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("userHolder invoke finalize()");
    }
}
