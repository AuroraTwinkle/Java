package com.main.java.dao;

import com.main.java.model.Sorder;

import java.util.List;

public interface SorderDao extends BaseDao<Sorder> {
    public List<Object> querySale(int number);
}
