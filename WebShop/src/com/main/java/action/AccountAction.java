package com.main.java.action;

import com.main.java.model.Account;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("accountAction")
@Scope("prototype")
public class AccountAction extends BaseAction<Account> {
    public String query(){
        jsonList = accountService.query();
        return "jsonList";
    }
}
