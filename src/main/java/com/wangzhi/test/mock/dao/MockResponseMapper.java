package com.wangzhi.test.mock.dao;


import com.wangzhi.test.mock.core.Mapper;
import com.wangzhi.test.mock.model.MockResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MockResponseMapper extends Mapper<MockResponse> {
    MockResponse selectMockResponseByMock(@Param("mockId") int mockId, @Param("responseId") int responseId);

    List<MockResponse> selectMockResponseList(@Param("mockId") int mockId);

    void deleteMockResponseByMock(@Param("mockId") int mockId);
}