package com.zj.dingtalk.robot.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class RobotMsgRecvDTO {
    //姓名
    private String senderNick;
    //回调的webhook
    private String sessionWebhook;
    //内容json content
    private JSONObject text;
    //加密ID 工单机器人
    private String chatbotUserId;
    //用户加密ID
    private String senderId;
    //群聊标题
    private String conversationTitle;
    //用户钉钉UserId
    private String senderStaffId;
}