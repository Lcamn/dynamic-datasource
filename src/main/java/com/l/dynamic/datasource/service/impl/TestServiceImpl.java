package com.l.dynamic.datasource.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.l.dynamic.datasource.mapper.TestMapper;
import com.l.dynamic.datasource.mapper.TestMapperDb2;
import com.l.dynamic.datasource.model.Test;
import com.l.dynamic.datasource.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;
    @Autowired
    private TestMapperDb2 testMapperDb2;

    @Override
    public List<Test> findAll() {
        return testMapper.selectList(null);
    }

    @DS("db1")
    @Override
    public List<Test> findAllDb2() {

        return testMapperDb2.selectList(null);
    }
}
