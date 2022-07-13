package com.l.dynamic.datasource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.l.dynamic.datasource.model.TiaoZhan;
import com.l.dynamic.datasource.model.Tiku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TiKuMapper extends BaseMapper<Tiku> {
    Integer test(@Param("sql") String sql);

    List<TiaoZhan> getAllTiao();
}
