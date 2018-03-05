package com.wangzhi.test.mock.service;


import com.wangzhi.test.mock.core.Service;
import com.wangzhi.test.mock.model.Sign;
import com.wangzhi.test.mock.model.User;

import java.util.List;

/**
 * Created by CodeGenerator on 2018/01/22.
 */
public interface SignService extends Service<Sign> {
    List<String> signAll();
}
