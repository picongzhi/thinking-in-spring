package com.pcz.thinking.in.spring.bean.definition;

import com.pcz.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @author picongzhi
 */
public class BeanDefinitionCreationDemo {
    public static void main(String[] args) {
        // 通过 BeanDefinitionBuilder 构造
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
                .genericBeanDefinition(User.class)
                .addPropertyValue("id", 1)
                .addPropertyValue("name", "picongzhi");
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        System.out.println(beanDefinition);

        // 通过 GenericBeanDefinition 以及其派生类实例化
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues
                .add("id", 1)
                .add("name", "picongzhi");
        genericBeanDefinition.setPropertyValues(propertyValues);
        System.out.println(genericBeanDefinition);
    }
}
