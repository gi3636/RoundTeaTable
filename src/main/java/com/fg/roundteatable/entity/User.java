package com.fg.roundteatable.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fg.roundteatable.common.ValidException.AddGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author FG
 * @since 2022-01-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tbl_user")
@ApiModel(value="User对象", description="用户表")
@JsonIgnoreProperties(value = {"password","deleted"})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户;")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空",groups = {AddGroup.class})
    private String username;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空",groups = {AddGroup.class})
    private String password;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "昵称;媒体号")
    @NotBlank(message = "昵称不能为空",groups = {AddGroup.class})
    private String nickname;

    @ApiModelProperty(value = "媒体号，唯一标识;类似头条号，抖音号，公众号，唯一标识，需要限制修改次数，比如终生1次，每年1次，每半年1次等，可以用于付费修改。")
    private String no;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "性别;1:男  0:女  2:保密")
    private Integer sex;

    @ApiModelProperty(value = "生日")
    private String birthday;

    @ApiModelProperty(value = "国家")
    private String country;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "简介")
    private String description;

    @ApiModelProperty(value = "个人介绍的背景图")
    private String cover;

    @ApiModelProperty(value = "创建时间;创建时间")
      @TableField(fill = FieldFill.INSERT)
    private Date createdTime;

    @ApiModelProperty(value = "更新时间;更新时间")
      @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

    @ApiModelProperty(value = "是否删除;1是删除，0是不删除")
    @TableLogic
    private Integer deleted;


}
