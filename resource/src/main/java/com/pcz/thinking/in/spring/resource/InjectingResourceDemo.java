package com.pcz.thinking.in.spring.resource;

import com.pcz.thinking.in.spring.resource.util.ResourceUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

/**
 * @author picongzhi
 * @see org.springframework.core.io.Resource
 * @see org.springframework.beans.factory.annotation.Value
 * @see org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor
 */
public class InjectingResourceDemo {
    @Value("classpath:META-INF/default.properties")
    private Resource resource;

    @Value("classpath*:META-INF/*.properties")
    private Resource[] resources;

    @Value("${user.dir}")
    private String userDir;

    @PostConstruct
    public void init() {
        System.out.println(ResourceUtils.getContent(resource));
        System.out.println(userDir);
        Stream.of(resources)
                .map(ResourceUtils::getContent)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(InjectingResourceDemo.class);

        applicationContext.refresh();

        applicationContext.close();
    }
}
