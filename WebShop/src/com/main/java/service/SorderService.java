package com.main.java.service;

import com.main.java.model.Sorder;

import java.util.List;

public interface SorderService extends BaseService<Sorder> {
    public List<Object> querySale(int number);
}
