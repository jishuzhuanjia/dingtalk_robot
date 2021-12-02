package com.zj.dingtalk.robot.po.robot.message;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <p>
 * At.java
 * </p>
 *
 * @author zhoujian
 * @date 2021/12/02
 * @finished false
 */
@Setter
@Getter
public class At {

    /**
     * 被@人员手机号
     */
    List<String> atMobiles;

    /**
     * 被@人员id
     */
    List<String> atUserIds;
}
