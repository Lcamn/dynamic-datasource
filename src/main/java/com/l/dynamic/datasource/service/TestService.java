package com.l.dynamic.datasource.service;

import com.l.dynamic.datasource.model.Test;

import java.util.List;

public interface TestService {
    List<Test> findAll();

    List<Test> findAllDb2();
}
