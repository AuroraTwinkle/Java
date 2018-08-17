package com.main.java.dao;

import com.main.java.model.Forder;

public interface ForderDao extends BaseDao<Forder> {
    public void updateStatusById(int id, int sid);
}
