package com.fg.roundteatable.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 点赞短视频关联表
 * </p>
 *
 * @author FG
 * @since 2022-01-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tbl_user_liked_vlog")
@ApiModel(value="UserLikedVlog对象", description="点赞短视频关联表")
public class UserLikedVlog implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "喜欢的短视频id")
    private String vlogId;


}
