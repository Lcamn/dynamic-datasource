package com.l.dynamic.datasource.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.l.dynamic.datasource.model.Tiku;

import java.util.List;

public interface TiKuService extends IService<Tiku> {
    List<Tiku> find();

    Integer sync();

    Integer save();

    void test(String sql);

    Integer syncNet();


}
