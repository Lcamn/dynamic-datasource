package com.l.dynamic.datasource.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.l.dynamic.datasource.entity.Res;
import com.l.dynamic.datasource.model.Tiku;
import com.l.dynamic.datasource.service.TestService;
import com.l.dynamic.datasource.utils.HttpOk;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/t")
    public Res<List<Tiku>>  test(@RequestParam("ss") String ss) {
        //String s = HttpUtil.doGet(ss);
         String s = HttpOk.doGet(ss);

        JSONArray res = JSON.parseArray(s);
        List<Tiku> tikus = res.toJavaList(Tiku.class);
        System.out.println(tikus);

        log.info(res.toString());
        //用json的方法toJavaList，参数放入想转的集合对象就可以了
        //List<Tiku> monthTaskRes = res.toJavaList(Tiku.class);
        log.info(s);
        return Res.ok(null);
    }
}
