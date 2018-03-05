package com.wangzhi.test.mock.web;

import com.github.pagehelper.PageHelper;
import com.wangzhi.test.mock.core.Result;
import com.wangzhi.test.mock.core.ResultGenerator;
import com.wangzhi.test.mock.model.Sign;
import com.wangzhi.test.mock.service.SignService;
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
@RequestMapping("/wangzhi/sign")
public class SignController {

    Logger logger = LoggerFactory.getLogger(SignController.class);

    @Resource
    private SignService signService;


    @RequestMapping("/add")
    public Result add(@RequestParam String userName, @RequestParam String loginToken) {
        Sign sign = new Sign();
        sign.setLoginToken(loginToken);
        sign.setUserName(userName);
        signService.save(sign);
        return ResultGenerator.genSuccessResult("添加成功");
    }

    @RequestMapping("/delete")
    public Result delete(@RequestParam(defaultValue = "-1") int id) {
        if (id < 0) {
            return ResultGenerator.genFailResult("id 不能为空");
        }
        signService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping("/update")
    public Result update(@RequestParam(defaultValue = "-1") int id, @RequestParam String userName, @RequestParam String loginToken) {
        if (id < 0) {
            return ResultGenerator.genFailResult("id 不能为空");
        }
        Sign sign = new Sign();
        sign.setId(id);
        sign.setLoginToken(loginToken);
        sign.setUserName(userName);
        signService.update(sign);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        logger.debug("get list page {} ,size {}", page, size);
        PageHelper.startPage(page, size);
        List<Sign> list = signService.findAll();
        return ResultGenerator.genSuccessResult(list);
    }

    @RequestMapping("signAll")
    public Result signAll() {
        return ResultGenerator.genSuccessResult(signService.signAll());
    }
}
