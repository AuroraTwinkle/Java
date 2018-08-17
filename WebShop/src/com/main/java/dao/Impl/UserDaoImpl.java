package com.main.java.dao.Impl;

import com.main.java.dao.UserDao;
import com.main.java.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    @Override
    public User login(User user){
        String hql = "from User u where u.login=:login and u.paa=:pass";
        return (User)getSession().createQuery(hql)
                .setString("login",user.getLogin())
                .setString("pass",user.getPass())
                .uniqueResult();
    }
}
