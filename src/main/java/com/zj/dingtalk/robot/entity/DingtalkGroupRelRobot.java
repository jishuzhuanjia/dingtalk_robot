package com.zj.dingtalk.robot.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2021-11-29
 */
@Data
public class DingtalkGroupRelRobot implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String robotName;

    private Integer groupId;

    private String webHook;

    private Integer status;

}
