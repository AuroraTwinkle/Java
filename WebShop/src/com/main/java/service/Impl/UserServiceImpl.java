package com.main.java.service.Impl;

import com.main.java.model.User;
import com.main.java.service.UserService;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Override
    public User login(User user){
        return userDao.login(user);
    }
}
