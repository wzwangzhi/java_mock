package com.wangzhi.test.mock.web;

import com.wangzhi.test.mock.core.Result;
import com.wangzhi.test.mock.core.ResultGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by CodeGenerator on 2018/01/22.
 */
@RestController
public class MockOpenController {

    @RequestMapping("/*")
    public Result aMock() {
        return ResultGenerator.genSuccessResult("mock success");
    }

    @RequestMapping("/*/*")
    public Result bMock(@RequestParam(defaultValue = "wangzhi") String channel) {
        return ResultGenerator.genSuccessResult("mock success");
    }

    @RequestMapping("/*/*/*")
    public Result cMock() {
        return ResultGenerator.genSuccessResult("mock success");
    }

    @RequestMapping("/*/*/*/*")
    public Result dMock() {
        return ResultGenerator.genSuccessResult("mock success");
    }

    @RequestMapping("/*/*/*/*/*")
    public Result eMock() {
        return ResultGenerator.genSuccessResult("mock success");
    }

}
