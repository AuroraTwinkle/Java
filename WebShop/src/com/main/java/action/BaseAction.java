package com.main.java.action;


import com.main.java.service.*;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

@Controller("baseAction")
@Scope("prototype")
public class BaseAction<T> extends ActionSupport implements RequestAware, SessionAware, ApplicationAware, ModelDriven<T> {

    protected List<T> jsonList = null;

    //page和rows和分页有关，pageMap存放查询的数据，然后打包成json格式用的
    //page和rows实现get和set方法，pageMap只需要实现get方法即可，因为pageMap不是接收前台参数的，是让struts获取的
    protected Integer page;
    protected Integer rows;
    protected Map<String, Object> pageMap = null;

    @Resource
    protected CategoryService categoryService;
    @Resource
    protected AccountService accountService;
    @Resource
    protected ProductService productService;
    @Resource
    protected SorderService sorderService;
    @Resource
    protected ForderService forderService;
    @Resource
    protected UserService userService;

    protected Map<String,Object> request;
    protected Map<String,Object> session;
    protected Map<String,Object> application;


    @Override
    public void setApplication(Map<String,Object> application){
        this.application=application;
    }

    @Override
    public void setSession(Map<String,Object> session){
        this.session=session;
    }

    @Override
    public void setRequest(Map<String,Object> request){
        this.request=request;
    }

    protected T model;
    @Override
    public T getModel(){
        ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
        Class clazz = (Class)type.getActualTypeArguments()[0];
        try{
            model = (T)clazz.newInstance();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return model;
    }


    //get和set方法
    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPage(){
        return page;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getRows(){
        return rows;
    }

    public Map<String,Object> getPageMap(){
        System.out.println("--getPage--");
        return pageMap;
    }
}
