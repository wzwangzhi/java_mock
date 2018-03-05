package com.wangzhi.test.mock.service.impl;

import com.wangzhi.test.mock.core.AbstractService;
import com.wangzhi.test.mock.core.ProjectConstant;
import com.wangzhi.test.mock.kit.HttpRequest;
import com.wangzhi.test.mock.model.Sign;
import com.wangzhi.test.mock.service.SignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.net.ssl.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
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
            resultList.add(sign.getUserName() + ":" + signItem(sign.getLoginToken()));
        }
        return resultList;
    }

    /**
     * @param loginToken
     * @return
     */
    private String signItem(String loginToken) {
        try {
            ignoreSsl();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpRequest httpRequest = HttpRequest.get(ProjectConstant.API_SIGN, null, true);
        //header
        httpRequest.header("Cookie", "loginToken=" + loginToken);
        //result
        try {
            String responseMessage = httpRequest.body();
            logger.debug(responseMessage);
            return responseMessage;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 忽略HTTPS请求的SSL证书，必须在openConnection之前调用
     *
     * @throws Exception
     */
    public static void ignoreSsl() throws Exception {
        HostnameVerifier hv = new HostnameVerifier() {
            public boolean verify(String urlHostName, SSLSession session) {
                System.out.println("Warning: URL Host: " + urlHostName + " vs. " + session.getPeerHost());
                return true;
            }
        };
        trustAllHttpsCertificates();
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
    }

    private static void trustAllHttpsCertificates() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[1];
        TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }

    static class miTM implements TrustManager, X509TrustManager {
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(X509Certificate[] certs, String authType)
                throws CertificateException {
            return;
        }

        public void checkClientTrusted(X509Certificate[] certs, String authType)
                throws CertificateException {
            return;
        }
    }
}
