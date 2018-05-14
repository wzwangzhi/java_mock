package com.wangzhi.test.mock.web;

import com.wangzhi.test.mock.core.Result;
import com.wangzhi.test.mock.core.ResultGenerator;
import com.wangzhi.test.mock.model.MockItem;
import com.wangzhi.test.mock.model.MockResponse;
import com.wangzhi.test.mock.service.MockItemService;
import com.wangzhi.test.mock.service.MockResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CodeGenerator on 2018/01/22.
 */
@RestController
@RequestMapping("/wangzhi/mock")
public class MockController {

    Logger logger = LoggerFactory.getLogger(MockController.class);
    @Resource
    MockItemService mockItemService;
    @Resource
    MockResponseService mockResponseService;

    @RequestMapping("/list")
    public Result mockList(@RequestParam(defaultValue = "-1") String projectId) {
        if ("-1".equals(projectId)) {
            return ResultGenerator.genFailResult("projectId不能为空");
        }
        return ResultGenerator.genSuccessResult(mockItemService.selectMockByProject(projectId));
    }

    @RequestMapping("/delete")
    public boolean deleteMock(@RequestParam int mockId) {
        mockItemService.deleteById(mockId);
        return true;
    }

    @RequestMapping("/switch")
    public String switchMock(@RequestParam String mockId, @RequestParam boolean mockState) {
        String newState;
        if (mockState) {
            newState = "1";
        } else {
            newState = "0";
        }
        mockItemService.switchMock(mockId, newState);
        return newState;
    }

    @RequestMapping("/detail")
    public Result mockDetail(@RequestParam int mockId) {
        MockItem byId = mockItemService.findById(mockId);
        List<MockResponse> mockResponses = mockResponseService.selectMockResponseList(mockId);
        Map resultMap = new HashMap();
        resultMap.put("mock", byId);
        resultMap.put("responseList", mockResponses);
        return ResultGenerator.genSuccessResult(resultMap);
    }

    @RequestMapping("/save")
    public Result saveMock(@RequestParam(defaultValue = "-1") int mockId,
                           @RequestParam String mockDesc,
                           @RequestParam String mockUrl,
                           @RequestParam int mockState,
                           @RequestParam(defaultValue = "-1") int projectId,
                           @RequestParam(defaultValue = "-1") int mockGroupId,
                           @RequestParam String mockGroupDesc,
                           @RequestParam int mockResponseId) {
        if (mockId == -1) {
            MockItem item = mockItemService.selectMockByUrl(mockUrl, projectId + "");
            if (item != null) {
                return ResultGenerator.genFailResult("该URL已存在");
            }
        }
        MockItem mock = new MockItem();
        mock.setProjectId(projectId);
        mock.setMockGroupId(mockGroupId);
        mock.setMockGroupDesc(mockGroupDesc);
        mock.setMockUrl(mockUrl);
        mock.setMockDesc(mockDesc);
        mock.setMockState(mockState);
        mock.setMockResponseId(mockResponseId);
        if (mockId != -1) {
            mock.setMockId(mockId);
            mockItemService.update(mock);
        } else {
            mockItemService.save(mock);
        }
        return ResultGenerator.genSuccessResult(mockItemService.selectMockByUrl(mockUrl, projectId + ""));
    }

}
