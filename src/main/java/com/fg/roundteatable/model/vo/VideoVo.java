package com.fg.roundteatable.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VideoVo {
    @ApiModelProperty("视频id")
    private String videoId;
    private String userId;
    private String url;
    private String cover;
    private String outputType;
    private String coverURL;
    private String duration;
    private String status;
    private String title;
    private String mediaType;
    private String creationTime;
}
