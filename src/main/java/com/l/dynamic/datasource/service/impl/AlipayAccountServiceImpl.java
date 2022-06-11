package com.l.dynamic.datasource.service.impl;

import com.l.dynamic.datasource.entity.AccountReturn;
import com.l.dynamic.datasource.model.AlipayAccount;
import com.l.dynamic.datasource.mapper.AlipayAccountMapper;
import com.l.dynamic.datasource.service.AlipayAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 支付宝账户表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2022-05-26
 */
@Service
public class AlipayAccountServiceImpl extends ServiceImpl<AlipayAccountMapper, AlipayAccount> implements AlipayAccountService {

    @Autowired
    private AlipayAccountMapper alipayAccountMapper;
    @Override
    public List<AccountReturn> getAll() {

        return alipayAccountMapper.getAll();
    }
}
