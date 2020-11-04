package com.pcz.thinking.in.spring.bean.factory;

import com.pcz.thinking.in.spring.ioc.overview.domain.User;

/**
 * @author picongzhi
 */
public interface UserFactory {
    default User createUser() {
        return User.createUser();
    }
}
