package com.wangzhi.test.mock.dao;

import com.wangzhi.test.mock.core.Mapper;
import com.wangzhi.test.mock.model.MockItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MockItemMapper extends Mapper<MockItem> {
    MockItem selectMockByUrl(@Param("mockUrl") String mockUrl, @Param("projectId") String projectId);

    List<MockItem> selectMockByProject(@Param("projectId") String projectId);

    void switchMock(@Param("mockId") String mockId, @Param("mockState") String mockState);
}