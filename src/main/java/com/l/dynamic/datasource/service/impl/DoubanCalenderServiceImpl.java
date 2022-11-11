package com.l.dynamic.datasource.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.l.dynamic.datasource.mapper.DoubanCalenderMapper;
import com.l.dynamic.datasource.model.DoubanCalender;
import com.l.dynamic.datasource.service.DoubanCalenderService;
import org.springframework.stereotype.Service;

/**
 * 豆瓣日历(DoubanCalender)表服务实现类
 *
 * @author EasyCode
 * @since 2022-11-11 16:30:08
 */
@Service("doubanCalenderService")
@DS("db1")
public class DoubanCalenderServiceImpl extends ServiceImpl<DoubanCalenderMapper, DoubanCalender> implements DoubanCalenderService {

}
