package com.fg.roundteatable.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: fenggi123
 * @create: 9/1/2021 10:32 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    @ApiModelProperty("用户id")
    private Integer id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("头像")
    private String avatar;
}
