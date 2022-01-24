package com.pcz.thinking.in.spring.dependency.injection.annotation;

import java.lang.annotation.*;

/**
 * @author picongzhi
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectedUser {
}
