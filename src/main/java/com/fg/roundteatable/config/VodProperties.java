package com.fg.roundteatable.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix="aliyun.vod")
public class VodProperties {

    private String ki;
    private String ks;
    private String templateGroupId;
    private String workflowId;
}
