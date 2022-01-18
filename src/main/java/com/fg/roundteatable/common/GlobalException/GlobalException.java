package com.fg.roundteatable.common.GlobalException;

import com.fg.roundteatable.common.ResultCode.IResultCode;
import lombok.Data;

/**
 * @description:
 * @author: fenggi123
 * @create: 8/23/2021 2:53 PM
 */
@Data
public class GlobalException extends RuntimeException {

    private Integer code;

    private String msg;

    private GlobalException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    private GlobalException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public static GlobalException from(IResultCode resultCode) {
        GlobalException globalException = new GlobalException(resultCode.getCode(), resultCode.getMsg());
        return globalException;
    }

    public static GlobalException from(String msg) {
        GlobalException globalException = new GlobalException(msg);
        return globalException;
    }
}
