package com.fg.roundteatable.common.ResultCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description:
 * @author: fenggi123
 * @create: 8/18/2021 10:51 PM
 */
@AllArgsConstructor
@Getter
public enum ResultCode implements IResultCode {
    SUCCESS(20000, "操作成功"),

    ERROR(20001, "响应失败"),

    UPLOAD_FILE_ERROR(20010, "文件上传失败"),

    FILE_EMPTY(20011, "文件为空"),

    DELETE_ERROR(20012, "删除失败"),

    VALID_CODE_SEND_FAIL(20013, "短信验证码发送失败"),

    LOGIN_ERROR(20014, "登录失败，用户名或密码错误！"),

    REGISTER_ERROR(20015, "注册失败"),

    PASSWORD_NOT_SAME(20016, "密码不一致"),

    ACCOUNT_EXIST(20017, "此用户已存在"),

    LOGIN_SUCCESS(20018, "登入成功"),

    REGISTER_SUCCESS(20019, "注册成功"),

    PARAMS_ERROR(20020, "参数校验失败"),

    AUTH_FAIL(20021, "授权失败");


    private Integer code;
    private String msg;
}
