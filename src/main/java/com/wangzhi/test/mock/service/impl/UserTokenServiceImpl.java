package com.wangzhi.test.mock.service.impl;

import com.wangzhi.test.mock.core.AbstractService;
import com.wangzhi.test.mock.dao.UserTokenMapper;
import com.wangzhi.test.mock.model.UserToken;
import com.wangzhi.test.mock.service.UserTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/01/22.
 */
@Service
@Transactional
public class UserTokenServiceImpl extends AbstractService<UserToken> implements UserTokenService {
    Logger logger = LoggerFactory.getLogger(UserTokenServiceImpl.class);
    @Resource
    UserTokenMapper userTokenMapper;

    @Override
    public UserToken selectUserTokenByToken(String token) {
        return userTokenMapper.selectUserTokenByToken(token);
    }
}
