package com.l.dynamic.datasource.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.l.dynamic.datasource.model.TiaoZhan;

import java.util.List;

public interface TiaoZhanService extends IService<TiaoZhan> {

    List<TiaoZhan> getAllTiao();

}
