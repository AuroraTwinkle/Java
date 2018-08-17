package com.main.java.dao.Impl;

import org.springframework.stereotype.Repository;
import com.main.java.dao.AccountDao;
import com.main.java.model.Account;

@Repository("accountDao")
public class AccountDaoImpl extends BaseDaoImpl<Account> implements AccountDao {

}
