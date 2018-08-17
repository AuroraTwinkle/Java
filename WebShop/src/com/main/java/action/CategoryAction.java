package com.main.java.action;

import com.main.java.model.Category;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;


@Controller("categoryAction")
@Scope("prototype")
public class CategoryAction extends BaseAction<Category> {
    public String queryJoinAccount(){
        System.out.println("type:"+model.getType());
        System.out.println("page:"+page);
        System.out.println("rows:"+rows);

        pageMap = new HashMap<String, Object>();

        List<Category> categoryList = categoryService.queryJoinAccount(model.getType(),page,rows);
        pageMap.put("rows",categoryList);
        System.out.println(categoryList);
        Long total = categoryService.getCount(model.getType());

        pageMap.put("total",total);

        return "jsonMap";
    }
}
