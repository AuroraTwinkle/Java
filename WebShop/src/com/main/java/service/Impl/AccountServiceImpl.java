package com.main.java.service.Impl;

import com.main.java.model.Account;
import com.main.java.service.AccountService;
import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService {

}
