package com.wangzhi.test.mock.dao;


import com.wangzhi.test.mock.core.Mapper;
import com.wangzhi.test.mock.model.UserToken;
import org.apache.ibatis.annotations.Param;

public interface UserTokenMapper extends Mapper<UserToken> {
    UserToken selectUserTokenByToken(@Param("token") String token);
}