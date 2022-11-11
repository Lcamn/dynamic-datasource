package com.l.dynamic.datasource.controller;

import com.l.dynamic.datasource.entity.Res;
import com.l.dynamic.datasource.service.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * (Tickets)表控制层
 *
 * @author EasyCode
 * @since 2022-11-03 15:17:49
 */
@RestController
@RequestMapping("tickets")
public class TicketsController {

    @Autowired
    private TicketsService ticketsService;

    @PostMapping("/add")
    public Res<Integer> find() throws Exception {
        ticketsService.add();
        return Res.ok(0);
    }

}

