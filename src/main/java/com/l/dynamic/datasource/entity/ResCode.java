package com.l.dynamic.datasource.entity;

import lombok.Getter;

@Getter
public enum ResCode {
    FAILED(-1, "操作失败"),
    SUCCESS(0, "执行成功");

    private final int code;
    private final String msg;

    ResCode(final int code, final String msg) {
        this.code = code;
        this.msg = msg;
    }
}
