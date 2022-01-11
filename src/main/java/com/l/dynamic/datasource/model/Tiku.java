package com.l.dynamic.datasource.model;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @description tiku
 * @author zhengkai.blog.csdn.net
 * @date 2022-01-07
 */

@Getter
@Setter
@Accessors(chain = true)
public class Tiku {



    private String question;


    private String answer;


    private String wronganswer;


    private String option;


    private String datetime;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
