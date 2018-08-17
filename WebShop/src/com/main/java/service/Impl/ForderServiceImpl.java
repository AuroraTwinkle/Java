package com.main.java.service.Impl;


import com.main.java.model.Forder;
import com.main.java.service.ForderService;
import org.springframework.stereotype.Service;


@Service("forderService")
public class ForderServiceImpl extends BaseServiceImpl<Forder> implements ForderService {
    @Override
    public void updateStatusById(int id, int sid){
        forderDao.updateStatusById(id,sid);
    }
}
