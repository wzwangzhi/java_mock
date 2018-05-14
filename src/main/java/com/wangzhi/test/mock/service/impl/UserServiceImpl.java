package com.wangzhi.test.mock.service.impl;

import com.wangzhi.test.mock.core.AbstractService;
import com.wangzhi.test.mock.dao.UserMapper;
import com.wangzhi.test.mock.model.User;
import com.wangzhi.test.mock.service.UserService;
import com.wangzhi.test.mock.web.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


/**
 * Created by CodeGenerator on 2018/01/22.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserMapper userMapper;
    @Resource
    private User user;

    @Override
    public User login(String userName, String userPass) {
        user.setUserName(userName);
        user.setUserPass(userPass);
        User user = userMapper.selectOne(this.user);
        if (user != null) {
            return user;
        }
        user = findBy("userName", userName);
        if (user != null) {
            return null;
        }
        return register(userName, userPass, userName);
    }

    @Override
    public User register(String userName, String userPass, String userNick) {
        user.setUserName(userName);
        user.setUserPass(userPass);
        user.setRegisterTime(new Date());
        user.setUserNick(userNick);
        userMapper.insert(user);
        return user;
    }
}
