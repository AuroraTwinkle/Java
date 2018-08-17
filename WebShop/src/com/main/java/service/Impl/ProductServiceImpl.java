package com.main.java.service.Impl;

import com.main.java.model.Product;
import com.main.java.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {
    @Override
    public List<Product> queryJoinCategory(String name, int page, int size){
        return productDao.queryJoinCategory(name, page, size);
    }

    @Override
    public Long getCount(String name){
        return productDao.getCount(name);
    }

    @Override
    public void deleteByIds(String ids){
        productDao.deleteByIds(ids);
    }

    @Override
    public List<Product> queryByCategoryId(int cid){
        return productDao.queryByCategoryId(cid);
    }
}
