package com.zj.dingtalk.robot.po.robot.message;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * TextRobotMessage.java
 * </p>
 *
 * @author zhoujian
 * @date 2021/12/02
 * @finished false
 */
@Setter
@Getter
public class TextRobotMessage extends BasicRobotMessage {

    private final String msgtype="text";

    /**
     * 文本消息
     */
    private Text text;
}
