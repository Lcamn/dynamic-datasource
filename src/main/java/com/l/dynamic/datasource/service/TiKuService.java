package com.l.dynamic.datasource.service;

import com.l.dynamic.datasource.model.Tiku;

import java.util.List;

public interface TiKuService {
    List<Tiku> find();

    Integer sync();

    Integer save();

    void test(String sql);
}
