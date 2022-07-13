package com.l.dynamic.datasource.model;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@TableName("tiaozhan")
@RequiredArgsConstructor(staticName = "of")
public class TiaoZhan {
    private String question;
    private String answer;
    private String option;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
