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
 * 聊天信息表
 * </p>
 *
 * @author FG
 * @since 2022-01-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tbl_message")
@ApiModel(value="Message对象", description="聊天信息表")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "消息内容")
    private String content;

    @ApiModelProperty(value = "消息类型;系统消息：0，文本消息：1，图片：2，文件：3")
    private String msgType;

    @ApiModelProperty(value = "信息状态：未读/已读;已读：1，未读：0")
    private Integer isRead;

    @ApiModelProperty(value = "发送者")
    private String toUserId;

    @ApiModelProperty(value = "接受者")
    private String fromUserId;

    @ApiModelProperty(value = "聊天类型：私聊/群聊;群聊：0，私聊：1")
    private Integer chatType;

    @ApiModelProperty(value = "群组id")
    private String groupId;

    @ApiModelProperty(value = "创建时间")
      @TableField(fill = FieldFill.INSERT)
    private Date createdTime;


}
