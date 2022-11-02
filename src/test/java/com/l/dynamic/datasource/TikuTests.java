package com.l.dynamic.datasource;

import com.alibaba.fastjson.JSONObject;
import com.hankcs.hanlp.restful.HanLPClient;
import com.l.dynamic.datasource.entity.Tiao;
import com.l.dynamic.datasource.model.TiaoZhan;
import com.l.dynamic.datasource.service.TiaoZhanService;
import com.l.dynamic.datasource.utils.FileUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SpringBootTest
class TikuTests {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private TiaoZhanService tiaoZhanService;

    @Test
    void Paixu() {
        String s = FileUtil.readFileByChars("paixu.json");
        JSONObject o = (JSONObject) JSONObject.parse(s);

        List<Tiao> list = new ArrayList<>();
        List<TiaoZhan> tiaoZhanList = new ArrayList<>();
        Iterator<String> keys = o.keySet().iterator();// jsonObject.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            String question = key.replaceAll("\\s*", "");
            question = question.replaceAll(" ", "");
            String[] option = question.substring(question.indexOf("|")).substring(1).split("\\|");

            Tiao tiao = Tiao.of().setQuestion(question.substring(0, question.indexOf("|"))).setOption(option).setAnswer((String) o.get(key));
            list.add(tiao);
        }
        for (Tiao tmp : list) {
            String[] option = tmp.getOption();
            StringBuilder str = new StringBuilder();
            for (String s1 : option) {
                str.append(s1);
            }
            TiaoZhan tiaoZhan = new TiaoZhan();
            tiaoZhan.setQuestion(tmp.getQuestion())
                    .setAnswer(tmp.getAnswer())
                    .setOption(str.toString());
            tiaoZhanList.add(tiaoZhan);
        }


        tiaoZhanService.saveBatch(tiaoZhanList);
        List<TiaoZhan> tiaoList = tiaoZhanService.getAllTiao();
        System.out.println(tiaoList.size());
        System.out.println(list.size());
    }


    @Test
    void xin() throws IOException {
        HanLPClient client = new HanLPClient("https://hanlp.hankcs.com/api", null); // Replace null with your auth
        System.out.println(client.parse("2021年HanLPv2.1为生产环境带来次世代最先进的多语种NLP技术。晓美焰来到北京立方庭参观自然语义科技公司。"));
    }
}
