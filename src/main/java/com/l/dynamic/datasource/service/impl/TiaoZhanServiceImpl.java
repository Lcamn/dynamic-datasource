package com.l.dynamic.datasource.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.l.dynamic.datasource.mapper.TiaoZhanMapper;
import com.l.dynamic.datasource.model.TiaoZhan;
import com.l.dynamic.datasource.service.TiaoZhanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TiaoZhanServiceImpl extends ServiceImpl<TiaoZhanMapper, TiaoZhan> implements TiaoZhanService {
    @Autowired
    private TiaoZhanMapper tiaoZhanMapper;

    @Override
    public List<TiaoZhan> getAllTiao() {

        return tiaoZhanMapper.getAllTiao();
    }

    @Override
    public int sync(ArrayList<TiaoZhan> list) {
        int delete = tiaoZhanMapper.delete(null);
        System.out.println("已删除： " + delete);
        this.saveBatch(list);
        return list.size();
    }
}
