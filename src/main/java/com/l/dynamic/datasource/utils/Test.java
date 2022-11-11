package com.l.dynamic.datasource.utils;

import com.l.dynamic.datasource.entity.TcentParam;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
        TcentParam tcentParam = new TcentParam();
        String token = "576b4e6bae0e651acf14bcff29de08ae";
        String url = "https://api.e.qq.com/v1.1/fund_statements_detailed/get";
        Date date = new Date();
        String timestamp = date.getTime() + "";
        TcentParam param = new TcentParam();
        param.setTimestamp(timestamp)
                .setNonce(timestamp)
                .setAccess_token(token)
                .setDate_range("{\"start_date\":\"2022-10-29\",\"end_date\":\"2022-11-08\"}")
                .setFund_type("FUND_TYPE_CASH");
        HttpOk.doGet(url);
    }
}
