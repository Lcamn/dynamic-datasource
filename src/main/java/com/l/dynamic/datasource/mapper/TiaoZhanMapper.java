package com.l.dynamic.datasource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.l.dynamic.datasource.model.TiaoZhan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TiaoZhanMapper extends BaseMapper<TiaoZhan> {
        List<TiaoZhan> getAllTiao();
}
