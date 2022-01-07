package com.l.dynamic.datasource.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.Buffer;
import okio.BufferedSource;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;

/**
 * okhttp 拦截器
 */
@Slf4j
public class HttpLogInterceptor implements Interceptor {
    // private static final String TAG = HttpLogInterceptor.class.getSimpleName();
    private static final Charset UTF8 = StandardCharsets.UTF_8;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        RequestBody requestBody = request.body();
        String body = null;
        if (requestBody != null) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            Charset charset = UTF8;
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            body = buffer.readString(charset);
        }
        long startNs = System.nanoTime();

        log.info("发送请求: \r\nRequestId: {}\r\nmethod：{}\r\nurl：{}\r\n请求头：{}\r\n请求body: {}", startNs, request.method(), request.url(), request.headers(), body);

        Response response = chain.proceed(request);
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

        ResponseBody responseBody = response.body();
        String rBody;

        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE);
        Buffer buffer = source.buffer();

        Charset charset = UTF8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            try {
                charset = contentType.charset(UTF8);
            } catch (UnsupportedCharsetException e) {
                e.printStackTrace();
            }
        }

        rBody = buffer.clone().readString(charset);

        log.info("收到响应: \r\nRequestId: {}\r\n请求url：{}\r\n请求body：{}\r\nResponseCode:{}\r\n耗时: {}ms\r\nResponse: {}", startNs, response.request().url(), body, response.code(), tookMs, rBody);

        return response;
    }
}

