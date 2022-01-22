package com.fg.roundteatable.config;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class OssProperties implements InitializingBean {

    //    读取配置文件中的值
    private String endpoint;
    private String keyId;
    private String keySecret;
    private String bucketName;

    //定义公开静态常量
    public static String END_POINT;

    public static String KEY_ID;

    public static String KEY_SECRET;

    public static String BUCKET_NAME;

    //该方法在此类完成创建以后会进行调用，这样就可以直接通过类名.属性名进行直接调用
    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endpoint;
        KEY_ID = keyId;
        KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }
}

