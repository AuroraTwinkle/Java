package com.main.java.action;

import com.main.java.model.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.ByteArrayInputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller("productAction")
@Scope("prototype")
public class ProductAction extends BaseAction<Product> {
    public String queryJoinCategory(){
        System.out.println("name:" + model.getName());
        System.out.println("page：" + page);
        System.out.println("rows：" + rows);

        pageMap = new HashMap<String, Object>();

        List<Product> productList = productService.queryJoinCategory(model.getName(),page,rows);
        pageMap.put("rows",productList);
        Long total = productService.getCount(model.getName());
        pageMap.put("total",total);


        return "jsonMap";
    }

    public void save(){
        String pic = fileUpload.uploadFile(fileImage);

        model.setPic(pic);
        model.setDate(new Timestamp(new Date().getTime()));
        System.out.println(model);

        productService.save(model);
    }

    public String deleteByIds(){
        System.out.println(ids);
        productService.deleteByIds(ids);
        inputStream = new ByteArrayInputStream("true".getBytes());
        return "stream";
    }

    public void uodate(){
        String pic = fileUpload.uploadFile(fileImage);
        model.setPic(pic);
        model.setDate(new Timestamp(new Date().getTime()));
        System.out.println((model));
        productService.update(model);
    }

    public String get(){
        request.put("product",productService.get(model.getId()));
        return "detail";
    }
}
