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
public class RobotMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String title;

    private String content;

    private Integer groupId;

    /**
     * 顶部图片地址
     */
    private String imgUrl;

    private Integer robotId;
}
