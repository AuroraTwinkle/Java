package com.main.java.service.Impl;

import com.main.java.model.Sorder;
import com.main.java.service.SorderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sorderService")
public class SorderServiceImpl extends BaseServiceImpl<Sorder> implements SorderService {
    @Override
    public List<Object> querySale(int number){
        return sorderDao.querySale(number);
    }
}
