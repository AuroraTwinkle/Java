package com.main.java.service;

import java.util.List;

public interface BaseService<T> {
    public void add(T t);
    public void update(T t);
    public void delete(int id);
    public T get (int id);
    public List<T> query();
}
