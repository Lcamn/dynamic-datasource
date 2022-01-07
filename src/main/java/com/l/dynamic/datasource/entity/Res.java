package com.l.dynamic.datasource.entity;


import com.l.dynamic.datasource.config.InvalidArgumentException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


import java.io.Serializable;
import java.util.Optional;

@Getter
@Setter
@EqualsAndHashCode
public class Res<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int code;
    private T data;
    private String msg;

    public Res() {
    }

    public Res(ResCode errorCode) {
        errorCode = Optional.ofNullable(errorCode).orElse(ResCode.FAILED);
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public static <T> Res<T> ok(T data) {
        ResCode aec = ResCode.SUCCESS;
        if (data instanceof Boolean && Boolean.FALSE.equals(data)) {
            aec = ResCode.FAILED;
        }

        return restResult(data, aec);
    }

    public static <T> Res<T> failed(String msg) {
        return restResult(null, ResCode.FAILED.getCode(), msg);
    }

    public static <T> Res<T> failed(ResCode errorCode) {
        return restResult(null, errorCode);
    }

    public static <T> Res<T> restResult(T data, ResCode errorCode) {
        return restResult(data, errorCode.getCode(), errorCode.getMsg());
    }

    private static <T> Res<T> restResult(T data, int code, String msg) {
        Res<T> apiResult = new Res<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public boolean ok() {
        return ResCode.SUCCESS.getCode() == this.code;
    }

    public T serviceData() {
        if (!this.ok()) {
            throw new InvalidArgumentException(this.msg);
        } else {
            return this.data;
        }
    }

    public Res<T> setCode(final int code) {
        this.code = code;
        return this;
    }

    public Res<T> setData(final T data) {
        this.data = data;
        return this;
    }

    public Res<T> setMsg(final String msg) {
        this.msg = msg;
        return this;
    }

    // public boolean equals(final Object o) {
    //     if (o == this) {
    //         return true;
    //     } else if (!(o instanceof Res)) {
    //         return false;
    //     } else {
    //         Res<?> other = (Res) o;
    //         if (!other.canEqual(this)) {
    //             return false;
    //         } else if (this.getCode() != other.getCode()) {
    //             return false;
    //         } else {
    //             Object this$data = this.getData();
    //             Object other$data = other.getData();
    //             if (this$data == null) {
    //                 if (other$data != null) {
    //                     return false;
    //                 }
    //             } else if (!this$data.equals(other$data)) {
    //                 return false;
    //             }
    //
    //             Object this$msg = this.getMsg();
    //             Object other$msg = other.getMsg();
    //             if (this$msg == null) {
    //                 if (other$msg != null) {
    //                     return false;
    //                 }
    //             } else if (!this$msg.equals(other$msg)) {
    //                 return false;
    //             }
    //
    //             return true;
    //         }
    //     }
    // }

    // protected boolean canEqual(final Object other) {
    //     return other instanceof Res;
    // }

}

