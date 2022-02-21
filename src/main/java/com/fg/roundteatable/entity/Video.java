package com.fg.roundteatable.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 短视频表
 * </p>
 *
 * @author FG
 * @since 2022-01-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tbl_video")
@ApiModel(value="video对象", description="短视频表")
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "对应用户表id;vlog视频发布者")
    private String userId;

    @ApiModelProperty(value = "视频存放id")
    private  String videoId ;

    @ApiModelProperty(value = "视频播放地址")
    private String url;

    @ApiModelProperty(value = "视频封面")
    private String cover;

    @ApiModelProperty(value = "视频标题;可以为空")
    private String title;

    @ApiModelProperty(value = "视频的宽")
    private Integer width;

    @ApiModelProperty(value = "视频的高")
    private Integer height;

    @ApiModelProperty(value = "点赞总数")
    private Integer likeCounts;

    @ApiModelProperty(value = "评论总数")
    private Integer commentsCounts;

    @ApiModelProperty(value = "是否私密;0为公开，1为私密，用户可以设置私密，如此可以不公开给比人看")
    private Integer isPrivate;

    @ApiModelProperty(value = "创建时间;创建时间")
      @TableField(fill = FieldFill.INSERT)
    private Date createdTime;

    @ApiModelProperty(value = "更新时间;更新时间")
      @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

    @ApiModelProperty(value = "是否删除;1是删除，0是不删除")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "是否批准;1是批准，0是不批准")
    private Integer approve;

    @ApiModelProperty(value = "视频简介")
    private String description;

    @ApiModelProperty(value = "理由")
    private String reason;


}
