package com.tanhua.server.service;

import com.tanhua.template.SmsTemplate;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * @Author RongHaoLu
 * @Date 2023/2/21 22:57
 */
@Service
public class UserService {

    @Autowired
    private SmsTemplate smsTemplate;

    @Autowired
    RedisTemplate<String,String> redisTemplate;


    /**
     * @param mobile
     * @return
     * 发送短信
     */
    public ResponseEntity sendMsg(String mobile){
        //生成验证码
        String code = RandomStringUtils.randomNumeric(6);

        smsTemplate.sendSms(mobile,code);

        redisTemplate.opsForValue().set("CHECK_CODE_"+mobile,code, Duration.ofMinutes(5));


        return ResponseEntity.ok(null);

    }
}
