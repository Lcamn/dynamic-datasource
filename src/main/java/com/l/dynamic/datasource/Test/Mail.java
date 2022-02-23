package com.l.dynamic.datasource.Test;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mail implements Cloneable {
    private String tail;
    private String subject;

    public Mail() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Mail clone() throws CloneNotSupportedException {
        Mail mail = (Mail) super.clone();
        return mail;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
