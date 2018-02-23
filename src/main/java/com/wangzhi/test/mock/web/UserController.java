package com.wangzhi.test.mock.web;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangzhi.test.mock.core.Result;
import com.wangzhi.test.mock.core.ResultGenerator;
import com.wangzhi.test.mock.model.User;
import com.wangzhi.test.mock.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/01/22.
*/
@RestController
@RequestMapping("/wangzhi/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;


//    @PostMapping("/add")
    public Result add(User user) {
        userService.save(user);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

//    @PostMapping("/update")
    public Result update(User user) {
        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        User user = userService.findById(id);
        return ResultGenerator.genSuccessResult(user);
    }

//    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        logger.debug("get list page {} ,size {}",page,size);
        PageHelper.startPage(page, size);
        List<User> list = userService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/login")
    public Result login(@RequestParam String name, @RequestParam String pass) {
        User user = userService.login(name, pass);
        if (user == null) {
            return ResultGenerator.genFailResult("用户名或密码错误");
        } else {
            return ResultGenerator.genSuccessResult(user);
        }
    }

//    @PostMapping("/register")
    public Result register(@RequestParam String name, @RequestParam String pass) {
        User user = userService.register(name, pass);
        if (user == null) {
            return ResultGenerator.genFailResult("用户名或密码错误");
        } else {
            return ResultGenerator.genSuccessResult(user);
        }
    }
}
