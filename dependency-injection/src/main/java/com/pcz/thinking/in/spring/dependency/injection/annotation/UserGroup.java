package com.pcz.thinking.in.spring.dependency.injection.annotation;


import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 * @author picongzhi
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier
public @interface UserGroup {
}
