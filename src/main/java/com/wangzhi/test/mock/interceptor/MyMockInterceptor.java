package com.wangzhi.test.mock.interceptor;

import com.wangzhi.test.mock.core.ProjectConstant;
import com.wangzhi.test.mock.core.ResultGenerator;
import com.wangzhi.test.mock.kit.FileManager;
import com.wangzhi.test.mock.kit.HttpRequest;
import com.wangzhi.test.mock.kit.IgnoreSslKit;
import com.wangzhi.test.mock.kit.VerificationKit;
import com.wangzhi.test.mock.model.MockItem;
import com.wangzhi.test.mock.model.MockProject;
import com.wangzhi.test.mock.model.MockResponse;
import com.wangzhi.test.mock.service.MockItemService;
import com.wangzhi.test.mock.service.MockProjectService;
import com.wangzhi.test.mock.service.MockResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
public class MyMockInterceptor extends HandlerInterceptorAdapter {

    Logger logger = LoggerFactory.getLogger(MyMockInterceptor.class);

    @Resource
    MockItemService mockItemService;
    @Resource
    MockResponseService mockResponseService;
    @Resource
    MockProjectService mockProjectService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestUrl = request.getRequestURI();
        logger.debug("requestUrl:" + requestUrl);
        String projectId = "1";
        //检查有无projectId
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (!parameterMap.containsKey(ProjectConstant.KEY_PROJECTID)) {
            logger.debug("无projectId，将使用默认Project");
//            logger.debug("无projectId，无法定位。。。");
//            VerificationKit.responseResult(response, ResultGenerator.genFailResult("无projectId，无法定位。。。"));
//            return false;
        } else {
            projectId = parameterMap.get(ProjectConstant.KEY_PROJECTID)[0].toString();
            logger.debug("projectId：" + projectId);
        }
        if (!VerificationKit.isNumber(projectId)) {
            logger.debug("projectId格式有误。。。");
            VerificationKit.responseResult(response, ResultGenerator.genFailResult("projectId格式有误。。。"));
            return false;
        }
        //获取mock信息
        String mockUrl = requestUrl;
        MockItem mockItem = mockItemService.selectMockByUrl(mockUrl, projectId);

        //如果没有查到mock信息，或者mock没有打开，或者没有设置返回值，请求原值返回
        if (mockItem == null) {
            logger.debug(requestUrl + ":不需要拦截，获取真实的返回值返回");
            setRealResponse(request, response, projectId, mockUrl);
            return false;
        }
        if (!mockItem.isOpen()) {
            logger.debug(requestUrl + ":没有打开，获取真实的返回值返回");
            setRealResponse(request, response, projectId, mockUrl);
            return false;
        }
        if (!mockItem.hasResponse()) {
            logger.debug(requestUrl + ":没有设置返回值，获取真实的返回值返回");
            setRealResponse(request, response, projectId, mockUrl);
            return false;
        }
        MockResponse mockResponse = mockResponseService.selectMockResponseByMock(mockItem.getMockId(), mockItem.getMockResponseId());
        String mockResponseValue = FileManager.getInstance().readResponse(mockResponse.getResponsePath());
        //如果没有查到response
        if (mockResponseValue == null) {
            logger.debug(requestUrl + ":没有找到返回值，获取真实的返回值返回");
            setRealResponse(request, response, projectId, mockUrl);
            return false;
        }
        logger.debug(requestUrl + ":Mock成功");
        VerificationKit.responseResult(response, mockResponseValue);
        return false;
    }

    /**
     * 调用真正的接口并返回
     *
     * @param request
     * @param response
     * @param projectId
     * @param mockUrl
     */
    private void setRealResponse(HttpServletRequest request, HttpServletResponse response, String projectId, String mockUrl) {
        MockProject mockProject = mockProjectService.selectMockProjectById(Integer.parseInt(projectId));
        if (mockProject == null) {
            logger.debug("没有找到该project：projectId=" + projectId);
            VerificationKit.responseResult(response, ResultGenerator.genFailResult("没有找到该project：projectId=" + projectId));
            return;
        }
        String baseUrl = mockProject.getProjectBaseUrl();
//        String scheme = mockProject.getProjectScheme();
//        String scheme = "https";
        if (baseUrl.startsWith("https")) {
            //忽略证书
            IgnoreSslKit.ignoreSsl();
        }
        String path = baseUrl + mockUrl;
        logger.debug("真实路径：" + path);
        //params
        Map<String, String> parameterMap = new HashMap();
        Map<String, String[]> requestParameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : requestParameterMap.entrySet()) {
            parameterMap.put(entry.getKey(), entry.getValue()[0]);
        }
        //request
        HttpRequest httpRequest;
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            httpRequest = HttpRequest.get(path, parameterMap, true);
//            return JSON.parse(httpRequest.body());
        } else {
            httpRequest = HttpRequest.post(path, parameterMap, true);
//            return JSON.parse(httpRequest.body());
        }
        //header
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            httpRequest.header(request.getHeader(headerNames.nextElement()));
        }
        //result
        String responseMessage;
        if (HttpMethod.GET.equals(request.getMethod())) {
            responseMessage = httpRequest.body();
        } else {
            responseMessage = httpRequest.body();
        }
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(responseMessage);
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }
}
