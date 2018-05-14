package com.wangzhi.test.mock.interceptor;

import com.wangzhi.test.mock.core.ProjectConstant;
import com.wangzhi.test.mock.core.ResultGenerator;
import com.wangzhi.test.mock.kit.HttpRequest;
import com.wangzhi.test.mock.kit.VerificationKit;
import com.wangzhi.test.mock.model.UserToken;
import com.wangzhi.test.mock.service.UserTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.wangzhi.test.mock.core.ResultCode.INVALID_TOKEN;

@Component
public class MyTokenInterceptor extends HandlerInterceptorAdapter {

    Logger logger = LoggerFactory.getLogger(MyTokenInterceptor.class);

    @Resource
    UserTokenService userTokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap == null || parameterMap.isEmpty()) {
            logger.debug("无token");
            VerificationKit.responseResult(response, ResultGenerator.genFailResult(INVALID_TOKEN,"无token"));
            return false;
        }
        if (!parameterMap.containsKey(ProjectConstant.KEY_TOKEN)) {
            logger.debug("无token");
            VerificationKit.responseResult(response, ResultGenerator.genFailResult(INVALID_TOKEN,"无token"));
            return false;
        }
        String token = parameterMap.get(ProjectConstant.KEY_TOKEN)[0];
        UserToken userToken = userTokenService.selectUserTokenByToken(token);
        if (userToken == null) {
            logger.debug("token无效");
            VerificationKit.responseResult(response, ResultGenerator.genFailResult(INVALID_TOKEN,"token无效"));
            return false;
        }
        return true;
    }

    public static String getTokenByUserId(int userId) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(Math.random());
        buffer.append(Math.random());
        buffer.append(Math.random());
        buffer.append(Math.random());
        buffer.append(Math.random());
        buffer.append("userId=");
        buffer.append(userId);
        return HttpRequest.Base64.encode(buffer.toString());
    }

    public static String getUserIdByToken(String token) {
        try {
            return new String(new BASE64Decoder().decodeBuffer(token)).split("=")[1];
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
