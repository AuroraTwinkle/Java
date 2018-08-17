package com.main.java.test;
import com.main.java.model.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test {
    public static void main(String[] args)
    {
        ApplicationContext ac = new FileSystemXmlApplicationContext("web/WEB-INF/applicationContext.xml");
        Address ail = (AddressImpl) ac.getBean("address");
        ail.add();
    }

}
