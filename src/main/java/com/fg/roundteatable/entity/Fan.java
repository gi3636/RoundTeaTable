package com.fg.roundteatable.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 粉丝表
 * </p>
 *
 * @author FG
 * @since 2022-01-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tbl_fan")
@ApiModel(value="Fan对象", description="粉丝表")
public class Fan implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "作家用户id")
    private String vlogerId;

    @ApiModelProperty(value = "粉丝用户id")
    private String fanId;

    @ApiModelProperty(value = "粉丝是否是vloger的朋友;如果成为朋友，则本表的双方此字段都需要设置为1，如果有一人取关，则两边都需要设置为0")
    private Integer isFriend;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
