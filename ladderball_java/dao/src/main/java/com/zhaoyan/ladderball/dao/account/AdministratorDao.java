package com.zhaoyan.ladderball.dao.account;


import com.zhaoyan.ladderball.domain.account.db.Administrator;

public interface AdministratorDao {

    /**
     * 根据账号和密码查询
     */
    Administrator getAdministrator(String userName, String password);

    /**
     * 根据账号查询
     */
    Administrator getAdministrator(String userName);
}
