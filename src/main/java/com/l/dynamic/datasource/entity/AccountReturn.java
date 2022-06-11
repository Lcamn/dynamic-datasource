package com.l.dynamic.datasource.entity;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountReturn {
    private String accountNo;
    private String alipayPublicKey;
    private String privateKey;
    private String companyName;
    private String gatewayUrl;
    private String appId;
    private String format;
    private String charset;
    private String signType;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
