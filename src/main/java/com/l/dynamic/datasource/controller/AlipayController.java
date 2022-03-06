package com.l.dynamic.datasource.controller;

import com.l.dynamic.datasource.entity.Res;
import com.l.dynamic.datasource.service.AlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alipay")
public class AlipayController {
    @Autowired
    private AlipayService alipayService;

    @PostMapping("/test")
    public Res count() {
        String s = alipayService.test();
        return Res.ok(s);
    }
}
