package com.l.dynamic.datasource.Tools;

import com.alibaba.fastjson.JSONArray;
import com.l.dynamic.datasource.utils.FileUtil;

public class ReadJson {
    // 挑战题库读取
    public static void main(String[] args) {

        read();


    }

    private static void read() {
        String s = FileUtil.readFileByChars("a.json");
        JSONArray array = JSONArray.parseArray(s);
        System.out.println(array.size());
    }
}
