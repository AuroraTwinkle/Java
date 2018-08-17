package com.main.java.dao.Impl;

import java.util.List;

import com.main.java.dao.CategoryDao;
import com.main.java.model.Category;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;


@SuppressWarnings("unchecked")
@Repository("categoryDao")
public class CategoryDaoImpl extends BaseDaoImpl<Category> implements CategoryDao {

    @Override
    public List<Category> queryJoinAccount(String type, int page,int size){
        String hql = "from Category c left join fetch c.account where c.type like :type";
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        List list=s.createQuery(hql)
                .setString("type","%"+type+"%")
                .setFirstResult((page-1)*size)
                .setMaxResults(size).list();
        tx.commit();
        return list;

    }

    @Override
    public Long getCount(String type){
        String hql = "select count(c) from Category c where c.type like:type";
        return (Long)getSession().createQuery(hql)
                .setString("type","%"+type+"%")
                .uniqueResult();
    }

    @Override
    public void deleteByIds(String ids){
        String hql = "delete from Category c where c.id in ("+ids+")";
        getSession().createQuery(hql).executeUpdate();
    }

    @Override
    public List<Category> queryByHot(boolean hot){
        String hql = "from Category c where c.hot=:hot";
        return getSession().createQuery(hql)
                .setBoolean("hot",hot)
                .list();
    }

}
