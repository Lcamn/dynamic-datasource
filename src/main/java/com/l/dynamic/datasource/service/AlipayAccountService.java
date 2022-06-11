package com.l.dynamic.datasource.service;

import com.l.dynamic.datasource.entity.AccountReturn;
import com.l.dynamic.datasource.model.AlipayAccount;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 支付宝账户表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2022-05-26
 */
public interface AlipayAccountService extends IService<AlipayAccount> {

    List<AccountReturn> getAll();
}
