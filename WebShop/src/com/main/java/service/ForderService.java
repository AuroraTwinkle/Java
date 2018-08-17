package com.main.java.service;

import com.main.java.model.Forder;

import java.math.BigDecimal;

public interface ForderService extends BaseService<Forder> {
    public void updateStatusById(int id, int sid);
}
