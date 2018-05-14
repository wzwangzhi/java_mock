package com.wangzhi.test.mock.web;

import com.wangzhi.test.mock.core.Result;
import com.wangzhi.test.mock.core.ResultGenerator;
import com.wangzhi.test.mock.interceptor.MyTokenInterceptor;
import com.wangzhi.test.mock.model.User;
import com.wangzhi.test.mock.model.UserToken;
import com.wangzhi.test.mock.service.UserService;
import com.wangzhi.test.mock.service.UserTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by CodeGenerator on 2018/01/22.
 */
@RestController
@RequestMapping("/wangzhi/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;
    @Resource
    private UserTokenService userTokenService;

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        User user = userService.findById(id);
        return ResultGenerator.genSuccessResult(user);
    }

    @PostMapping("/login")
    public Result login(@RequestParam String userName, @RequestParam String userPass) {
        User user = userService.login(userName, userPass);
        if (user == null) {
            return ResultGenerator.genFailResult("密码错误");
        }
        UserToken userToken = userTokenService.findBy("userId", user.getUserId());
        if (userToken == null) {
            userToken = new UserToken();
            userToken.setUserId(user.getUserId());
            userToken.setToken(MyTokenInterceptor.getTokenByUserId(user.getUserId()));
            userTokenService.save(userToken);
        }
        Result result = ResultGenerator.genSuccessResult(userToken.getToken());
        result.setMessage("登录成功");
        return result;
    }

    //    @PostMapping("/register")
    public Result register(@RequestParam String userName, @RequestParam String userPass, @RequestParam(defaultValue = "") String nickName) {
        User user = userService.register(userName, userPass, nickName);
        Result result = ResultGenerator.genSuccessResult(user);
        result.setMessage("注册成功");
        return result;
    }
}
