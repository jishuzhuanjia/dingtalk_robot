package com.zj.dingtalk.robot.controller;

import com.alibaba.fastjson.JSON;
import com.zj.dingtalk.robot.dto.RobotMsgRecvDTO;
import com.zj.dingtalk.robot.po.robot.message.Text;
import com.zj.dingtalk.robot.po.robot.message.TextRobotMessage;
import com.zj.dingtalk.robot.util.RobotMsgUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * MessageController.java
 * </p>
 *
 * @author zhoujian
 * @date 2021/12/02
 * @finished false
 */
@RequestMapping("/message")
@Slf4j
@RestController
public class MessageController {

    @Autowired
    RobotMsgUtil robotMsgUtil;

//    {
//        "chatbotUserId": "$:LWCP_v1:$uFzR3MDWBqZv1xgupFJob3+D95L5oBtq",
//            "conversationTitle": "熊熊🐻乐园总部群",
//            "senderId": "$:LWCP_v1:$HaUOzKcushwQViAw2pxhNoKAWusvyqL/",
//            "senderNick": "光头强",
//            "senderStaffId": "2018072853690429",
//            "sessionWebhook": "https://oapi.dingtalk.com/robot/sendBySession?session=84baf57af726cb4237a96630eb021089",
//            "text": {
//        "content": " 你好，管家"
//    }
//    }

    @RequestMapping(value = "receive",method = RequestMethod.POST)
    public String message(@RequestBody RobotMsgRecvDTO message){
        log.info("机器人接收到信息: {}", JSON.toJSONString(message));

        TextRobotMessage textRobotMessage = new TextRobotMessage();
        Text text = new Text();
        text.setContent("正在被光头强训练中，暂时不能回答问题");
        textRobotMessage.setText(text);
        robotMsgUtil.sendTextMessage("https://oapi.dingtalk.com/robot/send?access_token=e5ff02286d1e2416314d9961574dba27b34f0890b8f54dac33d62f3f8451f06f",
                textRobotMessage);
        return "success";
    }
}
