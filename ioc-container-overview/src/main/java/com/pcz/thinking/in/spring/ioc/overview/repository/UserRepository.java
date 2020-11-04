package com.pcz.thinking.in.spring.ioc.overview.repository;

import com.pcz.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

/**
 * @author picongzhi
 */
public class UserRepository {
    private Collection<User> users;

    private BeanFactory beanFactory;

    private ObjectFactory<ApplicationContext> objectFactory;

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public ObjectFactory<ApplicationContext> getObjectFactory() {
        return objectFactory;
    }

    public void setObjectFactory(ObjectFactory<ApplicationContext> objectFactory) {
        this.objectFactory = objectFactory;
    }

    @Override
    public String toString() {
        return "UserRepository{" +
                "users=" + users +
                ", beanFactory=" + beanFactory +
                '}';
    }
}
