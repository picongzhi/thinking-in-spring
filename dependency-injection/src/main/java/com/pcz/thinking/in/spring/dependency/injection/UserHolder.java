package com.pcz.thinking.in.spring.dependency.injection;

import com.pcz.thinking.in.spring.ioc.overview.domain.User;

/**
 * @author picongzhi
 */
public class UserHolder {
    private User user;

    public UserHolder() {
    }

    public UserHolder(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
