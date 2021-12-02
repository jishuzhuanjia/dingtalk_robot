package com.zj.dingtalk.robot.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zj.dingtalk.robot.po.robot.message.TextRobotMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 * RotMsgUtil.java
 * </p>
 *
 * @author zhoujian
 * @date 2021/12/02
 * @finished false
 */
@Slf4j
@Component
public class RobotMsgUtil {

    private RestTemplate restTemplate = new RestTemplate();

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 发送文本消息
     */
    public void sendTextMessage(String robotWebhook, TextRobotMessage textRobotMessage){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TextRobotMessage> httpEntity = new HttpEntity<>(textRobotMessage, httpHeaders);
        log.info("机器人发送消息: {}",JSON.toJSONString(textRobotMessage));

        ResponseEntity<JSONObject> exchange = restTemplate.exchange(robotWebhook, HttpMethod.POST, httpEntity, JSONObject.class);
        log.info("发送返回: {}", JSON.toJSONString(exchange.getBody()));
    }
}
