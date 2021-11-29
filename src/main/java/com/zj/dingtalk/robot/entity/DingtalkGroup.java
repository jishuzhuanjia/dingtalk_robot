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
public class DingtalkGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String groupName;

    private String des;


}
