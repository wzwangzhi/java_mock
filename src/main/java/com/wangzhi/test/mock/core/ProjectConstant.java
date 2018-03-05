package com.wangzhi.test.mock.core;

/**
 * 项目常量
 */
public final class ProjectConstant {
    public static final String BASE_PACKAGE = "com.wangzhi.test.mock";//项目基础包名称，根据自己公司的项目修改

    public static final String MODEL_PACKAGE = BASE_PACKAGE + ".model";//Model所在包
    public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".dao";//Mapper所在包
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service";//Service所在包
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";//ServiceImpl所在包
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".web";//Controller所在包

    public static final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".core.Mapper";//Mapper插件基础接口的完全限定名

    public static final String DEFAULT_ROOT = "www.wanandroid.com";//默认的远端域名

    public static final String API_SIGN = "https://api.100iec.com/member/checkin";//默认的远端域名
}
