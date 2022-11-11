package com.l.dynamic.datasource.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.l.dynamic.datasource.model.Tiku;

public interface TiKuService extends IService<Tiku> {


    Integer sync();


}
