package com.pcz.thinking.in.spring.bean.factory;

import com.pcz.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author picongzhi
 */
public class UserFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
