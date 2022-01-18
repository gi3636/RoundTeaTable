package com.fg.roundteatable.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "昵称;媒体号")
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
