package com.main.java.test;

import com.main.java.model.Account;
import com.main.java.test.Accounts;
import com.main.java.test.AccountsImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test {
    public static void main(String[] args)
    {
        ApplicationContext ac = new FileSystemXmlApplicationContext("web/WEB-INF/applicationContext.xml");
        Accounts acs = (AccountsImpl)ac.getBean("account");
        Account act = new Account();
        act.setLogin("admin");
        act.setName("aurora");
        act.setPass("158999");
        acs.add(act);
    }

}
