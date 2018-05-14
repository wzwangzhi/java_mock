package com.wangzhi.test.mock.dao;


import com.wangzhi.test.mock.core.Mapper;
import com.wangzhi.test.mock.model.MockItem;
import com.wangzhi.test.mock.model.MockProject;
import com.wangzhi.test.mock.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MockProjectMapper extends Mapper<MockProject> {
    List<MockProject> selectMockProjectByUser(@Param("userId") String userId);
    MockProject selectMockProjectById(@Param("projectId") int projectId);
}