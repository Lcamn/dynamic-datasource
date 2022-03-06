package com.l.dynamic.datasource.controller;

import com.alibaba.fastjson.JSONArray;
import com.l.dynamic.datasource.entity.Res;
import com.l.dynamic.datasource.model.Tiku;
import com.l.dynamic.datasource.service.TiKuService;
import com.l.dynamic.datasource.utils.HttpOk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tiku")
public class TiKuController {
    @Autowired
    private TiKuService tiKuService;

    @PostMapping("/find")
    public Res<List<Tiku>> find() {
        List<Tiku> list = tiKuService.find();
        return Res.ok(list);
    }

    @PostMapping("/sync")
    public Res<Integer> sync() {
        Integer i = tiKuService.sync();
        return Res.ok(i);
    }

    @PostMapping("/count")
    public Res<Integer> count() {
        Integer i = tiKuService.count(null);
        return Res.ok(i);
    }

    @PostMapping("/syncNet")
    public Res<Integer> syncNet() {
        Integer i = tiKuService.syncNet();
        System.out.println("---------------------------");
        return Res.ok(i);
    }

    @PostMapping("/save")
    public Res<Integer> save() {
        return Res.ok(tiKuService.save());
    }

    @PostMapping("/test")
    public void test(@RequestParam("sql") String sql) {
        tiKuService.test(sql);
    }

    @GetMapping("/net")
    public String getNetQuestions() {
        String s = HttpOk.doGet("https://tiku.3141314.xyz/getAnswer");
        JSONArray jsonArray = JSONArray.parseArray(s);
        System.out.println(jsonArray.size());
        return s;
    }
}
