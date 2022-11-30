package com.l.dynamic.datasource.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Collection;

public interface InsertBatchBaseMapper<T> extends BaseMapper<T> {

    Integer insertBatchSomeColumn(Collection<T> entityList);
}