package com.main.java.dao;

import com.main.java.model.Category;

import java.util.List;

public interface CategoryDao extends BaseDao<Category> {
    public List<Category> queryJoinAccount(String type, int page, int size);
    public Long getCount(String type);
    public void deleteByIds(String ids);
    public List<Category> queryByHot(boolean hot);
}
