package com.main.java.action;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

@Controller("sendAction")
public class SendAction extends ActionSupport {
    public String execute(){
        return "send";
    }
}
