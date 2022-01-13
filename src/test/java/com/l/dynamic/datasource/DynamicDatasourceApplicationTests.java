package com.l.dynamic.datasource;

import com.alibaba.fastjson.JSONArray;
import com.l.dynamic.datasource.utils.HttpOk;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootTest
class DynamicDatasourceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testTiku() {
        String s = HttpOk.doGet("https://tiku.786345.xyz/getAnswerByQuestion");
        JSONArray jsonArray = JSONArray.parseArray(s);
        System.out.println(jsonArray.size());
    }

    @Test
    void ti() {
        LocalDateTime time = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0));
        System.out.println(time);
    }

}
