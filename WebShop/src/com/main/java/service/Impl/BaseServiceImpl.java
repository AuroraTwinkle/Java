package com.main.java.service.Impl;

import com.main.java.dao.*;
import com.main.java.service.BaseService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@SuppressWarnings("unchecked")
@Service("baseService")
@Lazy(true)
public class BaseServiceImpl<T> implements BaseService<T> {
    private Class clazz;

    public BaseServiceImpl(){
        System.out.println("this代表的是当前调用构造方法的对象" + this);
        System.out.println("获取当前this对象的父类信息" + this.getClass().getSuperclass());
        System.out.println("获取当前this对象的父类信息(包括泛型信息)" + this.getClass().getGenericSuperclass());
        //拿到泛型的参数类型
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class)type.getActualTypeArguments()[0];
    }

    @PostConstruct
    public void init(){
        String clazzName = clazz.getSimpleName();
        String clazzDao = clazzName.substring(0, 1).toLowerCase()
                + clazzName.substring(1) + "Dao"; //例如Account==>accountDao
        System.out.println(clazzDao);
        try{
            Field clazzField = this.getClass().getSuperclass().getDeclaredField(clazzDao);
            Field baseField = this.getClass().getSuperclass().getDeclaredField("baseDao");
            baseField.set(this, clazzField.get(this));
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    protected BaseDao baseDao;
    @Resource
    protected AccountDao accountDao;
    @Resource
    protected CategoryDao categoryDao;
    @Resource
    protected ForderDao forderDao;
    @Resource
    protected ProductDao productDao;
    @Resource
    protected SorderDao sorderDao;
    @Resource
    protected UserDao userDao;

    @Override
    public void save(T t){
        baseDao.save(t);
    }

    @Override
    public void update(T t){
        baseDao.update(t);
    }

    @Override
    public void delete(int id){
        baseDao.delete(id);
    }

    @Override
    public T get(int id){
        return (T) baseDao.get(id);
    }

    @Override
    public List<T> query(){
        return baseDao.query();
    }


}
