package com.l.dynamic.datasource.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.l.dynamic.datasource.model.DoubanCalender;
import org.apache.ibatis.annotations.Mapper;

/**
 * 豆瓣日历(DoubanCalender)表数据库访问层
 *
 * @author EasyCode
 * @since 2022-11-11 16:30:08
 */
@Mapper
@DS("db1")
public interface DoubanCalenderMapper extends InsertBatchBaseMapper<DoubanCalender> {

}

