package com.wangzhi.test.mock.service;

import com.wangzhi.test.mock.core.Service;
import com.wangzhi.test.mock.model.MockItem;
import com.wangzhi.test.mock.model.MockResponse;

import java.util.List;

/**
 * 获取本地数据
 */
public interface MockResponseService extends Service<MockResponse> {

    MockResponse selectMockResponseByMock(int mockId, int responseId);

    List<MockResponse> selectMockResponseList(int mockId);

    void deleteMockResponseByMock(int mockId);
}
