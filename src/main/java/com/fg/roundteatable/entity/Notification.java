package com.fg.roundteatable.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 消息通知
 * </p>
 *
 * @author FG
 * @since 2022-01-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tbl_notification")
@ApiModel(value="Notification对象", description="消息通知")
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "通知类型;系统，关注，回复，点赞")
    private String type;

    @ApiModelProperty(value = "发送者")
    private String fromUserId;

    @ApiModelProperty(value = "接受者")
    private String toUserId;

    @ApiModelProperty(value = "评论id")
    private String commentId;

    @ApiModelProperty(value = "视频id")
    private String vlogId;

    @ApiModelProperty(value = "通知内容")
    private String content;

    @ApiModelProperty(value = "创建时间")
      @TableField(fill = FieldFill.INSERT)
    private Date createdTime;


}
