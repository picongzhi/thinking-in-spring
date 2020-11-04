package com.pcz.thinking.in.spring.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author picongzhi
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {
    // 1. 基于@PostConstruct注解
    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct: init() 初始化");
    }

    // 2. 自定义初始化方法
    public void initUserFactory() {
        System.out.println("自定义初始化方法: initUserFactory()");
    }

    // 3. 实现InitializingBean接口
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean: afterPropertiesSet()");
    }

    // 1. @PreDestroy
    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy: preDestroy() 销毁");
    }

    // 2. 自动化销毁方法
    public void doDestroy() {
        System.out.println("自定义销毁方法: doDestroy()");
    }

    // 3. 实现DisposableBean接口
    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean: destroy()");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("垃圾回收中");
    }
}
