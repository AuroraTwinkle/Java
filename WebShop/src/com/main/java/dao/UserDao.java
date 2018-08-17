package com.main.java.dao;

import com.main.java.model.User;

public interface UserDao extends BaseDao<User> {
    public User login(User user);
}
