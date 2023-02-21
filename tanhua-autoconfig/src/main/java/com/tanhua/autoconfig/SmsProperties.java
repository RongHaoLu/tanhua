package com.tanhua.autoconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author RongHaoLu
 * @Date 2023/2/21 22:04
 * 配置信息类
 * 配置短信api需要的参数信息
 */
@Data
@ConfigurationProperties(prefix = "tanhua.sms")
public class SmsProperties {
    private String signName;
    private String templateCode;
    private String accessKey;
    private String secret;
}
