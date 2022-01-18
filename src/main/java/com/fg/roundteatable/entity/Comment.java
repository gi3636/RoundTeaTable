package com.fg.roundteatable.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author FG
 * @since 2022-01-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tbl_comment")
@ApiModel(value="Comment对象", description="评论表")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "评论的视频是哪个作者（vloger）的关联id")
    private String vlogerId;

    @ApiModelProperty(value = "如果是回复留言;则本条为子留言，需要关联查询")
    private String parentId;

    @ApiModelProperty(value = "回复的那个视频id")
    private String vlogId;

    @ApiModelProperty(value = "发布留言的用户id")
    private String userId;

    @ApiModelProperty(value = "留言内容")
    private String content;

    @ApiModelProperty(value = "留言的点赞总数")
    private Integer likeCounts;

    @ApiModelProperty(value = "留言时间")
    private Date createTime;

    @ApiModelProperty(value = "是否删除;1是删除，0是不删除")
    @TableLogic
    private Integer deleted;


}
