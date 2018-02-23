package com.wangzhi.test.mock.web;

import com.wangzhi.test.mock.core.Result;
import com.wangzhi.test.mock.core.ResultGenerator;
import com.wangzhi.test.mock.model.Mock;
import com.wangzhi.test.mock.service.MockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by CodeGenerator on 2018/01/22.
 */
@RestController
public class MockController {

    Logger logger = LoggerFactory.getLogger(MockController.class);
    @Resource
    MockService mockService;

    @RequestMapping("/wangzhi/mock/list")
    public Result mockList(@RequestParam String userId) {
        if (userId == null || "".equals(userId)) {
            return ResultGenerator.genFailResult("userId不能为空");
        }
        return ResultGenerator.genSuccessResult(mockService.selectMockByUser(userId));
    }

    @RequestMapping("/wangzhi/mock/delete")
    public boolean deleteMock(@RequestParam int id) {
        mockService.deleteById(id);
        return true;
    }

    @RequestMapping("/wangzhi/mock/save")
    public boolean saveMock(@RequestParam(defaultValue = "-1") int id, @RequestParam(defaultValue = "-1") int userId, @RequestParam String path, @RequestParam String response, @RequestParam String remote_root, @RequestParam String is_open) {
        Mock mock = new Mock();
        mock.setPath(path);
        mock.setResponse(response);
        mock.setIs_open(is_open);
        mock.setRemote_root(remote_root);
        mock.setUser_id(userId);
        if (id != -1) {
            mock.setId(id);
            mockService.update(mock);
        } else {
            mockService.save(mock);
        }
        return true;
    }

    @RequestMapping("/*")
    public Result aMock() {
        logger.debug("/* mocked");
        return ResultGenerator.genSuccessResult("mock success");
    }

    @RequestMapping("/*/*")
    public Result bMock(@RequestParam(defaultValue = "wangzhi") String channel) {
        logger.debug("/*/* mocked");
        return ResultGenerator.genSuccessResult("mock success");
    }

    @RequestMapping("/*/*/*")
    public Result cMock() {
        logger.debug("/*/*/* mocked");
        return ResultGenerator.genSuccessResult("mock success");
    }

    @RequestMapping("/*/*/*/*")
    public Result dMock() {
        logger.debug("/*/*/*/* mocked");
        return ResultGenerator.genSuccessResult("mock success");
    }

    @RequestMapping("/*/*/*/*/*")
    public Result eMock() {
        logger.debug("/*/*/*/*/* mocked");
        return ResultGenerator.genSuccessResult("mock success");
    }

}
