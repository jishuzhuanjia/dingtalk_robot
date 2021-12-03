package com.zj.dingtalk.robot.constants;

/**
 * <p>
 * RobotStatus.java
 * </p>
 *
 * @author zhoujian
 * @date 2021/12/03
 * @finished false
 */
public enum RobotStatus {

    /**
     * 关闭状态
     */
    OFF("关闭状态",0),

    /**
     * 打开状态
     */
    ON("打开状态",1);

    private Integer value;

    private String desc;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    RobotStatus(String desc, Integer value) {
        this.desc = desc;
        this.value = value;
    }
}
