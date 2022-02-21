package com.fg.roundteatable.common;

import com.fg.roundteatable.common.ResultCode.IResultCode;
import com.fg.roundteatable.common.ResultCode.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: fenggi123
 * @create: 8/18/2021 10:50 PM
 */
@Data
@ApiModel("统一返回结果")
@AllArgsConstructor
@NoArgsConstructor
public class ResultVo {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty("返回")
    private Integer code;

    @ApiModelProperty("返回信息")
    private String message;

    @ApiModelProperty("返回数据")
    private Map<String, Object> data = new HashMap<>();


    public static ResultVo ok() {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(ResultCode.SUCCESS.getCode());
        resultVo.setMessage(ResultCode.SUCCESS.getMessage());
        return resultVo;
    }

    public static ResultVo error() {
        ResultVo resultVo = new ResultVo();
        resultVo.setSuccess(ResultCode.UNKNOWN_REASON.getSuccess());
        resultVo.setCode(ResultCode.UNKNOWN_REASON.getCode());
        resultVo.setMessage(ResultCode.UNKNOWN_REASON.getMessage());
        return resultVo;
    }

    public ResultVo(IResultCode resultCode) {
        this.setSuccess(resultCode.getSuccess());
        this.setCode(resultCode.getCode());
        this.setMessage(resultCode.getMessage());
    }


    public ResultVo msg(String msg) {
        this.setMessage(msg);
        return this;
    }

    public ResultVo code(Integer code) {
        this.setCode(code);
        return this;
    }

    public ResultVo data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }

    public ResultVo data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }


}
