package com.wangzhi.test.mock.service.impl;

import com.wangzhi.test.mock.core.AbstractService;
import com.wangzhi.test.mock.core.ProjectConstant;
import com.wangzhi.test.mock.kit.HttpRequest;
import com.wangzhi.test.mock.kit.IgnoreSslKit;
import com.wangzhi.test.mock.model.Sign;
import com.wangzhi.test.mock.service.SignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by CodeGenerator on 2018/01/22.
 */
@Service
@Transactional
public class SignServiceImpl extends AbstractService<Sign> implements SignService {
    Logger logger = LoggerFactory.getLogger(SignServiceImpl.class);

    @Override
    public List<String> signAll() {
        List<Sign> all = findAll();
        List<String> resultList = new ArrayList<>();
        for (Sign sign : all) {
            String itemResult = sign.getUserName() + ":" + signItem(sign.getLoginToken());
            logger.debug(itemResult);
            resultList.add(itemResult);
        }
        return resultList;
    }

    /**
     * @param loginToken
     * @return
     */
    private String signItem(String loginToken) {
        IgnoreSslKit.ignoreSsl();
        HttpRequest httpRequest = HttpRequest.get(ProjectConstant.API_SIGN, null, true);
        //header
        httpRequest.header("Cookie", "loginToken=" + loginToken);
        //result
        try {
            String responseMessage = httpRequest.body();
            return responseMessage;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


}
