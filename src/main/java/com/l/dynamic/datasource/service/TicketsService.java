package com.l.dynamic.datasource.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.l.dynamic.datasource.model.Tickets;

/**
 * (Tickets)表服务接口
 *
 * @author EasyCode
 * @since 2022-11-03 15:17:58
 */
public interface TicketsService extends IService<Tickets> {


    void add() throws Exception;

}
