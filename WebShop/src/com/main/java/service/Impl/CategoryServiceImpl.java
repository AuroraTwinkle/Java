package com.main.java.service.Impl;

import com.main.java.model.Category;
import com.main.java.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {
    @Override
    public List<Category> queryJoinAccount(String type,int page,int size){
        return categoryDao.queryJoinAccount(type,page,size);
    }

    @Override
    public Long getCount(String type){
        return categoryDao.getCount(type);
    }

    @Override
    public void deleteByIds(String ids){
        categoryDao.deleteByIds(ids);
    }

    @Override
    public List<Category> queryByHot(boolean hot){
        return categoryDao.queryByHot(hot);
    }
}
