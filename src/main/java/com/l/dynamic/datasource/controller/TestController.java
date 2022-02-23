package com.l.dynamic.datasource.controller;

import com.l.dynamic.datasource.entity.Res;
import com.l.dynamic.datasource.model.Tiku;
import com.l.dynamic.datasource.service.TestService;
import com.l.dynamic.datasource.utils.HttpOk;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@Api(tags = "测试")
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/t")
    public Res test(@RequestParam("ss") String ss) {
        //String s = HttpUtil.doGet(ss);
        log.info("ss--->{}", ss);
        String s = HttpOk.doGet(ss);

        // JSONArray res = JSON.parseArray(s);
        // assert res != null;
        // for (Object re : res) {
        //     List<String> child = (List<String>) re;
        //     setToTiku(child);
        //
        //
        // }
        //用json的方法toJavaList，参数放入想转的集合对象就可以了
        //List<Tiku> monthTaskRes = res.toJavaList(Tiku.class);

        return Res.ok(s);
    }

    private void setToTiku(List<String> child) {
        Tiku tiku = new Tiku();
        tiku.setQuestion(child.get(0));
        tiku.setAnswer(child.get(1));
        tiku.setWronganswer(child.get(2));
        tiku.setOption(child.get(3));
        tiku.setDatetime(child.get(4));

        log.info("实体类Tiku。{}",tiku);
    }
}
