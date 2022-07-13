package com.l.dynamic.datasource;

import com.alibaba.fastjson.JSONObject;
import com.l.dynamic.datasource.entity.Tiao;
import com.l.dynamic.datasource.model.TiaoZhan;
import com.l.dynamic.datasource.service.TiaoZhanService;
import com.l.dynamic.datasource.utils.FileUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
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
            TiaoZhan tiaoZhan = TiaoZhan.of()
                    .setQuestion(tmp.getQuestion())
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
    public void tts() {
        LocalDateTime lastMonth = LocalDateTime.now().minusMonths(1);
        LocalDateTime startTime = LocalDateTime.of(LocalDate.of(lastMonth.getYear(), lastMonth.getMonth(), 1), LocalTime.of(23, 59, 59));
        LocalDateTime endTime = LocalDateTime.of(LocalDate.of(lastMonth.getYear(), lastMonth.getMonth(), 1).with(TemporalAdjusters.lastDayOfMonth()), LocalTime.of(23, 59, 59));

        System.out.println(lastMonth + "  " + startTime + "   " + endTime);

    }

    @Test
    void testIf() {
        int one = 0, three = 0, six = 0, twelve = 0, more = 0;
        ArrayList<Integer> leaveList = new ArrayList<>();
        leaveList.add(0);
        leaveList.add(1);
        leaveList.add(3);
        leaveList.add(4);
        leaveList.add(6);
        leaveList.add(7);
        leaveList.add(8);
        leaveList.add(9);
        leaveList.add(13);
        for (Integer user : leaveList) {
            int month = user;
            if (month <= 1) {
                one++;
            } else if (month <= 3) {
                three++;
            } else if (month <= 6) {
                six++;
            } else if (month <= 12) {
                twelve++;
            } else {
                more++;
            }
        }

        System.out.println(one + " " + three + "  " + six + " " + twelve + " " + more);
    }

}
