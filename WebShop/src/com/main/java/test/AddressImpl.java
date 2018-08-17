package com.main.java.test;
import com.main.java.model.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.annotation.Resource;
import java.util.List;

public class AddressImpl implements Address {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void add() {
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        String hql = "from Account";
        List list = s.createQuery(hql).list();
        tx.commit();
        System.out.println(list);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}