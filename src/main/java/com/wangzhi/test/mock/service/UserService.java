package com.wangzhi.test.mock.service;


import com.wangzhi.test.mock.core.Service;
import com.wangzhi.test.mock.model.User;

/**
 * Created by CodeGenerator on 2018/01/22.
 */
public interface UserService extends Service<User> {
    User login(String userName, String userPass);//登录

    User register(String userName, String userPass, String userNick);//注册
}
