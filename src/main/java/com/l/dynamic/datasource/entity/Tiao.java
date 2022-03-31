package com.l.dynamic.datasource.entity;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class Tiao {
    private String question;
    private String[] option;
    private String answer;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
