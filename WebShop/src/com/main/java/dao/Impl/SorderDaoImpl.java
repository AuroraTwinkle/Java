package com.main.java.dao.Impl;

import com.main.java.dao.SorderDao;
import com.main.java.model.Sorder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("sorderDao")
public class SorderDaoImpl extends BaseDaoImpl<Sorder> implements SorderDao {
    @Override
    public List<Object> querySale(int number){
        String hql = "select s.name,sum(s.number) from Sorder s join s.product group by s.product.id";
        return getSession().createQuery(hql)
                .setFirstResult(0)
                .setMaxResults(number)
                .list();
    }
}
