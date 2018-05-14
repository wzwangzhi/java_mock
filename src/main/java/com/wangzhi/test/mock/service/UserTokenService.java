package com.wangzhi.test.mock.service;


import com.wangzhi.test.mock.core.Service;
import com.wangzhi.test.mock.model.Sign;
import com.wangzhi.test.mock.model.UserToken;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by CodeGenerator on 2018/01/22.
 */
public interface UserTokenService extends Service<UserToken> {
    UserToken selectUserTokenByToken(String token);
}
