package com.l.dynamic.datasource.service.impl;

import com.l.dynamic.datasource.config.AlipayCommon;
import com.l.dynamic.datasource.service.AlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlipayServiceImpl implements AlipayService {
    @Autowired
    private AlipayCommon alipayCommon;

    @Override
    public String test() {
        System.out.println("----->");
        return alipayCommon.getAppId();
    }
}
