package com.wangzhi.test.mock.service.impl;

import com.wangzhi.test.mock.core.AbstractService;
import com.wangzhi.test.mock.dao.MockProjectMapper;
import com.wangzhi.test.mock.model.MockProject;
import com.wangzhi.test.mock.service.MockProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2018/01/22.
 */
@Service
@Transactional
public class MockProjectServiceImpl extends AbstractService<MockProject> implements MockProjectService {
    @Resource
    MockProjectMapper mockProjectMapper;

    @Override
    public List<MockProject> selectMockProjectByUser(String userId) {
        return mockProjectMapper.selectMockProjectByUser(userId);
    }

    @Override
    public MockProject selectMockProjectById(int projectId) {
        return mockProjectMapper.selectMockProjectById(projectId);
    }
}
