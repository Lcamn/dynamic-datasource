package com.l.dynamic.datasource.controller;

import com.l.dynamic.datasource.entity.Res;
import com.l.dynamic.datasource.model.Tiku;
import com.l.dynamic.datasource.service.TiKuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/save")
    public Res<Integer> save() {
        return Res.ok(tiKuService.save());
    }

    @PostMapping("/test")
    public void test(@RequestParam("sql") String sql) {
        tiKuService.test(sql);
    }
}
