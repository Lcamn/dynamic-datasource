package com.l.dynamic.datasource.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.l.dynamic.datasource.entity.AccountReturn;
import com.l.dynamic.datasource.model.AlipayAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 支付宝账户表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2022-05-26
 */
@Mapper
public interface AlipayAccountMapper extends BaseMapper<AlipayAccount> {

    @DS("db1")
    List<AccountReturn> getAll();
}
