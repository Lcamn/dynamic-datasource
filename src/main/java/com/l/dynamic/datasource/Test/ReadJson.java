package com.l.dynamic.datasource.Test;

import com.alibaba.fastjson.JSONObject;
import com.l.dynamic.datasource.entity.Tiao;
import com.l.dynamic.datasource.utils.FileUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadJson {
    public static void main(String[] args) {
        String s = FileUtil.readFileByChars("paixu.json");
        JSONObject o = (JSONObject) JSONObject.parse(s);

        List<Tiao> list = new ArrayList<>();
        Iterator<String> keys = o.keySet().iterator();// jsonObject.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            String question = key.replaceAll("\\s*", "");
            String[] option = question.substring(question.indexOf("|")).substring(1).split("\\|");

            Tiao tiao = Tiao.of().setQuestion(question.substring(0, question.indexOf("|"))).setOption(option).setAnswer((String) o.get(key));
            list.add(tiao);
        }
        System.out.println(list);


    }
}
