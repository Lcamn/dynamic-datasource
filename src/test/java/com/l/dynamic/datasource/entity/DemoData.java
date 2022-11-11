package com.l.dynamic.datasource.entity;

import com.alibaba.fastjson.JSON;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
public class DemoData {
    private String string;
    private Date date;
    private Double doubleData;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
