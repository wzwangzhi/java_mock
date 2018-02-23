package com.wangzhi.test.mock.api.impl;

import com.wangzhi.test.mock.api.BaiduApi;
import com.wangzhi.test.mock.kit.HttpRequest;
import org.springframework.stereotype.Component;

@Component
public class BaiduApiImpl implements BaiduApi {

   public String getContent(){
       return HttpRequest.get("http://www.baidu.com").body();
   }

}
