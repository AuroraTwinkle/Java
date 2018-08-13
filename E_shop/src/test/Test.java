package test;

import service.Address;
import service.AddressImpl;
import bean.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by kinthon on 17-3-31.
 */
public class Test {
    public static void main(String[] args)
    {
        ApplicationContext ac = new FileSystemXmlApplicationContext("web/WEB-INF/applicationContext.xml");
        Address ail = (AddressImpl)ac.getBean("address");
        Account al = new Account();
        al.setName("kaka");
        al.setLogin("aurora");
        al.setPass("888888");
        ail.add(al);
    }
}
