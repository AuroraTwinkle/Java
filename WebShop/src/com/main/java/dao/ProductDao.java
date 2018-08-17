package com.main.java.dao;

import com.main.java.model.Product;

import java.util.List;

public interface ProductDao extends BaseDao<Product> {
    public List<Product> queryJoinCategory(String type, int page, int size);
    public Long getCount(String type);
    public void deleteByIds(String ids);
    public List<Product> queryByCategoryId(int cid);
}
