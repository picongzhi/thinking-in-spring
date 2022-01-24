package com.pcz.thinking.in.spring.bean.scope.custom;

import com.pcz.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class ThreadLocalScopeDemo {
    @Bean
    @Scope(ThreadLocalScope.SCOPE_NAME)
    public User user() {
        User user = new User();
        // id为当前线程的线程id
        user.setId(Thread.currentThread().getId());

        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ThreadLocalScopeDemo.class);

        ThreadLocalScope scope = new ThreadLocalScope();
        // 注册scope
        applicationContext.addBeanFactoryPostProcessor(beanFactory ->
                beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME, scope));

        applicationContext.refresh();

        // 依赖查找
        threadLocalScopeBeansByLookup(applicationContext);

        applicationContext.close();
    }

    private static void threadLocalScopeBeansByLookup(AnnotationConfigApplicationContext applicationContext) {
        int threadNums = 10;
        for (int i = 0; i < threadNums; i++) {
            Thread thread = new Thread(() -> {
                User user = applicationContext.getBean(User.class);
                System.out.println("Current thread: " + Thread.currentThread().getId() + ", user: " + user);
            });

            thread.start();

            try {
                thread.join();
            } catch (InterruptedException e) {
            }
        }
    }
}
