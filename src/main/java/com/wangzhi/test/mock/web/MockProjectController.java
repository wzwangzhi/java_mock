package com.wangzhi.test.mock.web;

import com.wangzhi.test.mock.core.Result;
import com.wangzhi.test.mock.core.ResultGenerator;
import com.wangzhi.test.mock.interceptor.MyTokenInterceptor;
import com.wangzhi.test.mock.model.MockItem;
import com.wangzhi.test.mock.model.MockProject;
import com.wangzhi.test.mock.service.MockItemService;
import com.wangzhi.test.mock.service.MockProjectService;
import com.wangzhi.test.mock.service.MockResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CodeGenerator on 2018/01/22.
 */
@RestController
@RequestMapping("/wangzhi/mock/project")
public class MockProjectController {

    Logger logger = LoggerFactory.getLogger(MockProjectController.class);
    @Resource
    MockProjectService mockProjectService;
    @Resource
    MockItemService mockItemService;
    @Resource
    MockResponseService mockResponseService;


    @RequestMapping("/list")
    public Result projectList(@RequestParam String token) {
        List<MockProject> mockProjects = mockProjectService.selectMockProjectByUser(MyTokenInterceptor.getUserIdByToken(token));
        if (mockProjects == null) {
            mockProjects = new ArrayList<>();
        }
        return ResultGenerator.genSuccessResult(mockProjects);
    }

    @RequestMapping("/delete")
    public Result deleteProject(@RequestParam int projectId) {
        List<MockItem> mockItems = mockItemService.selectMockByProject(projectId + "");
        for (MockItem item : mockItems) {
            //删除返回值
            mockResponseService.deleteMockResponseByMock(item.getMockId());
            //删除mock
            mockItemService.deleteById(item.getMockId());
        }
        mockProjectService.deleteById(projectId);
        return ResultGenerator.genSuccessResult("删除成功");
    }

    @RequestMapping("/save")
    public Result saveProject(@RequestParam(defaultValue = "-1") int projectId,
                              @RequestParam String token,
                              @RequestParam String projectName,
                              @RequestParam String projectBaseUrl,
                              @RequestParam String projectDesc) {
        String userIdByToken = MyTokenInterceptor.getUserIdByToken(token);
        MockProject mockProject = new MockProject();
        mockProject.setUserId(Integer.valueOf(userIdByToken));
        mockProject.setProjectName(projectName);
        mockProject.setProjectBaseUrl(projectBaseUrl);
        mockProject.setProjectDesc(projectDesc);
        if (projectId <= 0) {
            mockProjectService.save(mockProject);
        } else {
            mockProject.setProjectId(projectId);
            mockProjectService.update(mockProject);
        }
        return ResultGenerator.genSuccessResult("保存成功");
    }

}
