package com.zj.dingtalk.robot.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zj.dingtalk.robot.constants.RobotStatus;
import com.zj.dingtalk.robot.entity.DingtalkGroup;
import com.zj.dingtalk.robot.entity.DingtalkGroupRelRobot;
import com.zj.dingtalk.robot.entity.RobotMessage;
import com.zj.dingtalk.robot.entity.RootMessageButtonLink;
import com.zj.dingtalk.robot.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * RobotMessageService.java
 * </p>
 *
 * @author zhoujian
 * @date 2021/11/29
 * @finished false
 */
@Service
@Slf4j
public class RobotMessageService {

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private DingtalkGroupMapper dingtalkGroupMapper;

    @Autowired
    private DingtalkGroupRelRobotMapper dingtalkGroupRelRobotMapper;

    @Autowired
    private RobotMessageMapper robotMessageMapper;

    @Autowired
    private RootMessageButtonLinkMapper rootMessageButtonLinkMapper;

    @Autowired
    ImageService imageService;

    @Autowired
    SystemParamService systemParamService;

    public RobotMessageService() {
        log.info("机器人服务初始化完成");
    }

    /**
     * 机器人1: 种树信息整合
     */
    @Scheduled(cron = "0 0/40 * * * *")
    public void sendTreesMessage() throws JSONException {
        log.info("机器人开始发送消息");

        // 查找所有设置机器人的群组
        List<DingtalkGroup> dingtalkGroups = dingtalkGroupMapper.selectAll();

        for (DingtalkGroup dg : dingtalkGroups) {

            // 获取群组的所有机器人
            DingtalkGroupRelRobot robotQueryPO = new DingtalkGroupRelRobot();
            robotQueryPO.setGroupId(dg.getId());
            List<DingtalkGroupRelRobot> groupRobots = dingtalkGroupRelRobotMapper.select(robotQueryPO);

            // 每个机器人发送对应的消息
            for (DingtalkGroupRelRobot e : groupRobots) {

                if(RobotStatus.OFF.getValue().equals(e.getStatus())){
                    log.info("机器人处理关闭状态,不处理消息: {}",JSON.toJSONString(e));
                    continue;
                }

                // 获取机器人在该群的消息
                RobotMessage robotMessageQueryPO = new RobotMessage();
                robotMessageQueryPO.setGroupId(dg.getId());
                robotMessageQueryPO.setRobotId(e.getId());

                List<RobotMessage> robotMessages = robotMessageMapper.select(robotMessageQueryPO);
                // 逐个拼装并发送消息
//                {
//                    "msgtype": "actionCard",
//                        "actionCard": {
//                    "title": "强哥 20 年前想打造一间苹果咖啡厅，而它正是 Apple Store 的前身",
//                            "text": "![screenshot](https://img.alicdn.com/tfs/TB1NwmBEL9TBuNjy1zbXXXpepXa-2400-1218.png) \n\n #### 乔布斯 20 年前想打造的苹果咖啡厅 \n\n Apple Store 的设计正从原来满满的科技感走向生活化，而其生活化的走向其实可以追溯到 20 年前苹果一个建立咖啡馆的计划",
//                            "btnOrientation": "0",
//                            "btns": [
//                    {
//                        "title": "内容不错",
//                            "actionURL": "https://www.dingtalk.com/"
//                    },
//                    {
//                        "title": "不感兴趣",
//                            "actionURL": "https://www.dingtalk.com/"
//                    }
//        ]
//                }
//                }
                for (RobotMessage rm : robotMessages) {

                    // 获取按钮和连接
                    RootMessageButtonLink buttonLinkQueryPO = new RootMessageButtonLink();
                    buttonLinkQueryPO.setMessageId(rm.getId());
                    List<RootMessageButtonLink> buttonLinks = rootMessageButtonLinkMapper.select(buttonLinkQueryPO);

                    JSONObject sendMsgJSONObject = new JSONObject();
                    sendMsgJSONObject.put("msgtype", "actionCard");

                    // actionCard;
                    JSONObject actionCardJSONObject = new JSONObject();
                    actionCardJSONObject.put("text", "![screenshot]" + "(" + imageService.getRandomImageUrl() + ")\n\n #### " + rm.getContent());
                    actionCardJSONObject.put("title", rm.getTitle());
                    actionCardJSONObject.put("btnOrientation", "0");

                    // btns
                    List<JSONObject> jsonObject3 = new ArrayList<>();
                    for (RootMessageButtonLink rootMessageButtonLink1 : buttonLinks) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("title", rootMessageButtonLink1.getButtonText());
                        jsonObject.put("actionURL", rootMessageButtonLink1.getButtonLink());
                        jsonObject3.add(jsonObject);
                    }
                    actionCardJSONObject.put("btns", jsonObject3);

                    sendMsgJSONObject.put("actionCard", actionCardJSONObject);

                    HttpHeaders httpHeaders = new HttpHeaders();
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    HttpEntity<JSONObject> httpEntity = new HttpEntity<>(sendMsgJSONObject, httpHeaders);

                    log.info("机器人[{}]向群[{}]发送消息: {}", e, dg, JSON.toJSONString(sendMsgJSONObject));
                    ResponseEntity<JSONObject> exchange = restTemplate.exchange(e.getWebHook(), HttpMethod.POST, httpEntity, JSONObject.class);
                    log.info("发送返回: {}", JSON.toJSONString(exchange.getBody()));

                    // 休眠
                    try {
                        Thread.sleep(Long.parseLong(systemParamService.getParam("robot_send_message_interval")));
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            }
        }

    }
}
