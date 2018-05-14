package com.wangzhi.test.mock.service;

import com.wangzhi.test.mock.core.Service;
import com.wangzhi.test.mock.model.MockItem;

import java.util.List;

/**
 * 获取本地数据
 */
public interface MockItemService extends Service<MockItem> {
    MockItem selectMockByUrl(String mockUrl, String projectId);

    void switchMock(String mockId, String mockState);

    List<MockItem> selectMockByProject(String projectId);
}
