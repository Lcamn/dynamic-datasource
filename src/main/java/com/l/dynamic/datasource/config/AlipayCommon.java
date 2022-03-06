package com.l.dynamic.datasource.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "alipay")
@Getter
@Setter
public class AlipayCommon {
    private String gatewayUrl;
    private String appId;
    private String privateKey;
    private String format;
    private String charset;
    private String alipayPublicKey;
    private String signType;
}
