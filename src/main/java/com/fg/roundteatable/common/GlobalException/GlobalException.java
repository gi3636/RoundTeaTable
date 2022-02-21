package com.fg.roundteatable.common.GlobalException;

import com.fg.roundteatable.common.ResultCode.IResultCode;
import com.fg.roundteatable.common.ResultCode.ResultCode;
import lombok.Data;

/**
 * @description:
 * @author: fenggi123
 * @create: 8/23/2021 2:53 PM
 */
@Data
public class GlobalException extends RuntimeException {
    private Boolean success;

    private Integer code;

    private String message;

    private GlobalException(IResultCode resultCode) {
        super(resultCode.getMessage());
        this.success = resultCode.getSuccess();
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }


    private GlobalException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    private GlobalException(Boolean success,Integer code, String message) {
        super(message);
        this.message = message;
        this.success = success;
        this.code = code;
    }


    public GlobalException(String message) {
        super(message);
        this.message = message;
    }

    public GlobalException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.success =resultCode.getSuccess();
        this.code = resultCode.getCode();
    }

    public static GlobalException from(IResultCode resultCode) {
        GlobalException globalException = new GlobalException(resultCode.getSuccess(),resultCode.getCode(), resultCode.getMessage());
        return globalException;
    }

    public static GlobalException from(String msg) {
        GlobalException globalException = new GlobalException(msg);
        return globalException;
    }
}
