package com.main.java.service;

import com.main.java.model.Category;

import java.util.List;

public interface CategoryService extends BaseService<Category> {
    public List<Category> queryJoinAccount(String type, int page, int size);
    public Long getCount(String type);
    public void deleteByIds(String ids);
    public List<Category> queryByHot(boolean hot);
}
