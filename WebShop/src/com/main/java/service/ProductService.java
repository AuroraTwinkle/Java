package com.main.java.service;

import com.main.java.model.Product;

import java.util.List;

public interface ProductService extends BaseService<Product> {
    public List<Product> queryJoinCategory(String type, int page, int size);
    public Long getCount(String type);
    public void deleteByIds(String ids);
    public List<Product> queryByCategoryId(int cid);
}
