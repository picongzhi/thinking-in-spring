package com.pcz.thinking.in.spring.bean.definition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author picongzhi
 */
public class BeanGarbageCollectionDemo {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);
        applicationContext.refresh();
        applicationContext.close();

        Thread.sleep(5000);
        System.gc();
        Thread.sleep(5000);
    }
}
