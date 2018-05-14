package com.wangzhi.test.mock.web;

import com.wangzhi.test.mock.core.Result;
import com.wangzhi.test.mock.core.ResultGenerator;
import com.wangzhi.test.mock.kit.FileManager;
import com.wangzhi.test.mock.model.MockResponse;
import com.wangzhi.test.mock.service.MockResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by CodeGenerator on 2018/01/22.
 */
@RestController
@RequestMapping("/wangzhi/mock/response")
public class MockResponseController {

    Logger logger = LoggerFactory.getLogger(MockResponseController.class);
    @Resource
    MockResponseService mockResponseService;


    @RequestMapping("/save")
    public Result saveMock(@RequestParam(defaultValue = "-1") int responseId,
                           @RequestParam(defaultValue = "-1") int responseTag,
                           @RequestParam(defaultValue = "-1") int projectId,
                           @RequestParam(defaultValue = "-1") int mockId,
                           @RequestParam String responseDesc,
                           @RequestParam String response) {
        if (projectId == -1) {
            return ResultGenerator.genFailResult("projectId不能为空");
        }
        if (mockId == -1) {
            return ResultGenerator.genFailResult("mockId不能为空");
        }
        if (responseTag == -1) {
            return ResultGenerator.genFailResult("responseTag不能为空");
        }
        if (responseId == -1) {
            //添加response时检查tag是否存在
            List<MockResponse> mockResponses = mockResponseService.selectMockResponseList(mockId);
            for (MockResponse item : mockResponses) {
                if (item.getResponseTag() == responseTag) {
                    return ResultGenerator.genFailResult("该tag已存在");
                }
            }
        }
        String responsePath = getResponsePath(projectId, mockId, responseTag);
        if (!FileManager.getInstance().writeResponse(responsePath, response)) {
            logger.debug("文件写入失败");
            return ResultGenerator.genFailResult("服务器异常");
        }
        MockResponse mockResponse = new MockResponse();
        mockResponse.setResponseTag(responseTag);
        mockResponse.setProjectId(projectId);
        mockResponse.setMockId(mockId);
        mockResponse.setResponsePath(responsePath);
        mockResponse.setResponseDesc(responseDesc);
        if (responseId != -1) {
            mockResponse.setResponseId(responseId);
            mockResponseService.update(mockResponse);
        } else {
            mockResponseService.save(mockResponse);
        }
        return ResultGenerator.genSuccessResult("保存成功");
    }

    @RequestMapping("/detail")
    public MockResponse responseDetail(@RequestParam int responseId) {
        MockResponse mockResponse = mockResponseService.findById(responseId);
        if (mockResponse != null) {
            String responsePath = mockResponse.getResponsePath();
            String response = FileManager.getInstance().readResponse(responsePath);
            mockResponse.setResponsePath(response);
        }
        return mockResponse;
    }

    @RequestMapping("/delete")
    public boolean deleteResponse(@RequestParam int responseId) {
        mockResponseService.deleteById(responseId);
        return true;
    }

    /**
     * @param projectId
     * @param mockId
     * @param responseTag
     * @return responsePath
     */
    private String getResponsePath(int projectId, int mockId, int responseTag) {
        StringBuffer responsePath = new StringBuffer();
        responsePath.append(projectId);
        responsePath.append("/");
        responsePath.append(mockId);
        responsePath.append("/");
        responsePath.append(responseTag);
        return responsePath.toString();
    }
}
