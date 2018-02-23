package com.wangzhi.test.mock.dao;

import com.wangzhi.test.mock.core.Mapper;
import com.wangzhi.test.mock.model.Mock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MockMapper extends Mapper<Mock> {
    Mock selectMockByPath(@Param("path") String path, @Param("userId") String userId);

    List<Mock> selectMockByUser(String userId);
}