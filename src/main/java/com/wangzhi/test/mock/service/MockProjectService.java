package com.wangzhi.test.mock.service;

import com.wangzhi.test.mock.core.Service;
import com.wangzhi.test.mock.model.MockProject;

import java.util.List;

/**
 * 获取本地数据
 */
public interface MockProjectService extends Service<MockProject> {

    List<MockProject> selectMockProjectByUser(String userId);

    MockProject selectMockProjectById(int projectId);
}
