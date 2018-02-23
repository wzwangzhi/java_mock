package com.wangzhi.test.mock.service.impl;

import com.wangzhi.test.mock.core.AbstractService;
import com.wangzhi.test.mock.dao.MockMapper;
import com.wangzhi.test.mock.model.Mock;
import com.wangzhi.test.mock.service.MockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2018/01/22.
 */
@Service
@Transactional
public class MockServiceImpl extends AbstractService<Mock> implements MockService {

    @Resource
    MockMapper mockMapper;

    @Override
    public Mock selectMockByPath(String path,String userId) {
        return mockMapper.selectMockByPath(path,userId);
    }

    @Override
    public List<Mock> selectMockByUser(String userId) {
        return mockMapper.selectMockByUser(userId);
    }
}
