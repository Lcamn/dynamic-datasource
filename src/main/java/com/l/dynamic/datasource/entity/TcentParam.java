package com.l.dynamic.datasource.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class TcentParam {
    private String access_token;
    private String timestamp;
    private String nonce;
    private String page;
    private String page_size;
    private String account_id;
    private String date_range;
    private String fund_type;

}
