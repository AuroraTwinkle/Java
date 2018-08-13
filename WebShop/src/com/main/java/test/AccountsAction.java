package com.main.java.test;

import com.main.java.test.AccountsImpl;
import com.main.java.model.Account;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountsAction extends ActionSupport{
    private String name;
    private String pass;
    private String login;

    @Autowired
    private AccountsImpl accounts;

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }

    public String add(){
        Account account = new Account();
        account.setName(getName());
        account.setLogin(getLogin());
        account.setPass(getPass());
        accounts.add(account);
        return SUCCESS;
    }

    public void setAccounts(AccountsImpl accounts) {
        this.accounts = accounts;
    }
}
