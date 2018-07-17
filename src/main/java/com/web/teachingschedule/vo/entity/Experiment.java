package com.web.teachingschedule.vo.entity;

import lombok.Data;

/**
 * 课程实验
 * @author q
 */
@Data
public class Experiment {
    /**
     * 实验项目编号（主键） 8位
     */
    private String eId;
    /**
     * 实验名称
     */
    private String eName;
    /**
     * 实验类型(验证/综合/设计)
     */
    private String eType;
    /**
     * 项目学时
     */
    private int eHours = -1;
    /**
     * 课程号
     */
    private String courseId;

}
