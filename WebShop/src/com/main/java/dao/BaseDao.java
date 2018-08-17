package com.main.java.dao;

import java.util.List;

public interface BaseDao<T> {
    public void add(T t);

    public void update(T t);

    public void delete(int id);

    public T get(int id);

    public List<T> query();

}