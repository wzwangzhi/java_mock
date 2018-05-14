package com.wangzhi.test.mock.service.impl;

import com.wangzhi.test.mock.core.AbstractService;
import com.wangzhi.test.mock.dao.MockItemMapper;
import com.wangzhi.test.mock.model.MockItem;
import com.wangzhi.test.mock.service.MockItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2018/01/22.
 */
@Service
@Transactional
public class MockItemServiceImpl extends AbstractService<MockItem> implements MockItemService {

    @Resource
    MockItemMapper mockMapper;

    @Override
    public MockItem selectMockByUrl(String mockUrl, String projectId) {
        return mockMapper.selectMockByUrl(mockUrl, projectId);
    }

    @Override
    public void switchMock(String mockId, String mockState) {
        mockMapper.switchMock(mockId, mockState);
    }

    @Override
    public List<MockItem> selectMockByProject(String projectId) {
        return mockMapper.selectMockByProject(projectId);
    }
}
