package com.l.dynamic.datasource.mapper;

import com.l.dynamic.datasource.model.TiaoZhan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TiaoZhanMapper extends InsertBatchBaseMapper<TiaoZhan> {
    List<TiaoZhan> getAllTiao();
}
