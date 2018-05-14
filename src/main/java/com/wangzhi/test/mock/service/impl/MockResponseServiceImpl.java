package com.wangzhi.test.mock.service.impl;

import com.wangzhi.test.mock.core.AbstractService;
import com.wangzhi.test.mock.dao.MockItemMapper;
import com.wangzhi.test.mock.dao.MockResponseMapper;
import com.wangzhi.test.mock.model.MockItem;
import com.wangzhi.test.mock.model.MockResponse;
import com.wangzhi.test.mock.service.MockItemService;
import com.wangzhi.test.mock.service.MockResponseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2018/01/22.
 */
@Service
@Transactional
public class MockResponseServiceImpl extends AbstractService<MockResponse> implements MockResponseService {

    @Resource
    MockResponseMapper mockResponseMapper;

    @Override
    public MockResponse selectMockResponseByMock(int mockId, int responseId) {
        return mockResponseMapper.selectMockResponseByMock(mockId,responseId);
    }

    @Override
    public List<MockResponse> selectMockResponseList(int mockId) {
        return mockResponseMapper.selectMockResponseList(mockId);
    }

    @Override
    public void deleteMockResponseByMock(int mockId) {
        mockResponseMapper.deleteMockResponseByMock(mockId);
    }
}
