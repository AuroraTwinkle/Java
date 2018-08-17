package com.main.java.dao.Impl;

import com.main.java.dao.ForderDao;
import com.main.java.model.Forder;
import org.springframework.stereotype.Repository;

@Repository("forderDao")
public class ForderDaoImpl extends BaseDaoImpl<Forder> implements ForderDao {
    @Override
    public void updateStatusById(int id, int sid){
        String hql = "update Forder f set f.status.id=:sid where f.id=:id";
        getSession().createQuery(hql)
        .setInteger("sid",sid)
        .setInteger("id",id)
        .executeUpdate();
    }
}
