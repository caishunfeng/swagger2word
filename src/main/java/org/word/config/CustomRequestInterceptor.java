package org.word.config;

import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomRequestInterceptor implements HttpRequestInterceptor {
    @Override
    public void process(HttpRequest request, HttpContext context) {
        log.info("CustomRequestInterceptor");
        request.setHeader("Accept-Language", "zh-CN,zh");
        request.setHeader("Cookie", "language=zh_CN");
    }
}
