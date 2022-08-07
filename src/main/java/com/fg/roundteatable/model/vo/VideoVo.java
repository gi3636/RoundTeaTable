package com.fg.roundteatable.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VideoVo {
    @ApiModelProperty("视频id")
    private String videoId;
    @ApiModelProperty("用户id")
    private String userId;
    @ApiModelProperty("url地址")
    private String url;
    @ApiModelProperty("封面")
    private String cover;
    @ApiModelProperty("输出类型")
    private String outputType;
    @ApiModelProperty("影片时长")
    private String duration;
    @ApiModelProperty(value = "是否私密;0为公开，1为私密，用户可以设置私密，如此可以不公开给比人看")
    private Integer isPrivate;
    @ApiModelProperty(value = "是否批准;1是批准，0是不批准")
    private Integer approve;
    private String title;
    private String mediaType;

}
