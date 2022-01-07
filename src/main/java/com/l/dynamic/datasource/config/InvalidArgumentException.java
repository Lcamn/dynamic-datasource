package com.l.dynamic.datasource.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidArgumentException extends RuntimeException{
    private int code;
    public InvalidArgumentException() {
        super();
    }

    public InvalidArgumentException(String message) {
        super(message);
        this.code = 500;
    }
}
