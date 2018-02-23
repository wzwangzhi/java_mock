package com.wangzhi.test.mock.service;

import com.wangzhi.test.mock.core.Service;
import com.wangzhi.test.mock.model.Mock;

import java.util.List;

/**
 * 获取本地数据
 */
public interface MockService extends Service<Mock> {
    Mock selectMockByPath(String path, String userId);

    List<Mock> selectMockByUser(String userId);
}
