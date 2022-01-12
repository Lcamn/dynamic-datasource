package com.l.dynamic.datasource.utils;

import com.alibaba.fastjson.JSON;
import com.l.dynamic.datasource.config.InvalidArgumentException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
public final class HttpOk {

    private static final OkHttpClient client = new OkHttpClient();

    private static OkHttpClient builder() {
        return client.newBuilder()
                .connectTimeout(10000, TimeUnit.SECONDS)
                .readTimeout(10000, TimeUnit.SECONDS)
                .writeTimeout(10000, TimeUnit.SECONDS)
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                .addInterceptor(new HttpLogInterceptor())
                .build();
    }

    public static String doGet(String url) {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        final Call call = builder().newCall(request);
        Response response = null;
        try {
            // 执行请求
            response = call.execute();
        } catch (Exception e) {
            log.error("get请求错误。url: {}", url, e);
        }

        try {
            assert response != null;
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            log.error("请求接口发生错误", e);
        }

        return null;
    }


    public static String doPost(String url, Object param) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSON.toJSONString(param));
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody) //post请求
                .build();
        final Call call = builder().newCall(request);
        Response response = null;
        try {
            // 执行请求
            response = call.execute();
        } catch (Exception e) {
            log.error("doPost请求错误。url: {}", url, e);
        }

        try {
            assert response != null;
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            log.error("请求接口发生错误", e);
        }

        return null;
    }

    public static String call(String url, String salt, String data) {

        try {


            MediaType JSONMediaType = MediaType.parse("application/json; charset=utf-8");

            RequestBody formBody = RequestBody.create(JSONMediaType, data);
            Headers setHeaders = generateHeader(salt);

            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .headers(setHeaders)
                    .build();

            Response response = builder().newCall(request).execute();

            String s = response.body().string();

            return s;

        } catch (IOException e) {
            log.error("url: {}, data: {}", url, data, e);
            throw new InvalidArgumentException("请求用户信息接口发生错误");
        }
    }


    public static String call(String url, String salt) {
        log.error("doGet。url: {}", url);

        Headers setHeaders = generateHeader(salt);

        Request request = new Request.Builder()
                .url(url)
                .headers(setHeaders)
                .get()
                .build();
        final Call call = builder().newCall(request);

        try {
            // 执行请求
            Response response = call.execute();

            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            log.error("url: {}", url, e);
            throw new InvalidArgumentException("请求用户信息接口发生错误");
        }
    }

    private static Headers generateHeader(String salt) {
        Map<String, String> headMap = new HashMap<>();

        long currentTimestamp = System.currentTimeMillis();

        String token = SignUtil.md5(currentTimestamp + salt);
        headMap.put("S-Request-Token", token);
        headMap.put("S-Request-Time", String.valueOf(currentTimestamp));
        return SetHeaders(headMap);
    }



    private static Headers SetHeaders(Map<String, String> headMap) {
        Headers headers = null;
        Headers.Builder headersbuilder = new Headers.Builder();

        headMap.forEach(headersbuilder::add);

        headers = headersbuilder.build();

        return headers;
    }

}

