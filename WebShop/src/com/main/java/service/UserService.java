package com.main.java.service;

import com.main.java.model.User;

public interface UserService extends BaseService<User> {
    public User login(User user);
}
