package com.wangzhi.test.mock.advice;

import com.alibaba.fastjson.JSON;
import com.wangzhi.test.mock.core.ProjectConstant;
import com.wangzhi.test.mock.core.Result;
import com.wangzhi.test.mock.core.ResultGenerator;
import com.wangzhi.test.mock.kit.HttpRequest;
import com.wangzhi.test.mock.model.Mock;
import com.wangzhi.test.mock.service.MockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class MyResponseBodyAdvice implements ResponseBodyAdvice {
    Logger logger = LoggerFactory.getLogger(MyResponseBodyAdvice.class);

    @Resource
    MockService mockService;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        String path = request.getURI().getPath();
        if (path.startsWith("/wangzhi")) {//自己的接口不拦截
            logger.debug("path:" + path + " 自己的接口不拦截");
            if (body instanceof Result) {
                return body;
            }
            return ResultGenerator.genSuccessResult(body);
        }
        Mock mock = mockService.selectMockByPath(path, getUserIdByRequest(request));
        if (mock != null && mock.is_open()) {
            logger.debug("path:" + path + " 需要拦截");
            return JSON.parse(mock.getResponse());
        }
        logger.debug("path:" + path + " 不需要拦截");
        return getRealResponse(request, mock);
    }

    private String getUserIdByRequest(ServerHttpRequest request) {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest) request;
            Map<String, String[]> requestParameterMap = servletServerHttpRequest.getServletRequest().getParameterMap();
            if (requestParameterMap != null && requestParameterMap.containsKey("mockUserId")) {
                return requestParameterMap.get("mockUserId")[0];
            }
        }
        return "-1";
    }

    /**
     * @param request
     * @param mock
     * @return
     */
    private Object getRealResponse(ServerHttpRequest request, Mock mock) {
        String host = ProjectConstant.DEFAULT_ROOT;
        if (mock != null && mock.getRemote_root()!=null && !"".equals(mock.getRemote_root())){
            host = mock.getRemote_root();
        }
        String scheme = request.getURI().getScheme();
        String path = request.getURI().getPath();
        //params
        Map<String, String> parameterMap = new HashMap();
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest) request;
            Map<String, String[]> requestParameterMap = servletServerHttpRequest.getServletRequest().getParameterMap();
            for (Map.Entry<String, String[]> entry : requestParameterMap.entrySet()) {
                logger.debug(entry.getKey() + ":" + entry.getValue()[0]);
                parameterMap.put(entry.getKey(), entry.getValue()[0]);
            }
        }
        //request
        HttpRequest httpRequest;
        if (HttpMethod.GET.equals(request.getMethod())) {
            httpRequest = HttpRequest.get(scheme + "://" + host + path, parameterMap, true);
//            return JSON.parse(httpRequest.body());
        } else {
            httpRequest = HttpRequest.post(scheme + "://" + host + path, parameterMap, true);
//            return JSON.parse(httpRequest.body());
        }
        //header
        for (Map.Entry<String, List<String>> item : request.getHeaders().entrySet()) {
            httpRequest.header(item.getKey(), item.getValue().get(0));
        }
        //result
        try {
            if (HttpMethod.GET.equals(request.getMethod())) {
                String responseMessage = httpRequest.body();
                return JSON.parse(responseMessage);
            } else {
                String responseMessage = httpRequest.body();
                return JSON.parse(responseMessage);
            }
        } catch (Exception e) {
            return ResultGenerator.genFailResult("返回数据非json格式");
        }
    }
}
