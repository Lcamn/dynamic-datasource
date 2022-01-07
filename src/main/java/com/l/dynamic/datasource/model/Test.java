package com.l.dynamic.datasource.model;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Test {

        private Long id;
        private boolean state;
        private String no;

        @Override
        public String toString() {
                return JSON.toJSONString(this);
        }
}
